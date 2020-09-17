package com.foodella.driver.bottomSheets.insuranceType;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.bottomSheets.insuranceType.adapter.InsuranceType;
import com.foodella.driver.databinding.BottomSheetInsuranceTypeBinding;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class InsuranceTypeBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetInsuranceTypeBinding binding;
    private InsuranceTypeViewModel viewModel;
    public InsuranceType insuranceType;
    private int insuranceTypeSelectedPosition = -1;


    public InsuranceTypeBottomSheet(int insuranceTypeSelectedPosition) {
        this.insuranceTypeSelectedPosition = insuranceTypeSelectedPosition;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetInsuranceTypeBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this.requireActivity(), factory).get(InsuranceTypeViewModel.class);
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
                case ACTION_GET_INSURANCE_TYPE:
                    EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_GET_INSURANCE_TYPE, insuranceType, insuranceTypeSelectedPosition));
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
        if (actionEvent.getActions() == AppAction.ACTION_INSURANCE_TYPE_ITEM) {
            viewModel.onSelectVehicle(actionEvent.getPosition());
            insuranceType = (InsuranceType) actionEvent.getData();
            insuranceTypeSelectedPosition = actionEvent.getPosition();
        }
    }

}
