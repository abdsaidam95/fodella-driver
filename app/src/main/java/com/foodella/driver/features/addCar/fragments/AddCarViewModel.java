package com.foodella.driver.features.addCar.fragments;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.insuranceType.adapter.InsuranceType;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.VehicleTypes;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

public class AddCarViewModel extends BaseViewModel {

    public MutableLiveData<String> vehicleNumber = new MutableLiveData<>();
    public MutableLiveData<String> manufacturingYear = new MutableLiveData<>();
    public MutableLiveData<String> vehicleColor = new MutableLiveData<>();
    public MutableLiveData<String> vehicleModel = new MutableLiveData<>();
    public MutableLiveData<String> insuranceType = new MutableLiveData<>();

    public MutableLiveData<Integer> vehicleType = new MutableLiveData<>();

    public MutableLiveData<Boolean> isBicycleClickable = new MutableLiveData<>();
    public MutableLiveData<Boolean> isCarClickable = new MutableLiveData<>();
    public MutableLiveData<Boolean> isBusClickable = new MutableLiveData<>();

    public MutableLiveData<Integer> vehicleNumberError = new MutableLiveData<>();
    public MutableLiveData<Integer> manufacturingYearError = new MutableLiveData<>();
    public MutableLiveData<Integer> vehicleColorError = new MutableLiveData<>();
    public MutableLiveData<Integer> vehicleModelError = new MutableLiveData<>();
    public MutableLiveData<Integer> insuranceTypeError = new MutableLiveData<>();
    public MutableLiveData<Integer> vehicleTypeError = new MutableLiveData<>();


    public TextChange vehicleModelTextChange = value -> vehicleModelError.setValue(0);
    public TextChange insuranceTypeTextChange = value -> insuranceTypeError.setValue(0);
    public TextChange vehicleNumberTextChange = value -> vehicleNumberError.setValue(0);
    public TextChange manufacturingYearTextChange = value -> manufacturingYearError.setValue(0);
    public TextChange vehicleColorTextChange = value -> vehicleColorError.setValue(0);
    public TextChange vehicleTypeTextChange = value -> vehicleTypeError.setValue(0);

    public Vehicle vehicle;
    public int selectedVehiclePosition = -1;
    public int selectedInsuranceTypePosition = -1;

    private VehicleTypes selected;

    public AddCarViewModel() {

        vehicleModelError.setValue(0);
        insuranceTypeError.setValue(0);
        vehicleTypeError.setValue(0);

        isBicycleClickable.setValue(false);
        isCarClickable.setValue(false);
        isBusClickable.setValue(false);

    }


    public void onStart() {


    }

    public void onClickVehicleModel() {
        doAction.setValue(AppAction.ACTION_VEHICLE_MODEL);
    }

    public void onClickInsuranceType() {
        doAction.setValue(AppAction.ACTION_INSURANCE_TYPE);
    }

    public void onClickBicycle() {
        selected = VehicleTypes.TYPE_BICYCLE;
        vehicleType.setValue(R.string.bicycle);
        isBicycleClickable.setValue(true);
        isCarClickable.setValue(false);
        isBusClickable.setValue(false);
    }

    public void onClickCar() {
        selected = VehicleTypes.TYPE_CAR;
        vehicleType.setValue(R.string.car);
        isBicycleClickable.setValue(false);
        isCarClickable.setValue(true);
        isBusClickable.setValue(false);
    }

    public void onClickBus() {
        selected = VehicleTypes.TYPE_BUS;
        vehicleType.setValue(R.string.bus);
        isBicycleClickable.setValue(false);
        isCarClickable.setValue(false);
        isBusClickable.setValue(true);

    }


    private boolean validationInsuranceType() {
        if (TextUtils.isEmpty(insuranceType.getValue())) {
            insuranceTypeError.setValue((R.string.please_enter_insurance_type));
            return false;
        } else {
            insuranceTypeError.setValue(0);
            return true;
        }
    }


    private boolean validationVehicleColor() {
        if (TextUtils.isEmpty(vehicleColor.getValue())) {
            vehicleColorError.setValue((R.string.please_enter_vehicle_color));
            return false;
        } else {
            vehicleColorError.setValue(0);
            return true;
        }
    }


    private boolean validationVehicleModel() {

        if (TextUtils.isEmpty(vehicleModel.getValue())) {
            vehicleModelError.setValue((R.string.please_enter_vehicle_model));
            return false;
        } else {
            vehicleModelError.setValue(0);
            return true;
        }
    }

    private boolean validationVehicleNumber() {
        if (TextUtils.isEmpty(vehicleNumber.getValue())) {
            vehicleNumberError.setValue((R.string.please_enter_vehicle_number));
            return false;

        } else {
            vehicleNumberError.setValue(0);
            return true;
        }
    }

    private boolean validationManufacturingYear() {
        if (TextUtils.isEmpty(manufacturingYear.getValue())) {
            manufacturingYearError.setValue((R.string.please_enter_manufacturing_year));
            return false;
        } else if (manufacturingYear.getValue().length() != 4) {
            manufacturingYearError.setValue((R.string.manufacturing_year_length_error));
            return false;
        } else {
            manufacturingYearError.setValue(0);
            return true;
        }
    }

    public void onClickNext() {
        Boolean b1 = validationVehicleModel();
        Boolean b2 = validationVehicleNumber();
        Boolean b3 = validationManufacturingYear();
        Boolean b4 = validationVehicleColor();
        Boolean b5 = validationInsuranceType();
        Boolean b6 = validationVehicleType();
        Vehicle v = generateVehicle();
        if (b1 && b2 && b3 && b4 && b5 && b6)
            EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_CAR_LICENSE, generateVehicle()));

    }

    private Boolean validationVehicleType() {
//        if (!((selected == VehicleTypes.TYPE_CAR) || (selected == VehicleTypes.TYPE_BUS) || (selected == VehicleTypes.TYPE_BICYCLE))) {
//            vehicleTypeError.setValue((R.string.please_select_vehicle_shape));
//            return false;
//        } else {
//            vehicleTypeError.setValue(0);
//            return true;
//        }

        if ((!isBicycleClickable.getValue() && !isCarClickable.getValue() && !isBusClickable.getValue())) {
            vehicleTypeError.setValue((R.string.please_select_vehicle_shape));
            return false;
        } else {
            vehicleTypeError.setValue(0);
            return true;
        }


    }

    public Vehicle generateVehicle() {
        vehicle = new Vehicle();
        vehicle.setVehicleName(vehicleModel.getValue());
        vehicle.setVehicleNumber(vehicleNumber.getValue());
        vehicle.setManufacturingYear(manufacturingYear.getValue());
        vehicle.setVehicleColor(vehicleColor.getValue());
        vehicle.setInsuranceType(insuranceType.getValue());
        vehicle.setVehicleType(String.valueOf(vehicleType.getValue()));
        Log.d("ABR", "generateVehicle: " + vehicleModel.getValue());
        Log.d("ABR", "generateVehicle: " + vehicleNumber.getValue());
        Log.d("ABR", "generateVehicle: " + manufacturingYear.getValue());
        Log.d("ABR", "generateVehicle: " + vehicleColor.getValue());
        Log.d("ABR", "generateVehicle: " + insuranceType.getValue());
        Log.d("ABR", "generateVehicle: " + vehicleType.getValue());
        return vehicle;
    }

    public void onSelectedVehicle(ActionEvent actionEvent) {
        Vehicle vehicle = (Vehicle) actionEvent.getData();
        selectedVehiclePosition = actionEvent.getPosition();
        vehicleModel.setValue(vehicle.getVehicleName());
    }

    public void onSelectedInsuranceType(ActionEvent actionEvent) {
        InsuranceType insurance = (InsuranceType) actionEvent.getData();
        selectedInsuranceTypePosition = actionEvent.getPosition();
        insuranceType.setValue(insurance.getInsuranceName());
    }


    public int getVehicleSelectedPosition() {
        return selectedVehiclePosition;
    }

    public int getInsuranceTypeSelectedPosition() {
        return selectedInsuranceTypePosition;
    }
}