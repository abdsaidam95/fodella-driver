package com.foodella.driver.bottomSheets.signUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.bottomSheets.confirmAccount.ConfirmAccountBottomSheet;
import com.foodella.driver.databinding.BottomSheetSignUpBinding;
import com.foodella.driver.features.login.model.CountryCode;

public class SignUpBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetSignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = BottomSheetSignUpBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) requireActivity(), factory).get(SignUpViewModel.class);
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

        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_COUNTRY_CODE:
                    showSpinnerDialog();
                    break;
                case ACTION_CONFIRM_ACCOUNT:
                    showConfirmAccountDialog();
                    break;
                case ACTION_GO_BACK:
                    hideSignUpDialog();
                    break;
            }
        });
    }

    public void showSpinnerDialog() {

        ArrayAdapter<CountryCode> countryCodeArrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_single_choice, viewModel.countryCodes);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.select_country_code);

        builder.setSingleChoiceItems(countryCodeArrayAdapter, viewModel.selectedCountryCode, (dialog, which) -> {
            dialog.dismiss();
            viewModel.onSelectCountryCode(which);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showConfirmAccountDialog() {
        ConfirmAccountBottomSheet bottomSheetFragment = new ConfirmAccountBottomSheet();
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }

    public void hideSignUpDialog() {
        this.dismiss();
    }

}
