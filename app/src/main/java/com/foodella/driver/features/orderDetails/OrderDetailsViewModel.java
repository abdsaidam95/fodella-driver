package com.foodella.driver.features.orderDetails;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class OrderDetailsViewModel extends BaseViewModel {

    public MutableLiveData<Integer> checkedButton = new MutableLiveData<>();
    public MutableLiveData<Boolean> isOrderDataClicked = new MutableLiveData<>();
    public MutableLiveData<Boolean> isAddressClicked = new MutableLiveData<>();
    public MutableLiveData<Boolean> isFinancialDataClicked = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRatingClicked = new MutableLiveData<>();

    public OrderDetailsViewModel() {
        checkedButton.setValue(R.id.order_data_btn);
        isOrderDataClicked.setValue(true);
    }

    public MaterialButtonToggleGroup.OnButtonCheckedListener mListener = (group, checkedId, isChecked) -> {
        if (isChecked) {
            switch (checkedId) {
                case R.id.order_data_btn:
                    doAction.setValue(AppAction.ACTION_ORDER_DATA);
                    break;
                case R.id.address_and_contact_btn:
                    doAction.setValue(AppAction.ACTION_ADDRESS);
                    break;
                case R.id.financial_data_btn:
                    doAction.setValue(AppAction.ACTION_FINANCIAL_DATA);
                    break;
                case R.id.rating_btn:
                    doAction.setValue(AppAction.ACTION_RATING);
                    break;
            }
        } else {
            if (group.getCheckedButtonId() == -1) {
                doAction.setValue(AppAction.ACTION_NO_SELECTED_BUTTON);
                checkedButton.setValue(R.id.order_data_btn);
                isOrderDataClicked.setValue(true);
            }
        }
    };


    public void onStart() {
    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }


}
