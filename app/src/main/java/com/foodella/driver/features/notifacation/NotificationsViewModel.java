package com.foodella.driver.features.notifacation;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.notifacation.adapter.Notification;
import com.foodella.driver.features.notifacation.adapter.NotificationsAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class NotificationsViewModel extends BaseViewModel {

    public MutableLiveData<NotificationsAdapter> notificationsAdapter = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    public ArrayList<Notification> items = new ArrayList<>();


    public NotificationsViewModel() {

        notificationsAdapter.setValue(new NotificationsAdapter());
        isRefreshing.setValue(false);
        emptyColor.setValue(R.color.colorWhite);

        getData();

        isEmpty.setValue(items.isEmpty());
        emptyIcon.setValue(R.drawable.ic_close);
        emptyText.setValue(R.string.no_notification);

    }

    public SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = () -> {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            isRefreshing.setValue(false);
            isEmpty.setValue(items.isEmpty());
        }, 2000);
    };


    public void onStart() {
        if (items.isEmpty()) {
            isEmpty.setValue(true);
        } else {
            isEmpty.setValue(false);
            getData();
        }
    }

    public void getData() {
       items.add(new Notification("نص افتراضي", "نموذج نص من المفترض أن يكون هنا نموذج نص من المفترض أن  نموذج نص من المفترض أن يكون هنا", "09:30 AM"));
        items.add(new Notification("نص افتراضي", "نموذج نص من المفترض أن يكون هنا نموذج نص من المفترض أن  نموذج نص من المفترض أن يكون هنا", "09:30 AM"));
        items.add(new Notification("نص افتراضي", "نموذج نص من المفترض أن يكون هنا نموذج نص من المفترض أن  نموذج نص من المفترض أن يكون هنا", "09:30 AM"));
        items.add(new Notification("نص افتراضي", "نموذج نص من المفترض أن يكون هنا نموذج نص من المفترض أن  نموذج نص من المفترض أن يكون هنا", "09:30 AM"));
        items.add(new Notification("نص افتراضي", "نموذج نص من المفترض أن يكون هنا نموذج نص من المفترض أن  نموذج نص من المفترض أن يكون هنا", "09:30 AM"));
        items.add(new Notification("نص افتراضي", "نموذج نص من المفترض أن يكون هنا نموذج نص من المفترض أن  نموذج نص من المفترض أن يكون هنا", "09:30 AM"));
        Objects.requireNonNull(notificationsAdapter.getValue()).setData(items);
    }

    public void makeGetNotificationsRequest() {
    }


}
