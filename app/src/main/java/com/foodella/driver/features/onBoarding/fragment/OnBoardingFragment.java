package com.foodella.driver.features.onBoarding.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.FragmentOnBoardingBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OnBoardingFragment extends BaseFragment {

    FragmentOnBoardingBinding binding;
    OnBoardingViewModel viewModelFragment;

    public static OnBoardingFragment newInstance(OnBoarding onBoarding) {
        OnBoardingFragment onBoardingFragment = new OnBoardingFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("onBoarding", onBoarding);

        onBoardingFragment.setArguments(bundle);
        return onBoardingFragment;
    }

    public OnBoardingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModelFragment = new ViewModelProvider(requireActivity(), factory).get(OnBoardingViewModel.class);

        viewModelFragment.onStart((OnBoarding) Objects.requireNonNull(requireArguments().getSerializable("onBoarding")));

        binding.setViewModel(viewModelFragment);
        binding.setLifecycleOwner(this);
        return binding.getRoot();

    }
}