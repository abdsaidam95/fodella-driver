package com.foodella.driver.bottomSheets.newPasswordButtomSheet;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import static com.foodella.driver.utils.AppAction.ACTION_BACK_LOGIN;

public class NewPasswordViewModel extends BaseViewModel {

    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> confirmPassword = new MutableLiveData<>();

    public MutableLiveData<Integer> passwordError = new MutableLiveData<>();
    public MutableLiveData<Integer> confirmPasswordError = new MutableLiveData<>();

    public TextChange passwordTextChange = value -> passwordError.setValue(0);
    public TextChange confirmPasswordTextChange = value -> confirmPasswordError.setValue(0);

    public NewPasswordViewModel() {
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart() {
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

    public void makeNewPassWordRequest() {

        String newPassword = password.getValue();
        String confirmNewPassword = confirmPassword.getValue();

        boolean v1 = validationPassword();
        if (v1) {
            doAction.setValue(AppAction.ACTION_GO_HOME_ACCOUNT);
        }

    }


    public void onClickDismissDialog() {
        doAction.setValue(ACTION_BACK_LOGIN);
    }



}
