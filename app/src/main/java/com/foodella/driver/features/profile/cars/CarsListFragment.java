package com.foodella.driver.features.profile.cars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.FragmentCarsListBinding;
import com.foodella.driver.features.main.MainActivity;
import com.foodella.driver.features.main.cars.adapter.Vehicle;


public class CarsListFragment extends BaseFragment {

    private FragmentCarsListBinding binding;
    private CarsListViewModel viewModel;
    Vehicle vehicle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCarsListBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(CarsListViewModel.class);
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

        if (getArguments() != null) {
            vehicle = (Vehicle) getArguments().getSerializable("vehicle");
        }
        if (vehicle != null) {
            viewModel.onStart(vehicle);
        }

        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_GO_MAIN:
                    ((BaseActivity) requireActivity()).startNewActivity(MainActivity.class);
                    break;
                case ACTION_CAR_LIST:

                    break;
            }
        });
    }

}

