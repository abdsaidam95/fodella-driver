package com.foodella.driver.features.messages.adapter;

import android.text.format.DateFormat;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MessageItemViewModel extends BaseViewModel {

    private Message message;
    private int position;

    public MutableLiveData<String> messageTitle = new MutableLiveData<>();
    public MutableLiveData<String> messageDate = new MutableLiveData<>();

    public MessageItemViewModel(Message message) {
        this.message = message;
    }

    public void onStart() {
        messageTitle.setValue(message.getMessageTitle());
        messageDate.setValue(setDate());

    }

    public void onClickItem() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_MESSAGE_ITEM, message, position));
    }
    public String setDate(){
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(" dd,MMM MM, yyyy h:mm a");
        String dateString = sdf.format(date);
        message.setMessageDate(dateString);
        return message.getMessageDate();
    }
}
