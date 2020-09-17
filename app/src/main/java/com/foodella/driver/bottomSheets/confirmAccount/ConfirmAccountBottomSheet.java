package com.foodella.driver.bottomSheets.confirmAccount;

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
import com.foodella.driver.databinding.BottomSheetConfirmAccountBinding;
import com.foodella.driver.features.profile.ProfileActivity;

import org.jetbrains.annotations.NotNull;

public class ConfirmAccountBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetConfirmAccountBinding binding;
    private ConfirmAccountViewModel viewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = BottomSheetConfirmAccountBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(ConfirmAccountViewModel.class);
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

        viewModel.showMessage.observe(this, this::showToast);
        viewModel.showMessageRes.observe(this, this::showToast);

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
    }

    public void hideConfirmAccountDialog() {
        this.dismiss();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.stopTimer();
    }
}