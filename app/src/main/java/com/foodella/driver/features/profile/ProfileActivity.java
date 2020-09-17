package com.foodella.driver.features.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.databinding.ActivityProfileBinding;
import com.foodella.driver.features.addCar.AddCarActivity;
import com.foodella.driver.features.main.cars.adapter.Vehicle;
import com.foodella.driver.features.profile.cars.CarsListFragment;
import com.foodella.driver.features.profile.documents.AddDataFragment;
import com.foodella.driver.features.profile.info.InfoFragment;
import com.foodella.driver.features.profile.services.ServicesFragment;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.AppConst;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.foodella.driver.utils.AppAction.ACTION_BACK;
import static com.foodella.driver.utils.AppAction.ACTION_CAR_LIST;
import static com.foodella.driver.utils.AppAction.ACTION_GO_CARLIST;
import static com.foodella.driver.utils.AppAction.ACTION_GO_SURVICE;

public class ProfileActivity extends BaseActivity {

    private ActivityProfileBinding binding;
    private ProfileViewModel viewModel;
    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(ProfileViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setViewModel(viewModel);
        binding.setFragmentManager(getSupportFragmentManager());
        binding.setLifecycleOwner(this);
        profileObserver();
        replaceFragment(new InfoFragment(), R.id.containar);

    }

    public void profileObserver() {
        viewModel.doAction.observe(this, new Observer<AppAction>() {
            @Override
            public void onChanged(AppAction appAction) {
                if (appAction == ACTION_CAR_LIST) {

                } else if (appAction == ACTION_BACK) {
                    onBackPressed();
                }
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
        if (actionEvent.getActions() == AppAction.ACTION_GO_DECUMENT) {
            replaceFragment(new AddDataFragment(), R.id.containar);
            viewModel.positionFragment("2/4", R.string.Profile_privite);
        } else if (actionEvent.getActions() == ACTION_GO_SURVICE) {
            replaceFragment(new ServicesFragment(), R.id.containar);
            viewModel.positionFragment("3/4", R.string.services);
        } else if (actionEvent.getActions() == ACTION_GO_CARLIST) {
            replaceFragment(new CarsListFragment(), R.id.containar);
            viewModel.positionFragment("4/4", R.string.Vehicles);
        } else if (actionEvent.getActions() == AppAction.ACTION_CAR_LIST) {
            Intent intent = new Intent(ProfileActivity.this, AddCarActivity.class);
            startActivityForResult(intent, AppConst.ADD_CAR_REQUEST_CODE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (viewModel.pagerCounter.getValue().equals("1/4")) {
            finish();
        }
       else if (viewModel.pagerCounter.getValue().equals("2/4")){
            viewModel.positionFragment("1/4",R.string.Profile_privite);
        }else if (viewModel.pagerCounter.getValue().equals("3/4")){
            viewModel.positionFragment("2/4",R.string.Profile_privite);

        }else if (viewModel.pagerCounter.getValue().equals("4/4")){
            viewModel.positionFragment("3/4",R.string.services);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConst.ADD_CAR_REQUEST_CODE && data != null && resultCode == RESULT_OK) {
            vehicle = (Vehicle) data.getSerializableExtra("vehicle");
            assert vehicle != null;
            Bundle bundle = new Bundle();
            bundle.putSerializable("vehicle", vehicle);
            CarsListFragment fragment = new CarsListFragment();
            changeFragment(fragment, bundle);
        }
    }

    private void changeFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containar, fragment);
        fragmentTransaction.commit();
    }


}