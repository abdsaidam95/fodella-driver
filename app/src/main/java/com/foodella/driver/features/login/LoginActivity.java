package com.foodella.driver.features.login;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.bottomSheets.forgetPasswordBottomSheet.ForgetPasswordBottomSheet;
import com.foodella.driver.bottomSheets.signUp.SignUpBottomSheet;
import com.foodella.driver.bottomSheets.vehicleModel.VehiclesModelBottomSheet;
import com.foodella.driver.databinding.ActivityLoginBinding;
import com.foodella.driver.features.login.model.CountryCode;
import com.foodella.driver.features.main.MainActivity;
import com.foodella.driver.features.profile.ProfileActivity;

import java.util.function.Consumer;

public class LoginActivity extends BaseActivity {

    private LoginViewModel viewModel;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.onStart();
        initializeView();
    }

    private void initializeView() {
        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_HOME:
                    startNewActivity(MainActivity.class);
                    break;
                case ACTION_COUNTRY_CODE:
                    showSpinnerDialog();
                    break;
                case ACTION_FORGET_PASSWORD:
                    showForgetPasswordDialog();
                    break;
                case ACTION_GO_PROFILE:
                    startNewActivity(ProfileActivity.class);
                    break;
                case ACTION_DRIVER_SIGN_UP:
                    showSignUpDialog();
                    break;
                case ACTION_RESTAURANT_SIGN_UP:
                    showSignUpDialog();
                    break;
            }
        });
    }

    public void showSpinnerDialog() {


        ArrayAdapter<CountryCode> countryCodeArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, viewModel.countryCodes);
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(R.string.app_name);

        builder.setSingleChoiceItems(countryCodeArrayAdapter, viewModel.selectedCountryCode, (dialog, which) -> {
            dialog.dismiss();
            viewModel.onSelectCountryCode(which);
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void showSignUpDialog() {
        SignUpBottomSheet bottomSheetFragment = new SignUpBottomSheet();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
    public void showForgetPasswordDialog() {
        ForgetPasswordBottomSheet forgetPasswordButtomSheet = new ForgetPasswordBottomSheet();
        forgetPasswordButtomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
    }



}