package com.foodella.driver.bottomSheets.selectMultiCity.adapter;

import androidx.collection.ArrayMap;
import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class CityItemViewModel extends BaseViewModel {

    private City city;
    private int position;

    public MutableLiveData<String> cityName = new MutableLiveData<>();
    public MutableLiveData<Integer> icon = new MutableLiveData<>();

    public CityItemViewModel(City city, int layoutPosition) {
        this.city = city;
        this.position = layoutPosition;
    }

    public void onStart(ArrayMap<Integer, Boolean> selectedPositions) {
        if (city != null) {
            cityName.setValue(city.getCityName());

            boolean isSelected = (selectedPositions.containsKey(position));

            if (isSelected)
                icon.setValue(R.drawable.ic_check_box_24);
            else
                icon.setValue(R.drawable.ic_check_box_blank_24);
        }
    }

    public void onClickItem() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_SELECT_CITY, city, position));
    }
}
