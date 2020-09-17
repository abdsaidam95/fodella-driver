package com.foodella.driver.features.addService;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.bottomSheets.selectCity.SelectSingleCityBottomSheet;
import com.foodella.driver.databinding.ActivityAddServiceBinding;
import com.foodella.driver.features.main.services.MyServicesFragment;
import com.foodella.driver.features.main.services.Services;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

import static com.foodella.driver.utils.AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY;
import static com.foodella.driver.utils.AppAction.GO_TO_SERVICE_FRAGMENT;
import static com.foodella.driver.utils.AppConst.RES_NOT_OK;

public class AddServiceActivity extends BaseActivity {

    private AddServiceViewModel viewModel;
    private ActivityAddServiceBinding binding;
    public Services services;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(AddServiceViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_service);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.appBar.toolbar);
        Objects.requireNonNull(getSupportActionBar()). setTitle(getString(R.string.addservice));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel.onStart();


        viewModel.doAction.observe(this, appAction -> {
            if (appAction == GO_TO_SERVICE_FRAGMENT) {
               services= viewModel.getServices();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("service",services);
                setResult(RES_NOT_OK,returnIntent);
                finish();
                onBackPressed();
            } else if (appAction == ACTION_SHOW_BOTOOM_SHEET_CITY) {
                SelectSingleCityBottomSheet cityBottomSheet = new SelectSingleCityBottomSheet(viewModel.getCitySelectedPosition());
                cityBottomSheet.show(getSupportFragmentManager(), "exampleBottomSheetCity");
            }
        });
        int flag= getIntent().getExtras().getInt("flag");
        if (flag==1){
            Services service = (Services) getIntent().getExtras().getSerializable("sarvice");
            viewModel.setInisalizeData(service);
            viewModel.textByttom.setValue(R.string.edit);
        }

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
        if (actionEvent.getActions() == AppAction.ACTION_GET_CITY) {
            viewModel.onSelectedCity(actionEvent);
//        }else if (actionEvent.getActions()==AppAction.GO_TO_PASS_OBJECT){
//            Log.d("ytjty","jytyjty"+actionEvent.getData());
//
//            viewModel.setInisalizeData((Services) actionEvent.getData());

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}