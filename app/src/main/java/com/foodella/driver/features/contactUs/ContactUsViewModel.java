package com.foodella.driver.features.contactUs;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;

public class ContactUsViewModel extends BaseViewModel {


    public MutableLiveData<String> contactTitle = new MutableLiveData<>();
    public MutableLiveData<String> contactDescription = new MutableLiveData<>();

    public ContactUsViewModel() {
    }

    public void onStart() {
    }

    public void onClickSend() {
    }

    public void makeContactUsRequest() {

        String titleValue = contactTitle.getValue();
        String descriptionValue = contactDescription.getValue();

    }

}
