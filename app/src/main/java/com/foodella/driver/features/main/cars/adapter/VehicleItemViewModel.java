package com.foodella.driver.features.main.cars.adapter;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;

public class VehicleItemViewModel extends BaseViewModel {

    public MutableLiveData<String> vehicleName = new MutableLiveData<>();
    public MutableLiveData<String> vehicleNumber = new MutableLiveData<>();
    public MutableLiveData<String> dateOfBuild = new MutableLiveData<>();
    public MutableLiveData<String> status = new MutableLiveData<>();
    public MutableLiveData<String> idcardImge = new MutableLiveData<>();
    public Vehicle vehicle;

    public VehicleItemViewModel(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void onStart() {
        if (vehicle!=null){
        vehicleName.setValue(vehicle.getVehicleName());
        vehicleNumber.setValue(vehicle.getVehicleNumber());
        dateOfBuild.setValue(vehicle.getManufacturingYear());
        status.setValue(vehicle.getStatus());
            Log.d("ergerg","regfre"+vehicle.getStatus());
        idcardImge.setValue(vehicle.getVehicleImage());
    }
}}
