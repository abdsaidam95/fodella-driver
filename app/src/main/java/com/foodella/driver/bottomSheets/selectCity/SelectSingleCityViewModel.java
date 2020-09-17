package com.foodella.driver.bottomSheets.selectCity;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.selectCity.adapter.SingleCitiesAdapter;
import com.foodella.driver.bottomSheets.selectMultiCity.model.City;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Objects;

import static com.foodella.driver.utils.AppAction.ACTION_DISMESs_CITY_BUTTOM;
import static com.foodella.driver.utils.AppAction.ACTION_GET_CITY;

public class SelectSingleCityViewModel extends BaseViewModel {

    private ArrayList<City> items = new ArrayList<>();
    public MutableLiveData<SingleCitiesAdapter> adapter = new MutableLiveData<>();

    public SelectSingleCityViewModel() {
        emptyColor.setValue(R.color.colorWhite);
        adapter.setValue(new SingleCitiesAdapter());
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart() {
        setCities();
    }

    // TODO: 20-Aug-20 request list of city
    public void makeGetCitiesRequest(){

    }


    private void setCities() {
        items.clear();
        items.add(new City("maka"));
        items.add(new City("madina"));
        items.add(new City("alread"));
        items.add(new City("maka"));
        items.add(new City("maka"));
        Objects.requireNonNull(adapter.getValue()).setData(items);
    }

    public void onClick() {
        doAction.setValue(ACTION_DISMESs_CITY_BUTTOM);
    }

    public void onSelectCity(int position) {
        Objects.requireNonNull(adapter.getValue()).setSelectedPosition(position);
    }

    public void onClickSave(){
        doAction.setValue(ACTION_GET_CITY);

    }
}
