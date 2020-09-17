package com.foodella.driver.bottomSheets.vehicleModel.adapter;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

public class VehicleItemViewModel extends BaseViewModel {

    private Vehicle vehicle;
    public int positionClick;
    private int getPositionClick;

    public MutableLiveData<String> cityName = new MutableLiveData<>();
    public MutableLiveData<Integer> icon = new MutableLiveData<>();

    public VehicleItemViewModel(Vehicle vehicle, int position) {
        this.vehicle = vehicle;
        this.positionClick = position;
    }

    public void onStart(int position) {
        this.getPositionClick = position;
        cityName.setValue(vehicle.getVehicleName());
        boolean isSelected = (position == positionClick);
        if (isSelected) {
            icon.setValue(R.drawable.ic_group_bottom_cheaked);
        } else {
            icon.setValue(R.drawable.ic_ellipsed_radio_button);
        }
    }

    public void onClickItem() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_VEHICLE_ITEM, vehicle, positionClick));
    }

}


