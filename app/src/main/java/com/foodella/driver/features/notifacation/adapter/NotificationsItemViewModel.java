package com.foodella.driver.features.notifacation.adapter;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;

public class NotificationsItemViewModel extends BaseViewModel {

    public NotificationsItemViewModel(Notification notifications) {
        this.notifications = notifications;
    }

    public Notification notifications;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    public MutableLiveData<String> date = new MutableLiveData<>();

    public void onStart() {
        name.setValue(notifications.getName());
        description.setValue(notifications.getDescription());
        date.setValue(notifications.getDate());
    }

}