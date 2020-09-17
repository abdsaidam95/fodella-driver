package com.foodella.driver.bottomSheets.languageBottomSheet;

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
import com.foodella.driver.databinding.BottomSheetLanguageBinding;
import com.foodella.driver.features.login.LoginActivity;
import com.foodella.driver.features.main.MainActivity;
import com.foodella.driver.utils.AppLanguageContextWrapper;
import com.foodella.driver.utils.From;

import org.jetbrains.annotations.NotNull;

public class LanguageBottomSheet extends BaseBottomSheetDialog {

    public LanguageViewModel viewModel;
    private From from;

    public LanguageBottomSheet(From from) {
        this.from = from;
    }

    public LanguageBottomSheet() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BottomSheetLanguageBinding binding = BottomSheetLanguageBinding.inflate(inflater, container, false);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) requireActivity(), factory).get(LanguageViewModel.class);
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
        viewModel.onStart(from);

        viewModel.doAction.observe(this, appAction -> {
            switch (appAction) {
                case ACTION_LOGIN:
                    dismiss();
                    ((BaseActivity) requireActivity()).startNewActivity(LoginActivity.class, null, true);
                    break;
                case ACTION_MAIN:
                    dismiss();
                    ((BaseActivity) requireActivity()).startNewActivity(MainActivity.class, null, true);
                    break;
                case ACTION_SET_LANGUAGE_ARABIC:
                    changeLanguage("ar");
                    break;
                case ACTION_SET_LANGUAGE_ENGLISH:
                    changeLanguage("en");
                    break;
            }
        });
    }


    private void changeLanguage(String language) {
        AppLanguageContextWrapper.setAppLanguage(language);
    }





}
