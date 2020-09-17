package com.foodella.driver.features.profile.services;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.selectMultiCity.OnSelectedCitiesListener;
import com.foodella.driver.bottomSheets.selectMultiCity.SelectCityBottomSheet;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.FragmentServicesBinding;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ServicesFragment extends BaseFragment {

    private FragmentServicesBinding binding;
    private ServicesViewModel viewModel;
    private SelectCityBottomSheet bottomSheetFragment;

    public ServicesFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentServicesBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(ServicesViewModel.class);
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

        viewModel.showMessage.observe(this, this::showToast);

        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_CHOOSE_CITIES:
                    showSelectCityDialog();
                    break;
            }
        });
    }

    public void showSelectCityDialog() {
        bottomSheetFragment = new SelectCityBottomSheet(viewModel.selectedCities.getValue());
        bottomSheetFragment.setOnSelectedCitiesListener(cities -> {
            viewModel.onSelectCities(cities);
        });
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }

}
