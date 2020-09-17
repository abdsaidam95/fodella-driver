package com.foodella.driver.features.main.payments;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.payments.adapter.PaymentsAdapter;
import com.foodella.driver.features.main.payments.adapter.Payment;

import java.util.ArrayList;
import java.util.Objects;

public class PaymentsViewModel extends BaseViewModel {

    public MutableLiveData<PaymentsAdapter> adapter = new MutableLiveData<>();
    private ArrayList<Payment> items = new ArrayList<>();
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    public MutableLiveData<Boolean> isPhotosEmpty = new MutableLiveData<>();

    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            isRefreshing.setValue(false);
            if (items.isEmpty()) {
                isEmpty.setValue(true);
                emptyIcon.setValue(R.drawable.ic_close);
                emptyText.setValue(R.string.payment_order_empty);

            } else {
                isEmpty.setValue(false);
                getData();
            }
        }, 2000);
    };

    public PaymentsViewModel() {
        emptyColor.setValue(R.color.colorWhite);
        isEmpty.setValue(false);
        isRefreshing.setValue(false);
        isPhotosEmpty.setValue(false);
        adapter.setValue(new PaymentsAdapter());

    }

    public void getData() {
        items.add(new Payment("trgrtg", "ergfergre", "ytjhtyj", "ytjytj"));
        items.add(new Payment("trgrtg", "ergfergre", "ytjhtyj", "ytjytj"));
        items.add(new Payment("trgrtg", "ergfergre", "ytjhtyj", "ytjytj"));
        items.add(new Payment("trgrtg", "ergfergre", "ytjhtyj", "ytjytj"));
        items.add(new Payment("trgrtg", "ergfergre", "ytjhtyj", "ytjytj"));
        Objects.requireNonNull(adapter.getValue()).setData(items);
        isEmpty.setValue(false);
    }
    // TODO: 20-Aug-20 get payments request
    public void getPaymentsRequest(){

    }

    public void onStart() {
        getData();
        if (items.isEmpty()) {
            isEmpty.setValue(true);
            emptyIcon.setValue(R.drawable.ic_close);
            emptyText.setValue(R.string.payment_order_empty);
        } else {
            isEmpty.setValue(false);
            getData();
        }
    }

}
