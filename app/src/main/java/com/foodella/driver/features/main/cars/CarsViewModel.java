package com.foodella.driver.features.main.cars;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.features.main.cars.adapter.VehiclesAdapter;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;
import java.util.Objects;

public class CarsViewModel extends BaseViewModel {

    public MutableLiveData<VehiclesAdapter> adapter = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();

    public ArrayList<Vehicle> items = new ArrayList<>();
    private Vehicle vehicle;


    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            isRefreshing.setValue(false);
            isEmpty.setValue(items.isEmpty());
            emptyIcon.setValue(R.drawable.ic_close);
            emptyText.setValue(R.string.car_empty);
        }, 2000);
    };


    public CarsViewModel() {
        adapter.setValue(new VehiclesAdapter());
        emptyColor.setValue(R.color.colorWhite);
        isRefreshing.setValue(false);

        if (items.isEmpty()) {
            isEmpty.setValue(true);
            emptyIcon.setValue(R.drawable.ic_close);
            emptyText.setValue(R.string.car_empty);
        } else {
            isEmpty.setValue(false);
        }

    }

    public void onStart(Vehicle vehicle) {
        this.vehicle = vehicle;
        addVehicle(vehicle);
    }

    public void addVehicle(Vehicle vehicle) {
        items.clear();
        items.add(vehicle);
        Objects.requireNonNull(adapter.getValue()).appendData(items);
        isEmpty.setValue(false);
    }

    public void setVehicles() {
        items.add(new Vehicle("شيفروليه اوكتافيا سنتافيه", "1230456789", "2019", "status"));
        items.add(new Vehicle("شيفروليه اوكتافيا سنتافيه", "1230456789", "2019", "status"));
        items.add(new Vehicle("شيفروليه اوكتافيا سنتافيه", "1230456789", "2019", "status"));
        items.add(new Vehicle("شيفروليه اوكتافيا سنتافيه", "1230456789", "2019", "status"));
        items.add(new Vehicle("شيفروليه اوكتافيا سنتافيه", "1230456789", "2019", "status"));

        Objects.requireNonNull(adapter.getValue()).setData(items);
        isEmpty.setValue(false);

    }

    public void addNewCar() {
        doAction.setValue(AppAction.ACTION_ADD_NEW_CAR);
    }

    public void makeGetVehiclesRequest() {

    }

}