package com.foodella.driver.features.login;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.features.login.model.CountryCode;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.AppUtil;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.foodella.driver.utils.AppAction.ACTION_GO_PROFILE;

public class LoginViewModel extends BaseViewModel {

    public ArrayList<CountryCode> countryCodes = AppUtil.getListOfCountryCode();
    public int selectedCountryCode = -1;

    public MutableLiveData<String> phone = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> countryFlag = new MutableLiveData<>();
    public MutableLiveData<String> countryCode = new MutableLiveData<>();

    public MutableLiveData<Integer> phoneError = new MutableLiveData<>();
    public MutableLiveData<Integer> passwordError = new MutableLiveData<>();

    public TextChange phoneTextChange = value -> phoneError.setValue(0);
    public TextChange passwordTextChange = value -> passwordError.setValue(0);

    public LoginViewModel() {
        passwordError.setValue(0);
        countryCode.setValue("966");
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
        } else {
            passwordError.setValue(0);
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

    public void onClickLogin() {

        boolean v1 = validationPhone();
        boolean v2 = validationPassword();

        if (v1 && v2) {
            doAction.setValue(ACTION_GO_PROFILE);
        }
    }

    public void makeLoginRequest() {
        String phoneValue = phone.getValue();
        String passwordValue = password.getValue();

    }

    public void onClickPhone() {
        doAction.setValue(AppAction.ACTION_COUNTRY_CODE);
    }

    public void onClickNewDriver() {
        doAction.setValue(AppAction.ACTION_DRIVER_SIGN_UP);
    }

    public void onClickNewRestaurant() {
        doAction.setValue(AppAction.ACTION_RESTAURANT_SIGN_UP);
    }

    public void onClickForgetPassword() {
        doAction.setValue(AppAction.ACTION_FORGET_PASSWORD);
    }

    public void onSelectCountryCode(int which) {
        countryFlag.setValue(countryCodes.get(which).getImage());
        countryCode.setValue(countryCodes.get(which).getCountryCode());
    }


}
