package com.foodella.driver.features.addCar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager2.widget.ViewPager2;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.addCar.fragments.AddCarFragment;
import com.foodella.driver.features.addCar.fragments.CarLicenseFragment;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;

public class AddCarActivityViewModel extends BaseViewModel {

    private int position = 0;
    public MutableLiveData<Integer> currentPage = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Fragment>> addCarList = new MutableLiveData<>();
    public MutableLiveData<Integer> textButton = new MutableLiveData<>();

    public ViewPager2.OnPageChangeCallback onPageChangeListener = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int p) {
            position = p;
            handleSelectedPage();
        }
    };


    public AddCarActivityViewModel() {

        textButton.setValue(R.string.next);
        currentPage.setValue(0);

        ArrayList<Fragment> data = new ArrayList<>();
        data.add(new AddCarFragment());
        data.add(new CarLicenseFragment());

        addCarList.setValue(data);
    }


    public void onStart() {
    }

    public void handleSelectedPage() {
        switch (position) {
            case 0:
                textButton.setValue(R.string.next);
                break;
            case 1:
                textButton.setValue(R.string.save);
                break;
        }
    }

    public void onClickNext() {
        if (position == 0) {
            currentPage.setValue(1);
        }
        handleSelectedPage();
    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }




}
