package com.foodella.driver.features.addCar.fragments;


import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.esafirm.imagepicker.model.Image;
import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import static com.foodella.driver.utils.AppAction.ACTION_SHoW_BOTOOM_SHEET_IMGE;

public class CarLicenseViewModel extends BaseViewModel {

    public MutableLiveData<String> vehicleLicenseImage = new MutableLiveData<>();
    public MutableLiveData<String> insuranceCertificateImage = new MutableLiveData<>();
    public MutableLiveData<String> vehicleImage = new MutableLiveData<>();

    public MutableLiveData<Integer> vehicleLicenceImageErrorVisibility = new MutableLiveData<>();
    public MutableLiveData<Integer> insuranceImageErrorVisibility = new MutableLiveData<>();
    public MutableLiveData<Integer> vehicleImageErrorVisibility = new MutableLiveData<>();

    public MutableLiveData<Integer> vehicleLicenceImageErrorValue = new MutableLiveData<>();
    public MutableLiveData<Integer> insuranceImageErrorValue = new MutableLiveData<>();
    public MutableLiveData<Integer> vehicleImageErrorValue = new MutableLiveData<>();

    public MutableLiveData<Integer> position = new MutableLiveData<>();

    Vehicle vehicle;

    public CarLicenseViewModel() {
        vehicleLicenceImageErrorVisibility.setValue(View.INVISIBLE);
        insuranceImageErrorVisibility.setValue(View.INVISIBLE);
        vehicleImageErrorVisibility.setValue(View.INVISIBLE);
    }

    public void onStart() {

    }

    public void onClickVehicleLicenseImage() {
        position.setValue(1);
        doAction.setValue(ACTION_SHoW_BOTOOM_SHEET_IMGE);
        vehicleLicenceImageErrorVisibility.setValue(View.INVISIBLE);
    }

    public void onClickInsuranceImage() {
        position.setValue(2);
        doAction.setValue(ACTION_SHoW_BOTOOM_SHEET_IMGE);
        insuranceImageErrorVisibility.setValue(View.INVISIBLE);

    }

    public void onClickVehicleImage() {
        position.setValue(3);
        doAction.setValue(ACTION_SHoW_BOTOOM_SHEET_IMGE);
        vehicleImageErrorVisibility.setValue(View.INVISIBLE);

    }

    public void onClickClose() {

    }


    public void onSelectedImage(Image image) {
        if (position.getValue() == 1) {
            vehicleLicenseImage.setValue(image.getPath());
        } else if (position.getValue() == 2) {
            insuranceCertificateImage.setValue(image.getPath());
        } else {
            vehicleImage.setValue(image.getPath());
        }
    }

    public Vehicle generateVehicle() {
        vehicle = new Vehicle();
        vehicle.setVehicleLicenseImage(vehicleLicenseImage.getValue());
        vehicle.setInsuranceCertificateImage(insuranceCertificateImage.getValue());
        vehicle.setVehicleImage(vehicleImage.getValue());
        return vehicle;
    }


    private boolean validationVehicleLicenseImage() {
        if ((vehicleLicenseImage.getValue() == null)) {
            vehicleLicenceImageErrorVisibility.setValue(View.VISIBLE);
            vehicleLicenceImageErrorValue.setValue((R.string.please_enter_vehicle_license_image));
            return false;
        } else {
            vehicleLicenceImageErrorVisibility.setValue(View.INVISIBLE);
            return true;
        }
    }


    private boolean validationInsuranceCertificateImage() {
        if ((insuranceCertificateImage.getValue() == null)) {
            insuranceImageErrorVisibility.setValue(View.VISIBLE);
            insuranceImageErrorValue.setValue((R.string.please_enter_insurance_image_image));
            return false;
        } else {
            insuranceImageErrorVisibility.setValue(View.INVISIBLE);
            return true;
        }
    }

    private boolean validationVehicleImage() {
        if ((vehicleImage.getValue() == null)) {
            vehicleImageErrorVisibility.setValue(View.VISIBLE);
            vehicleImageErrorValue.setValue((R.string.please_enter_vehicle_image));
            return false;
        } else {
            vehicleImageErrorVisibility.setValue(View.INVISIBLE);
            return true;
        }
    }

    public void onClickSave() {
        Boolean b1 = validationVehicleLicenseImage();
        Boolean b2 = validationInsuranceCertificateImage();
        Boolean b3 = validationVehicleImage();

        if (b1 && b2 && b3)
            EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_CAR_LIST));
    }


}