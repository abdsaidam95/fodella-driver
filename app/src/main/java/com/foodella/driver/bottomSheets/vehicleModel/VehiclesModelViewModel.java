package com.foodella.driver.bottomSheets.vehicleModel;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.vehicleModel.adapter.VehiclesAdapter;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Objects;

import static com.foodella.driver.utils.AppAction.ACTION_BACK;
import static com.foodella.driver.utils.AppAction.ACTION_GET_CITY;
import static com.foodella.driver.utils.AppAction.ACTION_GET_VEHICLE;

public class VehiclesModelViewModel extends BaseViewModel {

    private ArrayList<Vehicle> items = new ArrayList<>();
    public MutableLiveData<VehiclesAdapter> vehiclesAdapter = new MutableLiveData<>();


    public VehiclesModelViewModel() {
        vehiclesAdapter.setValue(new VehiclesAdapter());
        emptyColor.setValue(R.color.colorWhite);
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart() {
        setVehicles();
    }

    private void setVehicles() {
        items.clear();
        items.add(new Vehicle("شيفروليه"));
        items.add(new Vehicle("شيفروليه"));
        items.add(new Vehicle("مرسيدس"));
        items.add(new Vehicle("شيفروليه"));
        items.add(new Vehicle("اكسنت"));
        Objects.requireNonNull(vehiclesAdapter.getValue()).setData(items);
    }

    public void onClickClose() {
        doAction.setValue(ACTION_BACK);
    }

    public void onSelectVehicle(int position) {
        Objects.requireNonNull(vehiclesAdapter.getValue()).setSelectedPosition(position);
    }

    public void onClickSave() {
        doAction.setValue(ACTION_GET_VEHICLE);
    }
}
