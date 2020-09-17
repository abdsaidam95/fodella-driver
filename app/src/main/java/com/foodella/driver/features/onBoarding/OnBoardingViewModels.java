package com.foodella.driver.features.onBoarding;

import androidx.lifecycle.MutableLiveData;
import androidx.viewpager.widget.ViewPager;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.onBoarding.fragment.OnBoarding;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;

public class OnBoardingViewModels extends BaseViewModel {

    private int position = 0;

    public MutableLiveData<Integer> currentPage = new MutableLiveData<>();
    public MutableLiveData<Integer> textButton = new MutableLiveData<>();
    public MutableLiveData<ArrayList<OnBoarding>> onBoardingArrayList = new MutableLiveData<>();


    public ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int p) {
            position = p;
            handleSelectedPage();
        }
    };

    public OnBoardingViewModels() {
        currentPage.setValue(0);
        textButton.setValue(R.string.next);

        ArrayList<OnBoarding> list = new ArrayList<>();

        list.add(new OnBoarding(R.drawable.img_on_boarding_1, R.string.on_boarding_1));
        list.add(new OnBoarding(R.drawable.img_on_boarding_2, R.string.on_boarding_2));
        list.add(new OnBoarding(R.drawable.img_on_boarding_3, R.string.on_boarding_3));

        onBoardingArrayList.setValue(list);
    }

    public void onClickButton() {
        switch (position) {
            case 0:
                currentPage.setValue(1);
                break;
            case 1:
                currentPage.setValue(2);
                break;
            case 2:
                doAction.setValue(AppAction.ACTION_LANGUAGE_DIALOG);
                break;
        }

        handleSelectedPage();
    }

    public void onClickSkip() {
        doAction.setValue(AppAction.ACTION_LANGUAGE_DIALOG);
    }

    private void handleSelectedPage() {
        switch (position) {
            case 0:
            case 1:
                textButton.setValue(R.string.next);
                break;
            case 2:
                textButton.setValue(R.string.start);
                break;
        }
    }

}