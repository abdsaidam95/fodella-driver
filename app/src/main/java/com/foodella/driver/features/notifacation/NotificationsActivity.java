package com.foodella.driver.features.notifacation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityNotificationsBinding;
import com.foodella.driver.features.main.chats.ChatsFragment;

public class NotificationsActivity extends BaseActivity {

    private NotificationsViewModel viewModel;
    private ActivityNotificationsBinding binding;

    private TextView textViewNotification;
    private TextView textViewMessages;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(NotificationsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notifications);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(getString(R.string.notifications));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem menuItemNotifications = menu.findItem(R.id.action_notifications);
        View actionViewNotifications = menuItemNotifications.getActionView();
        textViewNotification = (TextView) actionViewNotifications.findViewById(R.id.textViewNotification);

        final MenuItem menuItemMessages = menu.findItem(R.id.action_messages);
        View actionViewMessages = menuItemMessages.getActionView();
        textViewMessages = (TextView) actionViewMessages.findViewById(R.id.textViewNotification);

        setupBadge();

        actionViewNotifications.setOnClickListener(v -> onOptionsItemSelected(menuItemNotifications));
        actionViewMessages.setOnClickListener(v -> onOptionsItemSelected(menuItemMessages));

        menu.getItem(0).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.action_notifications) {

        } else if (item.getItemId() == R.id.action_messages) {

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (textViewNotification != null) {
            if (count == 0) {
                if (textViewNotification.getVisibility() != View.GONE) {
                    textViewNotification.setVisibility(View.GONE);
                }
            } else {
                textViewNotification.setText(String.valueOf(Math.min(count, 99)));
                if (textViewNotification.getVisibility() != View.VISIBLE) {
                    textViewNotification.setVisibility(View.VISIBLE);
                }
            }
        }

        if (textViewMessages != null) {
            if (count == 0) {
                if (textViewMessages.getVisibility() != View.GONE) {
                    textViewMessages.setVisibility(View.GONE);
                }
            } else {
                textViewMessages.setText(String.valueOf(Math.min(count, 99)));
                if (textViewMessages.getVisibility() != View.VISIBLE) {
                    textViewMessages.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}