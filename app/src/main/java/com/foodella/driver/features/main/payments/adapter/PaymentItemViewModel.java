package com.foodella.driver.features.main.payments.adapter;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

public class PaymentItemViewModel extends BaseViewModel {

    public MutableLiveData<String> orderDate = new MutableLiveData<>();
    public MutableLiveData<String> orderPrice = new MutableLiveData<>();
    public MutableLiveData<String> deliveryPrice = new MutableLiveData<>();
    public MutableLiveData<String> orderStatus = new MutableLiveData<>();
    public Payment payment;

    public PaymentItemViewModel(Payment payment) {
        this.payment = payment;
    }

    public void onStart() {
        payment.setOrderDate("15.08.2020");
        payment.setOrderPrice("500 Real");
        payment.setDeliveryPrice("35 Real");
        payment.setOrderStatus("on do");
        orderDate.setValue(payment.getOrderDate());
        orderPrice.setValue(payment.getOrderPrice());
        deliveryPrice.setValue(payment.getDeliveryPrice());
        orderStatus.setValue(payment.getOrderStatus());
    }

    public void onClickPaymentActivity() {
        EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_GO_ACTIVITY_PAYMENT,payment));
    }
}
