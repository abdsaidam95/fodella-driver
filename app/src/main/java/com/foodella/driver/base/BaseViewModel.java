package com.foodella.driver.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.dataBinding.SingleLiveEvent;

public class BaseViewModel extends ViewModel {

    public SingleLiveEvent<Boolean> isLoading = new SingleLiveEvent<>();
    public MutableLiveData<Boolean> isEmpty = new MutableLiveData<>();
    public SingleLiveEvent<AppAction> doAction = new SingleLiveEvent<>();
    public SingleLiveEvent<String> showMessage = new SingleLiveEvent<>();
    public SingleLiveEvent<Integer> showMessageRes = new SingleLiveEvent<>();
    public MutableLiveData<Integer> emptyIcon = new MutableLiveData<>();
    public MutableLiveData<Integer> emptyText = new MutableLiveData<>();
    public MutableLiveData<Integer> emptyColor = new MutableLiveData<>();

    public MutableLiveData<Integer> bottomSheetState = new MutableLiveData<>();



}
