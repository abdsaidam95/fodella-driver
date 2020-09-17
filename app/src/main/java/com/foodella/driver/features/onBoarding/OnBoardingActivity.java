package com.foodella.driver.features.onBoarding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseActivity;
import com.foodella.driver.bottomSheets.languageBottomSheet.LanguageBottomSheet;
import com.foodella.driver.databinding.ActivityOnBoardingBinding;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.From;

public class OnBoardingActivity extends BaseActivity {

    private LanguageBottomSheet bottomSheetFragment;
    private OnBoardingViewModels viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(OnBoardingViewModels.class);

        ActivityOnBoardingBinding boardingBinding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding);
        boardingBinding.setViewModel(viewModel);
        boardingBinding.setFragmentManager(getSupportFragmentManager());
        boardingBinding.setLifecycleOwner(this);

        initializeView();


    }

    private void initializeView() {
        bottomSheetFragment = new LanguageBottomSheet(From.FROM_ON_BOARDING_ACTIVITY);

        viewModel.doAction.observe(this, action -> {
            if (action == AppAction.ACTION_LANGUAGE_DIALOG) {
                if (!bottomSheetFragment.isVisible()) {
                    showLanguageDialog();
                }
            }
        });
    }

    public void showLanguageDialog() {
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

}