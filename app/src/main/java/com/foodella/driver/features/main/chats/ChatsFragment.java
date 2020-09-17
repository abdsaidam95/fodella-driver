package com.foodella.driver.features.main.chats;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.FragmentChatsBinding;
import com.foodella.driver.features.chatDetails.ConversationActivity;
import com.foodella.driver.features.chatDetails.adapter.Conversation;
import com.foodella.driver.features.main.chats.adapter.Chat;
import com.foodella.driver.features.main.home.HomeFragment;
import com.foodella.driver.features.messages.adapter.Message;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

public class ChatsFragment extends BaseFragment {

    private FragmentChatsBinding binding;
    private ChatsViewModel viewModel;

    public static ChatsFragment newInstance() {
        return new ChatsFragment();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatsBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(ChatsViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        initializeView();

        return binding.getRoot();
    }


    private void initializeView() {
        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_CHAT_ITEM:
                    break;
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onActionEvent(ActionEvent actionEvent) {
        if (actionEvent.getActions() == AppAction.ACTION_CHAT_ITEM) {
            Object data = actionEvent.getData();
            if (data != null && data instanceof Chat) {
                Intent intent = new Intent();
                intent.putExtra("name", ((Chat) data).getUserName());
                ((BaseActivity) requireActivity()).startNewActivity(ConversationActivity.class, intent, false);
            }
        }
    }



}