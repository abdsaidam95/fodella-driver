package com.foodella.driver.bottomSheets.approvalOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.foodella.driver.base.BaseBottomSheetDialog;
import com.foodella.driver.databinding.BottomSheetApprovalOrderBinding;

import org.jetbrains.annotations.NotNull;

public class ApprovalOrderBottomSheet extends BaseBottomSheetDialog {

    private BottomSheetApprovalOrderBinding binding;
    private ApprovalOrderViewModel viewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = BottomSheetApprovalOrderBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this.requireActivity(), factory).get(ApprovalOrderViewModel.class);
        binding.setViewModel(viewModel);
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
                case ACTION_CLOSE:
                   this.dismiss();
                    break;
            }
        });
    }


}
