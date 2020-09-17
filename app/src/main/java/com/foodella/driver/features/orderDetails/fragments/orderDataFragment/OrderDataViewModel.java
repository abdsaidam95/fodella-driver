package com.foodella.driver.features.orderDetails.fragments.orderDataFragment;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;

public class OrderDataViewModel extends BaseViewModel {

    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<String> time = new MutableLiveData<>();
    public MutableLiveData<String> deliveryTime = new MutableLiveData<>();
    public MutableLiveData<String> orderType = new MutableLiveData<>();
    public MutableLiveData<String> packagingType = new MutableLiveData<>();
    public MutableLiveData<String> orderWeight = new MutableLiveData<>();
    public MutableLiveData<String> oderDescription = new MutableLiveData<>();


    public MutableLiveData<Integer> dateLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> timeLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> deliveryTimeLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> orderTypeLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> packagingTypeLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> orderWeightLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> oderDescriptionLabel = new MutableLiveData<>();
    public MutableLiveData<Boolean> isVisible = new MutableLiveData<>();


    public OrderDataViewModel() {
        date.setValue("ABEER");
        time.setValue("ABEER");
        deliveryTime.setValue("ABEER");
        orderType.setValue("ABEER");
        packagingType.setValue("ABEER");
        orderWeight.setValue("ABEER");
        oderDescription.setValue("ABEER");

        dateLabel.setValue(R.string.order_date);
        timeLabel.setValue(R.string.receipt_time);
        deliveryTimeLabel.setValue(R.string.delivery_time);
        orderTypeLabel.setValue(R.string.order_type);
        packagingTypeLabel.setValue(R.string.packaging_type);
        orderWeightLabel.setValue(R.string.order_weight);
        oderDescriptionLabel.setValue(R.string.order_description);
    }

    public void onStart() {
    }
    // TODO: 20-Aug-20 data order request
    public void makeDataOrderRequest(String data,String time,String deliveryTimeLabel,String orderType,String packagingType,String orderWeight,String oderDescription){

    }


    public void onClickAccept() {
        doAction.setValue(AppAction.ACTION_ACCEPT);
    }

    public void onClickReject() {
        doAction.setValue(AppAction.ACTION_REJECT);
    }

}