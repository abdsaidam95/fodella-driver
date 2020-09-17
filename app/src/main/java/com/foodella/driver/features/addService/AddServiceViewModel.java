package com.foodella.driver.features.addService;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.features.main.services.Services;
import com.foodella.driver.utils.AppAction;
import com.foodella.driver.utils.dataBinding.TextChange;
import com.foodella.driver.utils.eventBusModel.ActionEvent;

import static com.foodella.driver.utils.AppAction.GO_TO_SERVICE_FRAGMENT;

public class AddServiceViewModel extends BaseViewModel {
    private Boolean isFromCity;
    public int selectedFromCityPosition = -1;
    public int selectedToCityPosition = -1;

    public Services services;
    public MutableLiveData<String> cityFrom = new MutableLiveData<>();
    public MutableLiveData<String> cityTo = new MutableLiveData<>();
    public MutableLiveData<String> weight = new MutableLiveData<>();
    public MutableLiveData<String> price = new MutableLiveData<>();
    public MutableLiveData<String> overWeight = new MutableLiveData<>();
    public MutableLiveData<Integer> textByttom = new MutableLiveData<>();
    public MutableLiveData<String> priceWight = new MutableLiveData<>();
    

    public MutableLiveData<Integer> weightError = new MutableLiveData<>();
    public MutableLiveData<Integer> priceError = new MutableLiveData<>();
    public MutableLiveData<Integer> overWeightError = new MutableLiveData<>();
    public MutableLiveData<Integer> priceWeightError = new MutableLiveData<>();


    public MutableLiveData<Integer> viableErrorTextCity = new MutableLiveData<>();
    public MutableLiveData<Integer> viableErrorTextCityRecive = new MutableLiveData<>();

    public TextChange weightTextChange = value -> weightError.setValue(0);
    public TextChange priceTextChange = value -> priceError.setValue(0);
    public TextChange overWeightTextChange = value -> overWeightError.setValue(0);
    public TextChange priceWeightTextChange = value -> priceWeightError.setValue(0);

    public AddServiceViewModel() {
        textByttom.setValue(R.string.add);
        weightError.setValue(0);
        priceError.setValue(0);
        overWeightError.setValue(0);
        priceWeightError.setValue(0);

        viableErrorTextCity.setValue(View.GONE);
        viableErrorTextCityRecive.setValue(View.GONE);

    }

    private boolean validationWight() {
        if (TextUtils.isEmpty(weight.getValue())) {
            weightError.setValue((R.string.please_allowable_weight));
            return false;
        } else {
            weightError.setValue(0);
            return true;
        }
    }

    private boolean validationPrice() {
        if (TextUtils.isEmpty(price.getValue())) {
            priceError.setValue((R.string.please_allowable_price));
            return false;
        } else {
            priceError.setValue(0);
            return true;
        }
    }

    private boolean validationOverWeight() {
        if (TextUtils.isEmpty(overWeight.getValue())) {
            overWeightError.setValue((R.string.please_allowable_over_weight));
            return false;
        } else {
            overWeightError.setValue(0);
            return true;
        }

    }

    private boolean validationWeightPrice() {
        if (TextUtils.isEmpty(priceWight.getValue())) {
            priceWeightError.setValue((R.string.please_allowable_price_weight));
            return false;
        } else {
            priceWeightError.setValue(0);
            return true;
        }
    }

    public void onStart( ) {
    }

    public void onClickCityFFrom() {
        isFromCity = true;
        doAction.setValue(AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY);
    }

    public void onClickCityTo() {
        isFromCity = false;
        doAction.setValue(AppAction.ACTION_SHOW_BOTOOM_SHEET_CITY);
    }

    public void onClickButtonAddService() {
        String cityFromValue = cityFrom.getValue();
        String cityToValue = cityTo.getValue();
        if (cityFromValue == null || TextUtils.isEmpty(cityFromValue)) {
            viableErrorTextCity.setValue(View.VISIBLE);
        } else {
            viableErrorTextCity.setValue(View.GONE);
        }

        if (cityToValue == null || TextUtils.isEmpty(cityToValue)) {
            viableErrorTextCityRecive.setValue(View.VISIBLE);
        } else {
            viableErrorTextCityRecive.setValue(View.GONE);
        }
        boolean v1 = validationWight();
        boolean v2 = validationPrice();
        boolean v3 = validationOverWeight();
        boolean v4 = validationWeightPrice();
        if (v1 && v2 && v3 && v4 && cityFromValue != null && cityToValue != null) {
            doAction.setValue(GO_TO_SERVICE_FRAGMENT);
        }

    }
    public Services getServices(){
        services=new Services(cityFrom.getValue(),cityTo.getValue(),price.getValue(),weight.getValue(),priceWight.getValue());
        return services;
    }

    public void onSelectedCity(ActionEvent actionEvent) {
        City city = (City) actionEvent.getData();

        if (isFromCity) {
            selectedFromCityPosition = actionEvent.getPosition();
            cityFrom.setValue(city.getCityName());
        } else {
            cityTo.setValue(city.getCityName());
            selectedToCityPosition = actionEvent.getPosition();
        }
    }


    public int getCitySelectedPosition() {
        return isFromCity ? selectedFromCityPosition : selectedToCityPosition;

    }

    // TODO: 23-Aug-20 make add service
    public void makeAddserviceRequest(String cityFrom,String cityTo,String weight,String price,String overWeight,String priceWight ){

    }

    public void setInisalizeData(Services service) {
            cityFrom.setValue(service.getCityFrom());
            cityTo.setValue(service.getCityTo());
            price.setValue(service.getPricePerKilomitars());
            weight.setValue(service.getMaximumWeight());
            overWeight.setValue(service.getMaximumWeight());
            priceWight.setValue(service.getCrumbprice());



    }
}
