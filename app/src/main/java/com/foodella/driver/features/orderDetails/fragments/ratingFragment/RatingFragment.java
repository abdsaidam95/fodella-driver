package com.foodella.driver.features.orderDetails.fragments.ratingFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.FragmentRatingBinding;
import com.foodella.driver.features.orderDetails.fragments.orderDataFragment.OrderDataFragment;

import org.jetbrains.annotations.NotNull;

public class RatingFragment extends BaseFragment {

    private RatingViewModel viewModel;
    private FragmentRatingBinding binding;

    public static OrderDataFragment newInstance() {
        return new OrderDataFragment();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRatingBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(RatingViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();

        initializeView();

        return binding.getRoot();
    }


    private void initializeView() {
        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);
    }


}
