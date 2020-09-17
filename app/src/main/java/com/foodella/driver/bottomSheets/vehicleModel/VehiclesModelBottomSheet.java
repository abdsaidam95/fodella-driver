package com.foodella.driver.bottomSheets.vehicleModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.databinding.BottomSheetVehicleModelBinding;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class VehiclesModelBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetVehicleModelBinding binding;
    private VehiclesModelViewModel viewModel;
    public Vehicle vehicle;
    private int vehicleSelectedPosition = -1;


    public VehiclesModelBottomSheet(int vehicleSelectedPosition) {
        this.vehicleSelectedPosition = vehicleSelectedPosition;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetVehicleModelBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this.requireActivity(), factory).get(VehiclesModelViewModel.class);
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
                case ACTION_BACK:
                    this.dismiss();
                    break;
                case ACTION_GET_VEHICLE:
                    EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_GET_VEHICLE, vehicle, vehicleSelectedPosition));
                    dismiss();
                    break;
            }
        });
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
        if (actionEvent.getActions() == AppAction.ACTION_VEHICLE_ITEM) {
            viewModel.onSelectVehicle(actionEvent.getPosition());
            vehicle = (Vehicle) actionEvent.getData();
            vehicleSelectedPosition = actionEvent.getPosition();
        }
    }

}
