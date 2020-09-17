package com.foodella.driver.bottomSheets.cancelOrder;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class CancelOrderViewModel extends BaseViewModel {


    public CancelOrderViewModel() {
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart() {
    }


    public void onClickReject() {
        doAction.setValue(AppAction.ACTION_REJECT);
    }

    public void makeRejectOrderRequest() {

    }

    public void onClickClose() {
        doAction.setValue(AppAction.ACTION_BACK);
    }


}
