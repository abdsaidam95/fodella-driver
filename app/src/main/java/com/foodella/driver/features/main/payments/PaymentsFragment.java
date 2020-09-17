package com.foodella.driver.features.main.payments;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.FragmentPaymentsBinding;
import com.foodella.driver.features.main.home.HomeFragment;
import com.foodella.driver.features.main.payments.adapter.Payment;
import com.foodella.driver.features.paymentDetails.PaymentDetailsActivity;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

public class PaymentsFragment extends BaseFragment {

    private FragmentPaymentsBinding binding;
    private PaymentsViewModel viewModelFragment;

    public PaymentsFragment() {
    }

    public static PaymentsFragment newInstance() {
        PaymentsFragment fragment = new PaymentsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPaymentsBinding.inflate(inflater, container, false);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModelFragment = new ViewModelProvider(requireActivity(), factory).get(PaymentsViewModel.class);
        binding.setViewModel(viewModelFragment);
        binding.setLifecycleOwner(this);
       viewModelFragment.onStart();

        return binding.getRoot();
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
        if (actionEvent.getActions() == AppAction.ACTION_GO_ACTIVITY_PAYMENT) {
            Intent intent = new Intent(getActivity(), PaymentDetailsActivity.class);
            intent.putExtra("payment", (Payment) actionEvent.getData());
            startActivity(intent);

        }
    }



}