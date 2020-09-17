package com.foodella.driver.features.paymentDetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityPaymentDetailsBinding;
import com.foodella.driver.features.main.payments.adapter.Payment;

import java.util.Objects;

public class PaymentDetailsActivity extends BaseActivity {

    private ActivityPaymentDetailsBinding binding;
    private PaymentDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(PaymentDetailsViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_details);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.appBar.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.payment_details));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel.onStart();
        Payment payment = (Payment) getIntent().getExtras().getSerializable("payment");
        Log.d("y5uu6", "uy" + payment.getOrderDate());
        viewModel.setPaymentData(payment);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}