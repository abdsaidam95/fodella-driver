package com.foodella.driver.features.editProfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.bottomSheets.pickImage.PickImageBottomSheet;
import com.foodella.driver.bottomSheets.selectCity.SelectSingleCityBottomSheet;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.ActivityEditProfileBinding;
import com.foodella.driver.databinding.ActivityNotificationsBinding;
import com.foodella.driver.features.notifacation.NotificationsViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

import static com.foodella.driver.utils.AppAction.ACTION_GET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_GO_CAMERA;
import static com.foodella.driver.utils.AppAction.ACTION_GO_PROFILE_FRAGMENT;
import static com.foodella.driver.utils.AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;
import static com.foodella.driver.utils.AppAction.SHOW_DIALOG_DATE;
import static com.foodella.driver.utils.AppConst.RES_NOT_OK;

public class EditProfileActivity extends BaseActivity {

    private EditProfileViewModel viewModel;
    private ActivityEditProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(EditProfileViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.appBar.toolbar);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.edit_profile));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel.doAction.observe(this, appAction -> {
            if (appAction == ACTION_SHOW_BOTOOM_SHEET_CITY) {
                SelectSingleCityBottomSheet cityBottomSheet = new SelectSingleCityBottomSheet();
                cityBottomSheet.show(getSupportFragmentManager(), "exampleBottomSheetCity");
            } else if (appAction == ACTION_SHoW_BOTOOM_SHEET_IMGE) {
                PickImageBottomSheet pickImageBottomSheet = new PickImageBottomSheet();
                pickImageBottomSheet.show(getSupportFragmentManager(), "exampleBottomSheetImge");
            } else if (appAction == SHOW_DIALOG_DATE) {

                new DatePickerDialog(EditProfileActivity.this, viewModel.date, viewModel.calendar
                        .get(Calendar.YEAR), viewModel.calendar.get(Calendar.MONTH),
                        viewModel.calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        Profile profile = (Profile) Objects.requireNonNull(getIntent().getExtras()).getSerializable("profileNotEdit");
        assert profile != null;
        viewModel.setInisializeData(profile);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            if (image != null) {
                viewModel.onSelectedImage(image);
            }
        } else {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onActionEvent(ActionEvent actionEvent) {
        if (actionEvent.getActions() == AppAction.ACTION_GO_GALLERY) {
            requestPermissions();
        } else if (actionEvent.getActions() == ACTION_GO_CAMERA) {
            requestCameraPermissions();
        } else if (actionEvent.getActions() == ACTION_GET_CITY) {
            City city = (City) actionEvent.getData();
            viewModel.city.setValue(city.getCityName());
        } else if (actionEvent.getActions() == ACTION_GO_PROFILE_FRAGMENT) {
            Profile profile = viewModel.getProfile();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("profile", profile);
            setResult(RES_NOT_OK, returnIntent);
            finish();
            onBackPressed();

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}