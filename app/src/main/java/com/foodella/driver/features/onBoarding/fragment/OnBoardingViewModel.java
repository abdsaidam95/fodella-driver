package com.foodella.driver.features.onBoarding.fragment;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;

public class OnBoardingViewModel extends BaseViewModel {

    public MutableLiveData<Integer> image = new MutableLiveData<>();
    public MutableLiveData<Integer> title = new MutableLiveData<>();

    public OnBoardingViewModel() {
        image.setValue(R.drawable.img_on_boarding_1);
        title.setValue(R.string.on_boarding_1);
    }

    public void onStart(OnBoarding onBoarding) {
        image.setValue(onBoarding.getImage());
        title.setValue(onBoarding.getTitle());
    }
}