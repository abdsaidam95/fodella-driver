package com.foodella.driver.bottomSheets.verificationCodeBottomSheet;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.bottomSheets.newPasswordButtomSheet.NewPasswordBottomSheet;
import com.foodella.driver.databinding.BottomSheetVerificationCodeBinding;
import com.foodella.driver.features.profile.ProfileActivity;

import org.jetbrains.annotations.NotNull;

public class VerificationCodeBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetVerificationCodeBinding binding;
    private VerificationCodeViewModel viewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = BottomSheetVerificationCodeBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(VerificationCodeViewModel.class);
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
                case ACTION_GO_BACK:
                    hideConfirmAccountDialog();
                    break;
                case ACTION_PROFILE:
                    ((BaseActivity) requireActivity()).startNewActivity(ProfileActivity.class);
                    break;
                case ACTION_RESEND_TIMER:
                    viewModel.startTimer();
                    break;
            }
        });

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_GO_BACK:
                    hideConfirmAccountDialog();
                    break;
                case ACTION_GO_NEW_PASSWORD:
                    NewPasswordBottomSheet bottomSheet = new NewPasswordBottomSheet();
                    bottomSheet.show(getChildFragmentManager(), "NewPasswordButtomSheet");
                    break;
                case ACTION_RESEND_TIMER:
                    viewModel.startTimer();
                    break;
            }
        });
    }

    public void hideConfirmAccountDialog() {
        this.dismiss();
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.stopTimer();
    }
}
