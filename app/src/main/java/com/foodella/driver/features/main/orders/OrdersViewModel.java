package com.foodella.driver.features.main.orders;

import android.os.Handler;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.main.orders.adapter.OrdersAdapter;
import com.foodella.driver.features.main.orders.adapter.Order;
import com.foodella.driver.utils.OrderStatus;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.Objects;

public class OrdersViewModel extends BaseViewModel {
    public MutableLiveData<OrdersAdapter> adapter = new MutableLiveData<>();
    private ArrayList<Order> items = new ArrayList<>();
    public MutableLiveData<Integer> background = new MutableLiveData<>();
    public MutableLiveData<Integer> id = new MutableLiveData<>();

    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    public MutableLiveData<Boolean> isPhotosEmpty = new MutableLiveData<>();

    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            isRefreshing.setValue(false);
            isEmpty.setValue(items.isEmpty());
        }, 2000);
    };

    public OrdersViewModel() {

        emptyColor.setValue(R.color.colorWhite);

        isRefreshing.setValue(false);
        isPhotosEmpty.setValue(false);
        background.setValue(R.color.ef_white);
        adapter.setValue(new OrdersAdapter());
        emptyIcon.setValue(R.drawable.ic_close);
        emptyText.setValue((R.string.empty_orders));
        isEmpty.setValue(false);
    }


    public MaterialButtonToggleGroup.OnButtonCheckedListener listeners = (group, checkedId, isChecked) -> {
        if (group.getCheckedButtonId() == -1) {
            group.check(checkedId);
        }
        if (isChecked) {
            switch (checkedId) {
                case R.id.buttonPending:
                    Objects.requireNonNull(adapter.getValue()).setOrderStatus(OrderStatus.PENDING);
                    break;
                case R.id.buttonInProgress:
                    Objects.requireNonNull(adapter.getValue()).setOrderStatus(OrderStatus.IN_PROGRESS);
                    break;
                case R.id.buttonHistory:
                    Objects.requireNonNull(adapter.getValue()).setOrderStatus(OrderStatus.HISTORY);
                    break;
                case R.id.buttonReturn:
                    Objects.requireNonNull(adapter.getValue()).setOrderStatus(OrderStatus.RETURNED);
                    break;
            }
        }
    };


    public void onClick() {
        background.setValue(R.drawable.shape_tab_fragment);
    }

    public void getData() {
        items.add(new Order("thtrhtr", "rthrthrt", "rhgrthrthtr", "rthtrhr"));
        items.add(new Order("thtrhtr", "rthrthrt", "rhgrthrthtr", "rthtrhr"));
        items.add(new Order("thtrhtr", "rthrthrt", "rhgrthrthtr", "rthtrhr"));
        items.add(new Order("thtrhtr", "rthrthrt", "rhgrthrthtr", "rthtrhr"));
        items.add(new Order("thtrhtr", "rthrthrt", "rhgrthrthtr", "rthtrhr"));
        items.add(new Order("thtrhtr", "rthrthrt", "rhgrthrthtr", "rthtrhr"));
        Objects.requireNonNull(adapter.getValue()).setData(items);

    }

    // TODO: 20-Aug-20 make review request
    public void getReviewOrderRequests() {

    }

    // TODO: 20-Aug-20 getExecuteOrderRequests
    public void getExecuteOrderRequests() {

    }

    // TODO: 20-Aug-20  getpreviousOrderRequests
    public void getpreviousOrderRequests() {

    }

    // TODO: 20-Aug-20 Review Order Requests
    public void reviewOrderRequests() {

    }


    public void onStart() {
            getData();
        if (items.isEmpty()){
            isEmpty.setValue(true);}
    }


}
