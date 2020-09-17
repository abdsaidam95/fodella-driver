package com.foodella.driver.base;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.foodella.driver.R;
import com.foodella.driver.utils.AppLanguageContextWrapper;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import static com.foodella.driver.base.BaseFragment.SETTING_REQUEST_CODE;

import java.util.Objects;

import static com.foodella.driver.utils.AppUtil.hideKeyboard;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(AppLanguageContextWrapper.wrap(newBase));
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(Integer msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void startNewActivity(Class<?> newActivity) {
        startNewActivity(newActivity, null, false);
    }

    public void startNewActivity(Class<?> newActivity, Intent extras) {
        startNewActivity(newActivity, extras, false);
    }

    public void startNewActivity(Class<?> newActivity, Intent extras, boolean clearStack) {
        Intent intent = new Intent(this, newActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (clearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (extras != null) {
            intent.putExtras(extras);
        }
        hideKeyboard(this);
        startActivity(intent);
    }

    public void replaceFragment(Fragment fragment, int container) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public void replaceFragment(Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            transaction.replace(R.id.fragmentContainerView, fragment, tag);
        } else {
            transaction.replace(R.id.fragmentContainerView, fragment, tag);
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }


    public void initializeToolbar(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void replaceFragment(Fragment fragment, String tag, int container, boolean addToBackStack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (!addToBackStack) {
            transaction.replace(container, fragment, tag);
        } else {
            transaction.replace(container, fragment, tag);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void requestImagesPermission(final MultiplePermissionsListener multiplePermissionsListener) {
        Dexter.withActivity(this).withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(multiplePermissionsListener).check();
    }


    public MultiplePermissionsListener showPermissionDialog() {
        MultiplePermissionsListener dialogMultiplePermissionsListener =
                DialogOnAnyDeniedMultiplePermissionsListener.Builder
                        .withContext(this)
                        .withTitle(R.string.camera_storage)
                        .withMessage(R.string.both_camera_needed)
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.mipmap.ic_launcher)
                        .build();
        return dialogMultiplePermissionsListener;
    }

    private void pickFromCamera() {
        ImagePicker.create(this)
                .returnMode(ReturnMode.CAMERA_ONLY)
                .single()
                .start();
    }

    private void pickFromGallery() {
        ImagePicker.create(this)
                .returnMode(ReturnMode.GALLERY_ONLY)
                .folderMode(true)
                .single()
                .start();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.need_permissions);
        builder.setMessage(R.string.this_app_need_permission);
        builder.setPositiveButton(R.string.go_settings, (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, SETTING_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_REQUEST_CODE) {
            requestCameraPermissions();
            requestPermissions();
        }
    }


    public void requestCameraPermissions() {
        MultiplePermissionsListener multiplePermissionsListener = showPermissionDialog();
        MultiplePermissionsListener mBaseMultiplePermissionsListener = new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    pickFromCamera();
                }
                if (report.isAnyPermissionPermanentlyDenied()) {
                    showSettingsDialog();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        };
        MultiplePermissionsListener compositePermissionsListener = new CompositeMultiplePermissionsListener(mBaseMultiplePermissionsListener, multiplePermissionsListener);
        requestImagesPermission(compositePermissionsListener);
    }


    public void requestPermissions() {
        MultiplePermissionsListener multiplePermissionsListener = showPermissionDialog();
        MultiplePermissionsListener mBaseMultiplePermissionsListener = new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    pickFromGallery();
                }
                if (report.isAnyPermissionPermanentlyDenied()) {
                    showSettingsDialog();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        };
        MultiplePermissionsListener compositePermissionsListener = new CompositeMultiplePermissionsListener(mBaseMultiplePermissionsListener, multiplePermissionsListener);
        requestImagesPermission(compositePermissionsListener);
    }


}
