package com.foodella.driver.bottomSheets.selectCity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.databinding.BottomSheetCityBinding;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SelectSingleCityBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetCityBinding binding;
    private SelectSingleCityViewModel viewModel;
   public int position;
   public City city;

    private int citySelectedPosition = -1;

    public SelectSingleCityBottomSheet() {

    }

    public SelectSingleCityBottomSheet(int citySelectedPosition) {
        this.citySelectedPosition = citySelectedPosition;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetCityBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this.requireActivity(), factory).get(SelectSingleCityViewModel.class);
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
        viewModel.onSelectCity(citySelectedPosition);
        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_DISMESs_CITY_BUTTOM:
                    this.dismiss();
                    break;
                case ACTION_GET_CITY:
                    EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_GET_CITY, city,position));
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
        if (actionEvent.getActions() == AppAction.ACTION_SELECT_SINGLE_CITY) {
            viewModel.onSelectCity(actionEvent.getPosition());
            position=actionEvent.getPosition();
            city= (City) actionEvent.getData();
           // dismiss();
        }
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setBehaiviarBottomSheet(binding.bottomSheet);
//
//
//
//    }

}
