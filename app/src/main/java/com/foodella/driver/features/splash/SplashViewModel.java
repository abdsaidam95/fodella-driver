package com.foodella.driver.features.splash;

import android.os.Handler;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;

public class SplashViewModel extends BaseViewModel {

    private static final int SPLASH_TIME_OUT = 2000;

    private Handler handler = new Handler();
    private Runnable mRunnable = () -> doAction.setValue(AppAction.ACTION_ON_BOARDING);

    public void onStart() {
        handler.postDelayed(mRunnable, SPLASH_TIME_OUT);

    }

    public void onDestroy() {
        handler.removeCallbacks(mRunnable);
    }

}
