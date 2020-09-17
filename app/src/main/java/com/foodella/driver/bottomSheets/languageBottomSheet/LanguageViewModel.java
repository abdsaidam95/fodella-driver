package com.foodella.driver.bottomSheets.languageBottomSheet;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.From;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class LanguageViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> isLanguageSelected = new MutableLiveData<>();
    public MutableLiveData<Boolean> isArabicClicked = new MutableLiveData<>();
    public MutableLiveData<Boolean> isEnglishClicked = new MutableLiveData<>();

    public From fromFlag;

    public LanguageViewModel() {
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart(From fromFlag) {
        this.fromFlag = fromFlag;
        isLanguageSelected.setValue(false);
        isArabicClicked.setValue(false);
        isEnglishClicked.setValue(false);
    }

    public void onClickArabicLanguage() {
        isLanguageSelected.setValue(true);

        isArabicClicked.setValue(true);
        isEnglishClicked.setValue(false);

        doAction.setValue(AppAction.ACTION_SET_LANGUAGE_ARABIC);

    }

    public void onClickEnglishLanguage() {
        isLanguageSelected.setValue(true);

        isArabicClicked.setValue(false);
        isEnglishClicked.setValue(true);

        doAction.setValue(AppAction.ACTION_SET_LANGUAGE_ENGLISH);

    }

    public void onClickOk() {
        if (fromFlag.equals(From.FROM_ON_BOARDING_ACTIVITY)) {
            doAction.setValue(AppAction.ACTION_LOGIN);
        } else if (fromFlag.equals(From.FROM_MORE_FRAGMENT)){
            doAction.setValue(AppAction.ACTION_MAIN);
        }
    }

}
