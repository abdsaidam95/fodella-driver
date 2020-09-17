package com.foodella.driver.bottomSheets.selectMultiCity;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.selectMultiCity.adapter.CityAdapter;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.foodella.driver.utils.AppAction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Objects;

import static com.foodella.driver.utils.AppAction.ACTION_SAVE;

public class SelectCityViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> isPhotosEmpty = new MutableLiveData<>();
    public MutableLiveData<CityAdapter> citiesAdapter = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();


    public SelectCityViewModel() {
        emptyColor.setValue(R.color.colorWhite);
        isRefreshing.setValue(false);
        isPhotosEmpty.setValue(false);
        citiesAdapter.setValue(new CityAdapter());
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }


    public void onStart(ArrayList<City> selectedCities) {
        Objects.requireNonNull(citiesAdapter.getValue()).setSelectedCities(selectedCities);
        getCities();
    }

    public void getCities() {
        isLoading.setValue(true);
        isRefreshing.setValue(true);
        setData();
    }

    public void setData() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("مكة", false));
        cities.add(new City("جدة", false));
        cities.add(new City("مكة", false));
        cities.add(new City("جدة", false));

        Objects.requireNonNull(citiesAdapter.getValue()).setData(cities);
        Objects.requireNonNull(citiesAdapter.getValue()).appendData(cities);
    }

    public void makeGetCitiesRequest() {

    }

    public void onClickCloseArrow() {
        doAction.setValue(AppAction.ACTION_BACK);
    }

    public void onSelectCity(int position) {
        Objects.requireNonNull(citiesAdapter.getValue()).setSelectedPosition(position);
    }

    public ArrayList<City> getSelectedCities() {
        return Objects.requireNonNull(citiesAdapter.getValue()).getSelectedCities();
    }

    public void onClickSave() {

        if(Objects.requireNonNull(citiesAdapter.getValue()).getSelectedCities().isEmpty()){
            showMessageRes.setValue(R.string.please_select_available_cities);
        }else{
            doAction.setValue(ACTION_SAVE);
        }
    }
}
