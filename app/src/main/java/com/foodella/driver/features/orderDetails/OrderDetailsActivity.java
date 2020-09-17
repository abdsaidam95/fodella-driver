package com.foodella.driver.features.orderDetails;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityOrderDetailsBinding;
import com.foodella.driver.features.main.MainActivity;
import com.foodella.driver.features.orderDetails.fragments.addressFragment.AddressAndContactFragment;
import com.foodella.driver.features.orderDetails.fragments.financialDataFragment.FinancialDataFragment;
import com.foodella.driver.features.orderDetails.fragments.orderDataFragment.OrderDataFragment;
import com.foodella.driver.features.orderDetails.fragments.ratingFragment.RatingFragment;

import java.util.Objects;

public class OrderDetailsActivity extends BaseActivity {

    private OrderDetailsViewModel viewModel;
    private ActivityOrderDetailsBinding binding;


    private TextView textViewNotification;
    private TextView textViewMessages;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(OrderDetailsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.appBar.toolbar);
        Objects.requireNonNull(getSupportActionBar()). setTitle(getString(R.string.orders));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel.onStart();

        View.OnClickListener onClickListener = v -> binding.toggleButton.check(v.getId());

        replaceFragment(new OrderDataFragment(), R.id.main_container);

        binding.orderDataBtn.setOnClickListener(onClickListener);
        binding.addressAndContactBtn.setOnClickListener(onClickListener);
        binding.financialDataBtn.setOnClickListener(onClickListener);
        binding.ratingBtn.setOnClickListener(onClickListener);

        initializeView();
    }

    private void initializeView() {
        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_ORDER_DATA:
                case ACTION_NO_SELECTED_BUTTON:
                    replaceFragment(new OrderDataFragment(), R.id.main_container);
                    break;
                case ACTION_ADDRESS:
                    replaceFragment(new AddressAndContactFragment(), R.id.main_container);
                    break;
                case ACTION_FINANCIAL_DATA:
                    replaceFragment(new FinancialDataFragment(), R.id.main_container);
                    break;
                case ACTION_RATING:
                    replaceFragment(new RatingFragment(), R.id.main_container);
                    break;
                case ACTION_BACK:
                    onBackPressed();
                    break;
            }
        });
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

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.action_notifications) {
            showToast("action_notifications");
        } else if (item.getItemId() == R.id.action_messages) {
            showToast("action_messages");
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startNewActivity(MainActivity.class);
    }
}