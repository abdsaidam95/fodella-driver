package com.foodella.driver.features.orderDetails.fragments.addressFragment;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;

public class AddressAndContactViewModel extends BaseViewModel {

    public MutableLiveData<String> providerName = new MutableLiveData<>();
    public MutableLiveData<String> providerCity = new MutableLiveData<>();
    public MutableLiveData<String> providerAddress = new MutableLiveData<>();
    public MutableLiveData<String> customerName = new MutableLiveData<>();
    public MutableLiveData<String> customerCity = new MutableLiveData<>();
    public MutableLiveData<String> customerAddress = new MutableLiveData<>();
    public MutableLiveData<String> shortestDistance = new MutableLiveData<>();

    public MutableLiveData<Integer> providerNameLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> providerCityLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> providerAddressLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> customerNameLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> customerCityLabel = new MutableLiveData<>();
    public MutableLiveData<Integer> customerAddressLabel = new MutableLiveData<>();


    public AddressAndContactViewModel() {
        providerName.setValue("ABEER");
        providerCity.setValue("مكة");
        providerAddress.setValue("test");
        customerName.setValue("ABEER");
        customerCity.setValue("جدة");
        customerAddress.setValue("test");
        shortestDistance.setValue("99");

        providerNameLabel.setValue(R.string.Name);
        providerCityLabel.setValue(R.string.city);
        providerAddressLabel.setValue(R.string.address);
        customerNameLabel.setValue(R.string.Name);
        customerCityLabel.setValue(R.string.city);
        customerAddressLabel.setValue(R.string.address);

    }

    public void onStart() {

    }
    // TODO: 20-Aug-20 make adress
    public void makeAdressAndContact(String providerName,String providerCity,String providerAddress,String customerName,String customerCity,String customerAddress,String shortestDistance){

    }


    public void onClickAccept() {
        doAction.setValue(AppAction.ACTION_ACCEPT);
    }

    public void onClickReject() {
        doAction.setValue(AppAction.ACTION_REJECT);
    }


}
