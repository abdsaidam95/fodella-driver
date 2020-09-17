package com.foodella.driver.features.splash;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivitySplashBinding;
import com.foodella.driver.features.login.LoginActivity;
import com.foodella.driver.features.onBoarding.OnBoardingActivity;
import com.foodella.driver.utils.AppAction;

public class SplashActivity extends BaseActivity {

    private SplashViewModel viewModel;
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();

        viewModel = new ViewModelProvider(this, factory).get(SplashViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        viewModel.doAction.observe(this, action -> {
            if (action == AppAction.ACTION_ON_BOARDING) {
                startNewActivity(OnBoardingActivity.class);
            }
        });

    }

    @Override
    protected void onDestroy() {
        viewModel.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}