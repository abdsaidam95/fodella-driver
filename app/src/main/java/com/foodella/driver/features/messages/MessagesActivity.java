package com.foodella.driver.features.messages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityMessagesBinding;
import com.foodella.driver.features.addService.AddServiceActivity;
import com.foodella.driver.features.main.services.Services;
import com.foodella.driver.features.messageDetails.MessageDetailsActivity;
import com.foodella.driver.features.messages.adapter.Message;
import com.foodella.driver.features.newMessage.NewMessageActivity;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

public class MessagesActivity extends BaseActivity {

    private MessagesViewModel viewModel;
    private ActivityMessagesBinding binding;
   public Message message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(MessagesViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_messages);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(getString(R.string.messages));

        initializeView();
    }

    private void initializeView() {

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_ADD_NEW_MESSAGE:
                    Intent intent = new Intent(MessagesActivity.this, NewMessageActivity.class);
                    startActivityForResult(intent , 200);
                  //  startNewActivity(NewMessageActivity.class);
                    break;
                case ACTION_BACK:
                    onBackPressed();
                    break;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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
        if (actionEvent.getActions() == AppAction.ACTION_MESSAGE_ITEM) {
            Object data = actionEvent.getData();
            if (data != null && data instanceof Message) {
                Intent intent = new Intent();
                intent.putExtra("messageTitle", ((Message) data).getMessageTitle());
                intent.putExtra("messageDate", ((Message) data).getMessageDate());
                intent.putExtra("massage",((Message) data).getMessageBody());
                startNewActivity(MessageDetailsActivity.class, intent);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == 200 && data!=null) {
             message=(Message)data.getExtras().getSerializable("message");
            viewModel.setAppendAdapterService(message);



        }


        }


    }

