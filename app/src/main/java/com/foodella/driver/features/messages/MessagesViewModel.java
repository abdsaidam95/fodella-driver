package com.foodella.driver.features.messages;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.chatDetails.adapter.ConversationsAdapter;
import com.foodella.driver.features.messages.adapter.Message;
import com.foodella.driver.features.messages.adapter.MessagesAdapter;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;
import java.util.Objects;

public class MessagesViewModel extends BaseViewModel {

    public MutableLiveData<MessagesAdapter> messagesAdapter = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    private ArrayList<Message> messages = new ArrayList<>();


    public MessagesViewModel() {

        isRefreshing.setValue(false);
        messagesAdapter.setValue(new MessagesAdapter());

        emptyColor.setValue(R.color.colorWhite);
        isEmpty.setValue(messages.isEmpty());
        emptyIcon.setValue(R.drawable.ic_close);
        emptyText.setValue(R.string.no_messages);
    }

    public void onStart() {

    }

    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            isRefreshing.setValue(false);
            isEmpty.setValue(messages.isEmpty());
        }, 2000);
    };



    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }

    public void onClickAddMessage() {
        doAction.setValue(AppAction.ACTION_ADD_NEW_MESSAGE);
    }


    public void setAppendAdapterService(Message message) {
        messages.add(message);
        Objects.requireNonNull(messagesAdapter.getValue()).appendData(messages);
        isEmpty.setValue(false);
    }
}
