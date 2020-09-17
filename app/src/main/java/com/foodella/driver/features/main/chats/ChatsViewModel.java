package com.foodella.driver.features.main.chats;


import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.chats.adapter.Chat;
import com.foodella.driver.features.main.chats.adapter.ChatsAdapter;
import com.foodella.driver.features.messages.adapter.MessagesAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ChatsViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> isPhotosEmpty = new MutableLiveData<>();
    public MutableLiveData<ChatsAdapter> chatsAdapter = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();

    private ArrayList<Chat> chats = new ArrayList<>();


    public ChatsViewModel() {

        isRefreshing.setValue(false);
        chatsAdapter.setValue(new ChatsAdapter());
        emptyColor.setValue(R.color.colorWhite);
        setData();
        isEmpty.setValue(chats.isEmpty());
        emptyIcon.setValue(R.drawable.ic_close);
        emptyText.setValue(R.string.no_chats);
    }

    public void onStart() {

    }

    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            isRefreshing.setValue(false);
            isEmpty.setValue(chats.isEmpty());
        }, 2000);
    };

    private void setData() {
        chats.add(new Chat("عبد الرحمن أسامة", "", "الاثنين 12.07.2019"));
        chats.add(new Chat("عبد الرحمن أسامة", "", "الاثنين 12.07.2019"));
        chats.add(new Chat("عبد الرحمن أسامة", "", "الاثنين 12.07.2019"));
        chats.add(new Chat("عبد الرحمن أسامة", "", "الاثنين 12.07.2019"));
        chats.add(new Chat("عبد الرحمن أسامة", "", "الاثنين 12.07.2019"));
        chats.add(new Chat("عبد الرحمن أسامة", "", "الاثنين 12.07.2019"));
        chats.add(new Chat("عبد الرحمن أسامة", "", "الاثنين 12.07.2019"));
        Objects.requireNonNull(chatsAdapter.getValue()).setData(chats);
        isEmpty.setValue(false);
    }

}