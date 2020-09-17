package com.foodella.driver.features.orderDetails.fragments.orderDataFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.FragmentOrderDataBinding;

import org.jetbrains.annotations.NotNull;

public class OrderDataFragment extends BaseFragment {

    private OrderDataViewModel viewModel;
    private FragmentOrderDataBinding binding;

    public static OrderDataFragment newInstance() {
        return new OrderDataFragment();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderDataBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(OrderDataViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        initializeView();

        return binding.getRoot();
    }


    private void initializeView() {
        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_ACCEPT:

                    break;
                case ACTION_REJECT:

                    break;
            }
        });

    }


}