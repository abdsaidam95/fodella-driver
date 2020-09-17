package com.foodella.driver.features.orderDetails.fragments.financialDataFragment;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;

import java.util.Objects;

public class FinancialDataViewModel extends BaseViewModel {


    public MutableLiveData<String> delivery = new MutableLiveData<>();
    public MutableLiveData<String> paymentMethod = new MutableLiveData<>();
    public MutableLiveData<String> currency = new MutableLiveData<>();
    public MutableLiveData<String> deliveryCost = new MutableLiveData<>();
    public MutableLiveData<String> orderStatus = new MutableLiveData<>();

    public MutableLiveData<Integer> deliveryLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> paymentMethodLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> currencyLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> deliveryCostLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> orderStatusLabel = new MutableLiveData<>();

    public MutableLiveData<Boolean> isOrderStatus = new MutableLiveData<>();
    public MutableLiveData<Boolean> isDeliveryCost = new MutableLiveData<>();

    public FinancialDataViewModel() {
        isOrderStatus.setValue(false);
        delivery.setValue("على الزبون");
        paymentMethod.setValue("ماستر كارد");
        currency.setValue("ريال سعودي");
        deliveryCost.setValue("50 ريال");

        deliveryLabel.setValue(R.string.in_review);
        paymentMethodLabel.setValue(R.string.payment_method);
        currencyLabel.setValue(R.string.the_currency);
        deliveryCostLabel.setValue(R.string.delivery_cost);
        orderStatusLabel.setValue(R.string.order_status);
        handleOrderStatus();
    }

    private void handleOrderStatus() {

        orderStatus.setValue("Under review");

        if (Objects.requireNonNull(orderStatus.getValue()).equalsIgnoreCase("Under review")) {
            isOrderStatus.setValue(true);
            isDeliveryCost.setValue(false);
        } else if (Objects.requireNonNull(orderStatus.getValue()).equalsIgnoreCase(String.valueOf(R.string.in_review))) {
            isOrderStatus.setValue(false);
            isDeliveryCost.setValue(true);
        } else {
            isOrderStatus.setValue(false);
            isDeliveryCost.setValue(true);
        }
    }

    public void onStart() {
    }
    // TODO: 20-Aug-20 make Financial data
    public void makeFinancialDataRequest(String delivery,String paymentMethod,String currency,String deliveryCost,String orderStatus){

    }

    public void onClickAccept() {
        doAction.setValue(AppAction.ACTION_ACCEPT);
    }

    public void onClickReject() {
        doAction.setValue(AppAction.ACTION_REJECT);
    }
}
