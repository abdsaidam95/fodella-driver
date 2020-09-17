package com.foodella.driver.bottomSheets.selectCity.adapter;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

public class SingleCityItemViewModel extends BaseViewModel {

    private City city;
    public int positionClick;
    private int getPositionClick;

    public MutableLiveData<String> cityName = new MutableLiveData<>();
    public MutableLiveData<Integer> icon = new MutableLiveData<>();

    public SingleCityItemViewModel(City city, int position) {
        this.city = city;
        this.positionClick = position;
    }

    public void onStart(int position) {
        getPositionClick = position;
        cityName.setValue(city.getCityName());
        boolean isSelected = (position == positionClick);
        if (isSelected)
            icon.setValue(R.drawable.ic_group_bottom_cheaked);
        else
            icon.setValue(R.drawable.ic_ellipsed_radio_button);
    }

    public void onClickItem() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_SELECT_SINGLE_CITY, city, positionClick));
    }

}