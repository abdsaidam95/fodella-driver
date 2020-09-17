package com.foodella.driver.features.addCar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.bottomSheets.insuranceType.InsuranceTypeBottomSheet;
import com.foodella.driver.bottomSheets.vehicleModel.VehiclesModelBottomSheet;
import com.foodella.driver.databinding.FragmentAddCarBinding;
import com.foodella.driver.features.addCar.AddCarActivity;
import com.foodella.driver.features.main.MainActivity;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import static com.foodella.driver.utils.AppAction.ACTION_CAR_LIST;

public class AddCarFragment extends BaseFragment {


    private AddCarViewModel viewModel;
    private FragmentAddCarBinding binding;

    public static CarLicenseFragment newInstance() {
        return new CarLicenseFragment();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddCarBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(AddCarViewModel.class);
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

       Vehicle vehicle =  viewModel.generateVehicle();

        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_VEHICLE_MODEL:
                    showVehicleModelDialog();
                    break;
                case ACTION_INSURANCE_TYPE:
                    showInsuranceTypeDialog();
                    break;

            }
        });
    }

    private void showInsuranceTypeDialog() {
        InsuranceTypeBottomSheet bottomSheetFragment = new InsuranceTypeBottomSheet(viewModel.getVehicleSelectedPosition());
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }

    public void showVehicleModelDialog() {
        VehiclesModelBottomSheet bottomSheetFragment = new VehiclesModelBottomSheet(viewModel.getInsuranceTypeSelectedPosition());
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
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
        if (actionEvent.getActions() == AppAction.ACTION_GET_VEHICLE) {
            viewModel.onSelectedVehicle(actionEvent);
        } else if (actionEvent.getActions() == AppAction.ACTION_GET_INSURANCE_TYPE) {
            viewModel.onSelectedInsuranceType(actionEvent);
        }
    }

}