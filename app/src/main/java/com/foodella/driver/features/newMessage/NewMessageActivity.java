package com.foodella.driver.features.newMessage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityNewMessageBinding;
import com.foodella.driver.features.messages.adapter.Message;

import java.util.Objects;

import static com.foodella.driver.utils.AppConst.RES_NOT_OK;

public class NewMessageActivity extends BaseActivity {

    private NewMessageViewModel viewModel;
    private ActivityNewMessageBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(NewMessageViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_message);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(getString(R.string.new_message));

        initializeView();
    }

    private void initializeView() {

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_BACK:
                    onBackPressed();
                    break;
                case ACTION_MESSAGES:
                    Message message = viewModel.generateMessage();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("message",message);
                    setResult(RES_NOT_OK,returnIntent);
                    finish();
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

}