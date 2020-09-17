package com.foodella.driver.features.privacyPolicy;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityPrivacyPolicyBinding;

public class PrivacyPolicyActivity extends BaseActivity {

    private PrivacyPolicyViewModel viewModel;
    private ActivityPrivacyPolicyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(PrivacyPolicyViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_privacy_policy);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(getString(R.string.privacy_policy));

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