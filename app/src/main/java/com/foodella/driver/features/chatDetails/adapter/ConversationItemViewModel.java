package com.foodella.driver.features.chatDetails.adapter;

import android.text.format.DateFormat;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;

import java.util.Calendar;
import java.util.Locale;


public class ConversationItemViewModel extends BaseViewModel {

    public Conversation conversation;
    public MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<String> dateTime = new MutableLiveData<>();
    public MutableLiveData<String> image = new MutableLiveData<>();

    public ConversationItemViewModel(Conversation conversation) {
        this.conversation = conversation;
    }

    public void onStart() {
        message.setValue(conversation.getMessage());
        dateTime.setValue(conversation.getTimestamp());
        image.setValue(conversation.getImage());
    }

    public void onClickItem() {
    }

     public String parceFormatTime(){
             String timesTemp = conversation.getTimestamp();
             Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
             calendar.setTimeInMillis(Long.parseLong(timesTemp));
             String dateTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
             return dateTime;
     }
}
