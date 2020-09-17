package com.foodella.driver.features.newMessage;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.messages.adapter.Message;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import static com.foodella.driver.utils.AppAction.ACTION_MESSAGES;

public class NewMessageViewModel extends BaseViewModel {

    public MutableLiveData<String> messageBody = new MutableLiveData<>();
    public MutableLiveData<String> messageTitle = new MutableLiveData<>();
    public MutableLiveData<Integer> visabilityErrorTitle = new MutableLiveData<>();
    public MutableLiveData<Integer> visabilityErrorMessage = new MutableLiveData<>();
    private Message message;

    public NewMessageViewModel() {
        visabilityErrorMessage.setValue(View.GONE);
        visabilityErrorTitle.setValue(View.GONE);
    }

    public void onStart() {
    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }

    public void onClickSendMessage() {
        if (messageTitle == null || TextUtils.isEmpty(messageTitle.getValue())) {
            visabilityErrorTitle.setValue(View.VISIBLE);
        } else {
            visabilityErrorTitle.setValue(View.GONE);
        }
        if (messageBody == null || TextUtils.isEmpty(messageBody.getValue())) {
            visabilityErrorMessage.setValue(View.VISIBLE);
        } else {
            visabilityErrorMessage.setValue(View.GONE);
        }
        if (messageTitle.getValue()!=null && messageBody.getValue()!=null){
           // EventBus.getDefault().post(new ActionEvent(ACTION_MESSAGES, generateMessage()));
            doAction.setValue( AppAction.ACTION_MESSAGES);

        }
    }

    public void makeNewMessageRequest() {
        String messageBodyValue = messageBody.getValue();
        String titleValue = messageTitle.getValue();

    }

    public Message generateMessage( ) {
        message = new Message();
        message.setMessageTitle(messageTitle.getValue());
        message.setMessageBody(messageBody.getValue());
        return message;
    }

}
