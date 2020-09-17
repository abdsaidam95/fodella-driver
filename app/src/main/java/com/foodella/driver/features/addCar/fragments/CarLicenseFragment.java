package com.foodella.driver.features.addCar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.pickImage.PickImageBottomSheet;
import com.foodella.driver.databinding.FragmentCarLicenseBinding;
import com.foodella.driver.features.addCar.AddCarActivity;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.features.profile.ProfileActivity;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.foodella.driver.utils.AppAction.ACTION_CAR_LIST;
import static com.foodella.driver.utils.AppAction.ACTION_GO_CAMERA;
import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;

public class CarLicenseFragment extends BaseFragment {

    private CarLicenseViewModel viewModel;
    private FragmentCarLicenseBinding binding;

    public static CarLicenseFragment newInstance() {
        return new CarLicenseFragment();
    }

    private Vehicle vehicle;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarLicenseBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(CarLicenseViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    private void initializeView() {
        viewModel.onStart();
        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            if (appAction == ACTION_SHoW_BOTOOM_SHEET_IMGE) {
                showPickImageDialog();
            }
        });

    }

    private void showPickImageDialog() {
        PickImageBottomSheet pickImageBottomSheet = new PickImageBottomSheet();
        pickImageBottomSheet.show(getChildFragmentManager(), "pickImageBottomSheet");
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

        } else if (actionEvent.getActions() == ACTION_CAR_LIST) {
            if (getArguments() != null) {
                vehicle = (Vehicle) getArguments().getSerializable("vehicle");
                Objects.requireNonNull(vehicle).setVehicleLicenseImage(viewModel.vehicleLicenseImage.getValue());
                vehicle.setVehicleImage(viewModel.vehicleImage.getValue());
                vehicle.setInsuranceCertificateImage(viewModel.insuranceCertificateImage.getValue());
            }

            Intent intent = new Intent();
            intent.putExtra("vehicle", vehicle);
            requireActivity().setResult(RESULT_OK, intent);
            requireActivity().finish();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            if (image != null) {
                viewModel.onSelectedImage(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}