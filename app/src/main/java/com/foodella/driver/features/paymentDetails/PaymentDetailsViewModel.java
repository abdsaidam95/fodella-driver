package com.foodella.driver.features.paymentDetails;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.payments.adapter.Payment;

public class PaymentDetailsViewModel extends BaseViewModel {

    public MutableLiveData<String> orderDate = new MutableLiveData<>();
    public MutableLiveData<String> nameRestaurant = new MutableLiveData<>();
    public MutableLiveData<String> nameSalesManager = new MutableLiveData<>();
    public MutableLiveData<String> paymentMethod = new MutableLiveData<>();
    public MutableLiveData<String> houseName = new MutableLiveData<>();
    public MutableLiveData<String> consigneeName = new MutableLiveData<>();
    public MutableLiveData<String> paymentMethodconsignee = new MutableLiveData<>();
    public MutableLiveData<String> orderPrice = new MutableLiveData<>();
    public MutableLiveData<String> deliveryPrice = new MutableLiveData<>();
    public MutableLiveData<String> orderStatus = new MutableLiveData<>();
    public MutableLiveData<String> deliveryCommission = new MutableLiveData<>();


    public PaymentDetailsViewModel() {
        nameRestaurant.setValue("resturant");
        nameSalesManager.setValue("resturant");
        paymentMethod.setValue("resturant");
        houseName.setValue("resturant");
        consigneeName.setValue("resturant");
        paymentMethodconsignee.setValue("resturant");
        orderPrice.setValue("resturant");
        deliveryPrice.setValue("resturant");
        orderStatus.setValue("resturant");
        deliveryCommission.setValue("resturant");
    }

    // TODO: 20-Aug-20 make payment details request
    public void makePaymentDetails(String orderDate, String nameRestaurant, String nameSalesManager,
                                   String paymentMethod, String houseName, String consigneeName, String paymentMethodconsignee,
                                   String orderPrice, String deliveryPrice, String orderStatus, String deliveryCommission) {

    }


    public void onStart() {
    }

    public void setPaymentData(Payment payment) {
        orderDate.setValue(payment.getOrderDate());
        orderPrice.setValue(payment.getOrderPrice());
        deliveryPrice.setValue(payment.getDeliveryPrice());
        orderStatus.setValue(payment.getOrderStatus());


    }
}
