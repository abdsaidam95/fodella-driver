package com.foodella.driver.features.profile.cars;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.features.main.cars.adapter.VehiclesAdapter;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Objects;

public class CarsListViewModel extends BaseViewModel {

    public MutableLiveData<VehiclesAdapter> adapter = new MutableLiveData<>();
    private ArrayList<Vehicle> items = new ArrayList<>();

    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    private Vehicle vehicle;

    public CarsListViewModel() {
        adapter.setValue(new VehiclesAdapter());
        emptyColor.setValue(R.color.colorRed);

        if (items.isEmpty()) {
            isRefreshing.setValue(false);
            isEmpty.setValue(true);
            emptyIcon.setValue(R.drawable.ic_nodata);
            emptyText.setValue(R.string.no_notification);
        } else {
            isEmpty.setValue(false);
            getVehicles();
        }

    }

    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            isRefreshing.setValue(false);
            if (items.isEmpty()) {
                isEmpty.setValue(true);
                emptyIcon.setValue(R.drawable.ic_nodata);
                emptyText.setValue(R.string.no_notification);
            } else {
                isEmpty.setValue(false);
            }
        }, 2000);
    };

    private void getVehicles() {
    }

    public void onStart(Vehicle vehicle) {
        this.vehicle = vehicle;
        setVehicles(vehicle);
    }

    public void setVehicles(Vehicle vehicle) {
        items.clear();
        items.add(vehicle);
        Objects.requireNonNull(adapter.getValue()).appendData(items);
        isEmpty.setValue(false);
    }

    public void onClickNext() {
        doAction.setValue(AppAction.ACTION_GO_MAIN);
    }

    public void addCarList() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_CAR_LIST));
    }
}
