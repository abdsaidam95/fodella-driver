package com.foodella.driver.features.profile.services;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Objects;

public class ServicesViewModel extends BaseViewModel {

    public MutableLiveData<String> weight = new MutableLiveData<>();
    public MutableLiveData<String> price = new MutableLiveData<>();
    public MutableLiveData<String> city = new MutableLiveData<>();
    public MutableLiveData<Boolean> isVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> errorVisible = new MutableLiveData<>();

    public MutableLiveData<Integer> cityError = new MutableLiveData<>();
    public MutableLiveData<Integer> weightError = new MutableLiveData<>();
    public MutableLiveData<Integer> priceError = new MutableLiveData<>();

    public MutableLiveData<ArrayList<City>> selectedCities = new MutableLiveData<>();

    public TextChange weightTextChange = value -> weightError.setValue(0);
    public TextChange priceTextChange = value -> priceError.setValue(0);

    public View.OnClickListener onClickChipListener = v -> {
        ArrayList<City> cityArrayList = Objects.requireNonNull(selectedCities.getValue());

        Object tag = v.getTag();
        if (tag instanceof Integer) {
            Integer position = (Integer) tag;
            cityArrayList.remove(position.intValue());
        }

        selectedCities.setValue(cityArrayList);
    };

    public ServicesViewModel() {
        isVisible.setValue(false);
        errorVisible.setValue(false);
        selectedCities.setValue(new ArrayList<City>());
    }

    public void onStart() {
    }

    public void onClickBackArrow() {

    }

    public void makeServiceRequest() {

        String weightValue = weight.getValue();
        String priceValue = price.getValue();

    }

    public void onClickAvailableCities() {
        doAction.setValue(AppAction.ACTION_CHOOSE_CITIES);
    }

    public void onClickNext() {
        Boolean v1 = validationWeight();
        Boolean v2 = validationPrice();
        Boolean v3 = validationCity();

        if (v1 && v2 && v3) {
            EventBus.getDefault().post(new ActionEvent(AppAction.ACTION_GO_CARLIST));
        }

    }


    public boolean validationCity() {
        if (city.getValue() == null) {
            errorVisible.setValue(true);
            cityError.setValue(R.string.please_select_available_cities);
            return false;
        } else {
            errorVisible.setValue(false);
            cityError.setValue(0);
            return true;
        }
    }

    private boolean validationWeight() {
        if (TextUtils.isEmpty(weight.getValue())) {
            weightError.setValue((R.string.please_enter_phone_number));
            return false;
        } else {
            weightError.setValue(0);
            return true;
        }
    }

    private boolean validationPrice() {
        if (TextUtils.isEmpty(price.getValue())) {
            priceError.setValue((R.string.please_enter_phone_number));
            return false;
        } else {
            priceError.setValue(0);
            return true;
        }
    }

    public void onSelectCities(ArrayList<City> cities) {
        selectedCities.setValue(cities);
        int size = cities.size();
        if (size > 0) {
            isVisible.setValue(true);
            errorVisible.setValue(false);
            city.setValue("");
        } else {
            isVisible.setValue(false);
            errorVisible.setValue(true);
            city.setValue(null);
        }
    }
}