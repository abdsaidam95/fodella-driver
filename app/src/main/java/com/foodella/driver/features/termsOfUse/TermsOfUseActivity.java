package com.foodella.driver.features.termsOfUse;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityTermsOfUseBinding;


public class TermsOfUseActivity extends BaseActivity {

    private TermsOfUseViewModel viewModel;
    private ActivityTermsOfUseBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(TermsOfUseViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_terms_of_use);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(getString(R.string.terms_of_use));

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