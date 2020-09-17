package com.foodella.driver.features.main.chats.adapter;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.nav.Menu;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

public class ChatItemViewModel extends BaseViewModel {

    private Chat chat;
    private int position;


    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> userImage = new MutableLiveData<>();
    public MutableLiveData<String> dateTime = new MutableLiveData<>();

    public ChatItemViewModel(Chat chat) {
        this.chat = chat;
    }

    public void onStart() {
        userName.setValue(chat.getUserName());
        userImage.setValue(chat.getUserImage());
        dateTime.setValue(chat.getDateTime());
    }

    public void onClickItem() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_CHAT_ITEM, chat, position));
    }
}
