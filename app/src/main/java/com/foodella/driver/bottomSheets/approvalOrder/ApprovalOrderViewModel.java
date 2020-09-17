package com.foodella.driver.bottomSheets.approvalOrder;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class ApprovalOrderViewModel extends BaseViewModel {

    public MutableLiveData<String> deliveryPrice = new MutableLiveData<>();
    public MutableLiveData<Integer> deliveryPriceError = new MutableLiveData<>();


    public ApprovalOrderViewModel() {
        deliveryPriceError.setValue(0);
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }


    public void onStart() {
    }

    private boolean validationPrice() {
        if (TextUtils.isEmpty(deliveryPrice.getValue())) {
            deliveryPriceError.setValue((R.string.please_enter_price));
            return false;
        } else {
            deliveryPriceError.setValue(0);
            return true;
        }
    }


    public void onClickClose() {
        doAction.setValue(AppAction.ACTION_CLOSE);
    }

    // TODO: 20-Aug-20 make price approval request
    public void makePriceApprovalRequest() {

        String price = deliveryPrice.getValue();

        boolean v1 = validationPrice();
        if (v1) {
            showMessage.setValue((R.string.validation_test) + price );
        }

    }


}
