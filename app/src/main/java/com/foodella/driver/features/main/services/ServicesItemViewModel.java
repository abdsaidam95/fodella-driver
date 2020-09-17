package com.foodella.driver.features.main.services;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.payments.adapter.Payment;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import static com.foodella.driver.utils.AppAction.DELETE_ITEM;
import static com.foodella.driver.utils.AppAction.GO_TO_SERVICE_ACTIVITY;

public class ServicesItemViewModel extends BaseViewModel {
    public Services services;
    public MutableLiveData<String> cityFrom = new MutableLiveData<>();
    public MutableLiveData<String> cityTo = new MutableLiveData<>();
    public MutableLiveData<String> price = new MutableLiveData<>();
    public MutableLiveData<String> maximumWeight = new MutableLiveData<>();
    public MutableLiveData<String> pricePerkilometer = new MutableLiveData<>();
    private int position;

    public ServicesItemViewModel(Services services, int position) {
        this.services = services;
        this.position = position;
    }

    public void onStart() {
        cityFrom.setValue(services.getCityFrom());
        cityTo.setValue(services.getCityTo());
        price.setValue(services.getCrumbprice());
        maximumWeight.setValue(services.getMaximumWeight());
        pricePerkilometer.setValue(services.getPricePerKilomitars());
    }

    public void deleteItem() {
        EventBus.getDefault().post(new ActionEvent(DELETE_ITEM, position));

    }
    public void onClick() {
        EventBus.getDefault().post(new ActionEvent(GO_TO_SERVICE_ACTIVITY,services,position));

    }
}
