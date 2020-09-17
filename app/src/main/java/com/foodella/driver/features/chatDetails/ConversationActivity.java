package com.foodella.driver.features.chatDetails;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityChatDetailsBinding;

import java.util.Objects;

public class ConversationActivity extends BaseActivity {

    private ConversationsViewModel viewModel;
    private ActivityChatDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(ConversationsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_details);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        String name = getIntent().getStringExtra("name");
        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(name);

        initializeView();
    }

    private void initializeView() {
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