package com.foodella.driver.bottomSheets.newPasswordButtomSheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.databinding.BottomSheetNewPasswordBinding;
import com.foodella.driver.features.main.MainActivity;

import org.jetbrains.annotations.NotNull;

public class NewPasswordBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetNewPasswordBinding binding;
    private NewPasswordViewModel viewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = BottomSheetNewPasswordBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this.requireActivity(), factory).get(NewPasswordViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
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
                case ACTION_BACK_LOGIN:
                    this.dismiss();
                    break;
                case ACTION_GO_HOME_ACCOUNT:
                    ((BaseActivity) requireActivity()).startNewActivity(MainActivity.class);
                    break;

            }
        });
    }
}
