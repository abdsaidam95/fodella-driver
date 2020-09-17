package com.foodella.driver.features.paymentMethods.adapter;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

public class PaymentMethodItemViewModel extends BaseViewModel {

    private PaymentMethod payment;
    private int positionAdapter;

    public MutableLiveData<String> paymentName = new MutableLiveData<>();
    public MutableLiveData<String> paymentDate = new MutableLiveData<>();
    public MutableLiveData<String> paymentImage = new MutableLiveData<>();
    public MutableLiveData<String> paymentNumber = new MutableLiveData<>();

    public MutableLiveData<Boolean> paymentSelection = new MutableLiveData<>();
    public MutableLiveData<Integer> icon = new MutableLiveData<>();

    public PaymentMethodItemViewModel(PaymentMethod payment, int position) {
        this.payment = payment;
        this.positionAdapter = position;
    }

    public void onStart(int position) {

        paymentName.setValue(payment.getPaymentName());
        paymentDate.setValue(payment.getPaymentDate());
        paymentImage.setValue(payment.getPaymentImage());
        paymentNumber.setValue(payment.getPaymentNumber());
        paymentSelection.setValue(payment.getPaymentSelection());

        boolean isSelected = (position == positionAdapter);
        if (isSelected) {
            icon.setValue(R.drawable.ic_group_bottom_cheaked);
        } else {
            icon.setValue(R.drawable.ic_ellipsed_radio_button);
        }

    }

    public void onClickItem() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_PAYMENT_ITEM, payment, positionAdapter));
    }

    public void onClickItemRemove() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_PAYMENT_ITEM_REMOVE, payment, positionAdapter));
    }
}
