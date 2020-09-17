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

public class BaseFragment extends Fragment {

    public static final int SETTING_REQUEST_CODE = 101;

    public void showToast(String msg) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(Integer msg) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    public void requestImagesPermission(final MultiplePermissionsListener multiplePermissionsListener) {
        Dexter.withActivity(getActivity()).withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(multiplePermissionsListener).check();
    }


    public MultiplePermissionsListener showPermissionDialog() {
        MultiplePermissionsListener dialogMultiplePermissionsListener =
                DialogOnAnyDeniedMultiplePermissionsListener.Builder
                        .withContext(requireActivity())
                        .withTitle("Camera & Storage permission")
                        .withMessage("Both camera and Storage permissions are needed to continue")
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
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
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
        Uri uri = Uri.fromParts("package", requireActivity().getPackageName(), null);
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
