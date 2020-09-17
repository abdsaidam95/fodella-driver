package com.foodella.driver.features.aboutApp;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;

public class AboutAppViewModel extends BaseViewModel {

    public MutableLiveData<String> aboutApp = new MutableLiveData<>();

    public AboutAppViewModel() {
    }

    public void onStart() {
    }

    public void makeAboutAppRequest() {
        String aboutAppValue = aboutApp.getValue();

    }

}
