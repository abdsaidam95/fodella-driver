package com.foodella.driver.bottomSheets.insuranceType.adapter;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

public class InsuranceItemViewModel extends BaseViewModel {

    private InsuranceType insuranceType;
    public int positionClick;
    private int getPositionClick;

    public MutableLiveData<String> insuranceName = new MutableLiveData<>();
    public MutableLiveData<Integer> icon = new MutableLiveData<>();

    public InsuranceItemViewModel(InsuranceType vehicle, int position) {
        this.insuranceType = vehicle;
        this.positionClick = position;
    }

    public void onStart(int position) {
        this.getPositionClick = position;
        insuranceName.setValue(insuranceType.getInsuranceName());
        boolean isSelected = (position == positionClick);
        if (isSelected) {
            icon.setValue(R.drawable.ic_group_bottom_cheaked);
        } else {
            icon.setValue(R.drawable.ic_ellipsed_radio_button);
        }
    }

    public void onClickItem() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_INSURANCE_TYPE_ITEM, insuranceType, positionClick));
    }

}


