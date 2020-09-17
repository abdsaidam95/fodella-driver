package com.foodella.driver.features.addCar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityAddCarBinding;
import com.foodella.driver.features.addCar.fragments.AddCarFragment;
import com.foodella.driver.features.addCar.fragments.CarLicenseFragment;
import com.foodella.driver.features.main.MainActivity;
import com.foodella.driver.features.main.cars.CarsFragment;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.features.profile.ProfileActivity;
import com.foodella.driver.features.profile.cars.CarsListFragment;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.From;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;

import static com.foodella.driver.utils.AppAction.ACTION_CAR_LIST;

public class AddCarActivity extends BaseActivity {

    private AddCarActivityViewModel viewModel;
    private ActivityAddCarBinding binding;
    private Vehicle vehicle;
    private From from;


    public AddCarActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(AddCarActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_car);
        binding.setViewModel(viewModel);
        binding.setFragmentActivity(this);
        viewModel.onStart();
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.appBar.toolbar);
        initializeToolbar(getString(R.string.add_car));

        initializeView();

    }

    private void initializeView() {

        replaceFragment(new AddCarFragment(), R.id.addCarContainerView);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {

                case ACTION_BACK:
                    onBackPressed();
                    break;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        finish();
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
        if (actionEvent.getActions() == AppAction.ACTION_CAR_LICENSE) {
            Object data = actionEvent.getData();

            if (data != null && data instanceof Vehicle) {
                vehicle = (Vehicle) data;
                Log.d("vehicle", "onActionEvent: " + vehicle.getVehicleName());

                Bundle bundle = new Bundle();
                bundle.putSerializable("vehicle", vehicle);

                CarLicenseFragment carLicenseFragment = new CarLicenseFragment();
                carLicenseFragment.setArguments(bundle);
                changeFragment(carLicenseFragment,bundle);

            }
        }
    }


    private void changeFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.addCarContainerView, fragment);
        fragmentTransaction.commit();
    }

}