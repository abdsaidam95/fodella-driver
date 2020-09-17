package com.foodella.driver.features.messageDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityMessageDetailsBinding;
import com.foodella.driver.features.messages.adapter.Message;
import com.foodella.driver.features.paymentMethods.PaymentMethodsViewModel;

import java.util.Objects;

public class MessageDetailsActivity extends BaseActivity {

    private MessageDetailsViewModel viewModel;
    private ActivityMessageDetailsBinding binding;
    String messageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(MessageDetailsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message_details);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();
        initializeView();


        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(messageTitle);

    }

    private void initializeView() {

         messageTitle = getIntent().getStringExtra("messageTitle");
        String messageDate = getIntent().getStringExtra("messageDate");
        String message = getIntent().getStringExtra("massage");

        viewModel.messageTitle.setValue(messageTitle);
        viewModel.messageDate.setValue(messageDate);
        assert message != null;
        viewModel.messageBody.setValue(message);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
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



}