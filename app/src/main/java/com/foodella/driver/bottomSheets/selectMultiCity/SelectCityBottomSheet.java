package com.foodella.driver.bottomSheets.selectMultiCity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.BottomSheetSelectCityBinding;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SelectCityBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetSelectCityBinding binding;
    private SelectCityViewModel viewModel;
    private OnSelectedCitiesListener onSelectedCitiesListener;
    private ArrayList<City> selectedCities;

    public SelectCityBottomSheet(ArrayList<City> value) {
        selectedCities = value;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = BottomSheetSelectCityBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) requireActivity(), factory).get(SelectCityViewModel.class);
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
        viewModel.onStart(selectedCities);

        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_BACK:
                    this.dismiss();
                    break;
                case ACTION_SAVE:
                    if (onSelectedCitiesListener != null)
                        onSelectedCitiesListener.onSelectedCities(viewModel.getSelectedCities());
                    this.dismiss();
                    break;
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void setOnSelectedCitiesListener(OnSelectedCitiesListener onSelectedCitiesListener) {
        this.onSelectedCitiesListener = onSelectedCitiesListener;
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
        if (actionEvent.getActions() == AppAction.ACTION_SELECT_CITY) {
            viewModel.onSelectCity(actionEvent.getPosition());
        }
    }


}
