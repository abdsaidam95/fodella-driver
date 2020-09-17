package com.foodella.driver.features.main.services;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Objects;

import static com.foodella.driver.utils.AppAction.GO_TO_SERVICE_ACTIVITY;

public class MyServicesViewModel extends BaseViewModel {

    public MutableLiveData<ServicesAdapter> adapter = new MutableLiveData<>();
    public ArrayList<Services> items = new ArrayList<>();
    public MutableLiveData<Boolean> serviceEmpty = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();


    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isEmpty.setValue(items.isEmpty());
                isRefreshing.setValue(false);
            }
        }, 2000);
    };

    public MyServicesViewModel() {
        emptyColor.setValue(R.color.colorWhite);
        adapter.setValue(new ServicesAdapter());
        serviceEmpty.setValue(true);
        isRefreshing.setValue(false);
        isEmpty.setValue(false);
        emptyIcon.setValue(R.drawable.ic_close);
        emptyText.setValue((R.string.service_empty));

    }

    public void deleteItemService(int position) {
        adapter.getValue().deleteItem(position);
    }

    public void setAppendAdapterService(Services service) {
        items.add(service);
        Objects.requireNonNull(adapter.getValue()).appendData(items);

        if (items.isEmpty()) {
            isEmpty.setValue(true);
        } else {
            isEmpty.setValue(false);
        }

    }

    public void onClickAddService() {
        doAction.setValue(GO_TO_SERVICE_ACTIVITY);

    }
    public void  onStart(){
        if (items.isEmpty()) {
            isEmpty.setValue(true);

        }

    }

    // TODO: 23-Aug-20 get service list
    public void getServiceRequest() {

    }

    public void editItem(int position,Services services) {
        Objects.requireNonNull(adapter.getValue()).editItem(position,services);
    }

}