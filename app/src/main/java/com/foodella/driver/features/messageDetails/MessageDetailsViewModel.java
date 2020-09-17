package com.foodella.driver.features.messageDetails;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.messages.adapter.Message;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;

public class MessageDetailsViewModel extends BaseViewModel {

    public MutableLiveData<String> messageBody = new MutableLiveData<>();
    public MutableLiveData<String> messageDate = new MutableLiveData<>();
    public MutableLiveData<String> messageTitle = new MutableLiveData<>();

    public MessageDetailsViewModel() {
    }

    public void onStart() {
    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }



}
