package com.foodella.driver.bottomSheets.forgetPasswordBottomSheet;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import static com.foodella.driver.utils.AppAction.ACTION_BACK_LOGIN;

public class ForgetPasswordViewModel extends BaseViewModel {

    public MutableLiveData<String> phoneData = new MutableLiveData<>();

    public MutableLiveData<Integer> phoneError = new MutableLiveData<>();
    public TextChange phoneTextChange = value -> phoneError.setValue(0);



    public ForgetPasswordViewModel() {
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart(){

    }
    public void onClickDismissDialog() {
        doAction.setValue(ACTION_BACK_LOGIN);
    }

    public void onClickCountryPhone() {

    }


    public void makeForgetPasswordRequest() {
        String phone = phoneData.getValue();
        boolean v1 = validationPhone();

        if (v1) {
            doAction.setValue(AppAction.ACTION_SHOW_CONFIRM_ACCOUNT);
        }
    }

    private boolean validationPhone() {
        if (TextUtils.isEmpty(phoneData.getValue())) {
            phoneError.setValue((R.string.please_enter_phone_number));
            return false;
        } else {
            phoneError.setValue(0);
            return true;
        }
    }


}
