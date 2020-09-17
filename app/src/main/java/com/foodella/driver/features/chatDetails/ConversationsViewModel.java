package com.foodella.driver.features.chatDetails;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.chatDetails.adapter.Conversation;
import com.foodella.driver.features.chatDetails.adapter.ConversationsAdapter;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;
import java.util.Objects;

public class ConversationsViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> message = new MutableLiveData<>();

    public MutableLiveData<ConversationsAdapter> conversationsAdapter = new MutableLiveData<>();

    private ArrayList<Conversation> messages = new ArrayList<>();

    public ConversationsViewModel() {
        emptyColor.setValue(R.color.colorWhite);
        isRefreshing.setValue(false);
        conversationsAdapter.setValue(new ConversationsAdapter());
        name.setValue("عبد الرحمن أسامة");

        setData();
        isEmpty.setValue(messages.isEmpty());
        emptyIcon.setValue(R.drawable.ic_close);
        emptyText.setValue(R.string.no_conversations_available);
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


    private void setData() {
        messages.clear();
        messages.add(new Conversation(message.getValue(), Conversation.SENT));
        Objects.requireNonNull(conversationsAdapter.getValue()).appendData(messages);
    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }

    public void onClickSendMessage() {
        if (message.getValue() !=null) {
            setData();
            message.setValue("");
        } else {
            showMessageRes.setValue(R.string.you_cannot_sent_empty);
        }
    }

}
