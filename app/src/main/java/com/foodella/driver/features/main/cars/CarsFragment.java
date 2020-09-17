package com.foodella.driver.features.main.cars;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseFragment;
import com.foodella.driver.databinding.FragmentCarsBinding;
import com.foodella.driver.features.addCar.AddCarActivity;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.features.profile.ProfileActivity;
import com.foodella.driver.features.profile.cars.CarsListFragment;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.AppConst;

import org.jetbrains.annotations.NotNull;

import static android.app.Activity.RESULT_OK;
import static com.foodella.driver.utils.AppAction.ACTION_ADD_NEW_CAR;

public class CarsFragment extends BaseFragment {

    private FragmentCarsBinding binding;
    private CarsViewModel viewModel;
    private Vehicle vehicle;

    public static CarsFragment newInstance() {
        return new CarsFragment();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarsBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this.requireActivity(), factory).get(CarsViewModel.class);
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
        viewModel.doAction.observe(this, appAction -> {
            if (appAction == ACTION_ADD_NEW_CAR) {
                Intent intent = new Intent(requireActivity(), AddCarActivity.class);
                startActivityForResult(intent, AppConst.ADD_CAR_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConst.ADD_CAR_REQUEST_CODE && data != null && resultCode == RESULT_OK) {
            vehicle = (Vehicle) data.getSerializableExtra("vehicle");
            assert vehicle != null;
            Log.d("CARS", "onActivityResult: " + vehicle.getVehicleName());
            viewModel.onStart(vehicle);
        }
    }



}