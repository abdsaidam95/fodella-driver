package com.foodella.driver.bottomSheets.signUp;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.login.model.CountryCode;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.AppUtil;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Objects;

public class SignUpViewModel extends BaseViewModel {

    public int selectedCountryCode = -1;
    public ArrayList<CountryCode> countryCodes = AppUtil.getListOfCountryCode();

    public MutableLiveData<Boolean> isChecked = new MutableLiveData<>();

    public MutableLiveData<String> phone = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> confirmPassword = new MutableLiveData<>();

    public MutableLiveData<String> countryFlag = new MutableLiveData<>();
    public MutableLiveData<String> countryCode = new MutableLiveData<>();

    public MutableLiveData<Integer> phoneError = new MutableLiveData<>();
    public MutableLiveData<Integer> passwordError = new MutableLiveData<>();
    public MutableLiveData<Integer> confirmPasswordError = new MutableLiveData<>();

    public TextChange phoneTextChange = value -> phoneError.setValue(0);
    public TextChange passwordTextChange = value -> passwordError.setValue(0);
    public TextChange confirmPasswordTextChange = value -> confirmPasswordError.setValue(0);


    public SignUpViewModel() {
        passwordError.setValue(0);
        confirmPasswordError.setValue(0);
        isChecked.setValue(false);
        countryCode.setValue("966");
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }


    public void onStart() {
    }


    public void onClickConfirm() {

        boolean v1 = validationPhone();
        boolean v2 = validationPassword();
        boolean v3 = validationCheckTerms();

        if (v1 && v2 && v3) {
            doAction.setValue(AppAction.ACTION_CONFIRM_ACCOUNT);
        }

    }

    public void onClickBackArrow() {
        doAction.setValue(AppAction.ACTION_GO_BACK);

    }

    public void onClickPhone() {
        doAction.setValue(AppAction.ACTION_COUNTRY_CODE);
    }

    public void onSelectCountryCode(int which) {
        countryFlag.setValue(countryCodes.get(which).getImage());
        countryCode.setValue(countryCodes.get(which).getCountryCode());
    }


    private boolean validationPassword() {
        if (TextUtils.isEmpty(password.getValue())) {
            passwordError.setValue((R.string.please_enter_password));
            return false;
        } else if ((password.getValue().length()) < 6) {
            passwordError.setValue((R.string.please_enter_6_digit_password));
            return false;
        } else if (TextUtils.isEmpty(confirmPassword.getValue())) {
            confirmPasswordError.setValue((R.string.invalid_confirm_password));
            return false;
        } else if (!(password.getValue().equals(confirmPassword.getValue()))) {
            confirmPasswordError.setValue((R.string.password_should_be_same));
            return false;
        } else {
            passwordError.setValue(0);
            confirmPasswordError.setValue(0);
            return true;
        }
    }


    private boolean validationPhone() {
        if (TextUtils.isEmpty(phone.getValue())) {
            phoneError.setValue((R.string.please_enter_phone_number));
            return false;
        } else {
            phoneError.setValue(0);
            return true;
        }
    }


    private boolean validationCheckTerms() {
        if (Objects.requireNonNull(isChecked.getValue()).equals(false)) {
            showMessageRes.setValue((R.string.please_accept_the_privacy_policy));
            return false;
        } else return true;
    }


    public void makeRegisterRequest(String phone, String password, String confirmPassword) {

    }
}
