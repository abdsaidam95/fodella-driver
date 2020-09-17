package com.foodella.driver.bottomSheets.addPaymentMethods;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.paymentMethods.adapter.PaymentMethod;
import com.foodella.driver.utils.AppAction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class AddPaymentMethodsViewModel extends BaseViewModel {

    public MutableLiveData<String> paymentName = new MutableLiveData<>();
    public MutableLiveData<String> cardNo = new MutableLiveData<>();
    public MutableLiveData<String> expirationDate = new MutableLiveData<>();
    public MutableLiveData<String> securityCode = new MutableLiveData<>();
    public MutableLiveData<String> holderName = new MutableLiveData<>();
    public PaymentMethod paymentMethod;

    public MutableLiveData<Integer> paymentNameError = new MutableLiveData<>();
    public MutableLiveData<Integer> paymentCardNoError = new MutableLiveData<>();
    public MutableLiveData<Integer> paymentExpirationDateError = new MutableLiveData<>();
    public MutableLiveData<Integer> paymentSecurityCodeError = new MutableLiveData<>();
    public MutableLiveData<Integer> paymentHolderNameError = new MutableLiveData<>();



    public AddPaymentMethodsViewModel() {
        paymentNameError.setValue(0);
        paymentCardNoError.setValue(0);
        paymentExpirationDateError.setValue(0);
        paymentSecurityCodeError.setValue(0);
        paymentHolderNameError.setValue(0);

        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart() {
        //generatePaymentMethod();
    }


    public void onClickAdd() {
        if (validationData())
            doAction.setValue(AppAction.ACTION_PAYMENT_METHODS);
    }

    public PaymentMethod generatePaymentMethod() {
        paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentName(paymentName.getValue());
        paymentMethod.setPaymentNumber(cardNo.getValue());
        paymentMethod.setPaymentDate(expirationDate.getValue());

        securityCode.getValue();
        holderName.getValue();

        return paymentMethod;
    }

    private boolean validationData() {
        if (TextUtils.isEmpty(paymentName.getValue())) {
            paymentNameError.setValue((R.string.please_enter_phone_number));
            return false;
        } else if (TextUtils.isEmpty(cardNo.getValue())) {
            paymentCardNoError.setValue((R.string.please_enter_phone_number));
            return false;
        } else if (TextUtils.isEmpty(expirationDate.getValue())) {
            paymentExpirationDateError.setValue((R.string.please_enter_phone_number));
            return false;
        } else if (TextUtils.isEmpty(securityCode.getValue())) {
            paymentSecurityCodeError.setValue((R.string.please_enter_phone_number));
            return false;
        } else if (TextUtils.isEmpty(holderName.getValue())) {
            paymentHolderNameError.setValue((R.string.please_enter_phone_number));
            return false;
        } else {
            paymentNameError.setValue(0);
            paymentCardNoError.setValue(0);
            paymentExpirationDateError.setValue(0);
            paymentSecurityCodeError.setValue(0);
            paymentHolderNameError.setValue(0);
            return true;
        }
    }

    public void onClickClose() {
        doAction.setValue(AppAction.ACTION_CLOSE);
    }


}
