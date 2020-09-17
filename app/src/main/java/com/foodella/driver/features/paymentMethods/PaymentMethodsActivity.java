package com.foodella.driver.features.paymentMethods;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.bottomSheets.addPaymentMethods.AddPaymentMethodsBottomSheet;
import com.foodella.driver.databinding.ActivityPaymentMethodsBinding;
import com.foodella.driver.features.paymentMethods.adapter.PaymentMethod;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

public class PaymentMethodsActivity extends BaseActivity implements AddClickListener {

    private PaymentMethodsViewModel viewModel;
    private ActivityPaymentMethodsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(PaymentMethodsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_methods);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(getString(R.string.payment_methods));

        initializeView();
    }

    private void initializeView() {

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_ADD_PAYMENT:
                    showAddPaymentMethodsDialog();
                    break;
                case ACTION_BACK:
                    onBackPressed();
                    break;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddPaymentMethodsDialog() {
        AddPaymentMethodsBottomSheet bottomSheetFragment = new AddPaymentMethodsBottomSheet(this);
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onActionEvent(ActionEvent actionEvent) {
        if (actionEvent.getActions() == AppAction.ACTION_PAYMENT_ITEM_REMOVE) {
            Object data = actionEvent.getData();
            if (data != null && data instanceof PaymentMethod) {
                Objects.requireNonNull(viewModel.paymentsAdapter.getValue()).removeItem((PaymentMethod) data);
            }
        } else if (actionEvent.getActions() == AppAction.ACTION_PAYMENT_ITEM) {
            viewModel.onSelectPayment(actionEvent.getPosition());
            Log.d("ABR", "onActionEvent: " + "TEst");
        }
    }


    @Override
    public void onAddClickListener(PaymentMethod paymentMethod) {
        viewModel.makeGetPaymentMethodsRequest(paymentMethod);
    }


}