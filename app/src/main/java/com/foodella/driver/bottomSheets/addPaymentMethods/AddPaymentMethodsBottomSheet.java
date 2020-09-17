package com.foodella.driver.bottomSheets.addPaymentMethods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.databinding.BottomSheetAddPaymentMethodsBinding;
import com.foodella.driver.features.paymentMethods.AddClickListener;
import com.foodella.driver.features.paymentMethods.adapter.PaymentMethod;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.jetbrains.annotations.NotNull;

public class AddPaymentMethodsBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetAddPaymentMethodsBinding binding;
    private AddPaymentMethodsViewModel viewModel;
    private PaymentMethod paymentMethod;

    private AddClickListener listener;


    public AddPaymentMethodsBottomSheet(AddClickListener addClickListener) {
        listener = addClickListener;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = BottomSheetAddPaymentMethodsBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) requireActivity(), factory).get(AddPaymentMethodsViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }


    private void initializeView() {
        viewModel.onStart();

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_CLOSE:
                    this.dismiss();
                    break;
                case ACTION_PAYMENT_METHODS:
                    paymentMethod = viewModel.generatePaymentMethod();
                    listener.onAddClickListener(paymentMethod);
                    this.dismiss();
                    break;
            }
        });
    }

}
