package com.foodella.driver.features.profile;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;

public class ProfileViewModel extends BaseViewModel {

    public MutableLiveData<Integer> currentPage = new MutableLiveData<>();
    public MutableLiveData<Integer> textProfile = new MutableLiveData<>();
    public MutableLiveData<String> pagerCounter = new MutableLiveData<>();


    public ProfileViewModel() {
        currentPage.setValue(0);
        textProfile.setValue(R.string.Profile_privite);
        pagerCounter.setValue("1/4");

    }


    public void onClickArrow() {
                doAction.setValue(AppAction.ACTION_BACK);

    }
    public void positionFragment(String currentFragment, int textProfiles){
        pagerCounter.setValue(currentFragment);
        textProfile.setValue(textProfiles);
    }


}


