package com.foodella.driver.features.paymentMethods;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.notifacation.adapter.NotificationsAdapter;
import com.foodella.driver.features.paymentMethods.adapter.PaymentMethod;
import com.foodella.driver.features.paymentMethods.adapter.PaymentMethodsAdapter;
import com.foodella.driver.utils.AppAction;

import java.util.ArrayList;
import java.util.Objects;

public class PaymentMethodsViewModel extends BaseViewModel {

    public MutableLiveData<PaymentMethodsAdapter> paymentsAdapter = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    public PaymentMethod paymentMethod;
    private ArrayList<PaymentMethod> payments = new ArrayList<>();

    public PaymentMethodsViewModel() {
        paymentsAdapter.setValue(new PaymentMethodsAdapter());
        isRefreshing.setValue(false);
        isRefreshing.setValue(false);
        emptyColor.setValue(R.color.colorWhite);
        isEmpty.setValue(payments.isEmpty());
        emptyIcon.setValue(R.drawable.ic_close);
        emptyText.setValue(R.string.no_notification);
    }

    public void onStart() {
        //setData();
    }

    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            isRefreshing.setValue(false);
            isEmpty.setValue(payments.isEmpty());
        }, 2000);
    };


    private void setData() {
        payments.add(new PaymentMethod("فيزا كارد", "8-2020", "", "**** **** **** **** 4564", false));
        payments.add(new PaymentMethod("فيزا كارد", "8-2020", "", "**** **** **** **** 4564", false));
        payments.add(new PaymentMethod("فيزا كارد", "8-2020", "", "**** **** **** **** 4564", false));
        payments.add(new PaymentMethod("فيزا كارد", "8-2020", "", "**** **** **** **** 4564", false));
        payments.add(new PaymentMethod("فيزا كارد", "8-2020", "", "**** **** **** **** 4564", false));
        Objects.requireNonNull(paymentsAdapter.getValue()).setData(payments);
    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }

    public void onClickAddPayment() {
        doAction.setValue(AppAction.ACTION_ADD_PAYMENT);
    }

    public void makeGetPaymentMethodsRequest(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        payments.add(paymentMethod);
        Objects.requireNonNull(paymentsAdapter.getValue()).appendData(payments);
        isEmpty.setValue(false);
    }

    public void onSelectPayment(int position) {
        Objects.requireNonNull(paymentsAdapter.getValue()).setSelectedPosition(position);
    }

}
