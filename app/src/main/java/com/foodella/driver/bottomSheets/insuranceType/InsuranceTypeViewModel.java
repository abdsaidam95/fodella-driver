package com.foodella.driver.bottomSheets.insuranceType;

import androidx.lifecycle.MutableLiveData;

import com.foodella.driver.R;
import com.foodella.driver.base.BaseViewModel;
import com.foodella.driver.bottomSheets.insuranceType.adapter.InsuranceType;
import com.foodella.driver.bottomSheets.insuranceType.adapter.InsuranceTypesAdapter;
import com.foodella.driver.utils.AppAction;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Objects;

import static com.foodella.driver.utils.AppAction.ACTION_BACK;

public class InsuranceTypeViewModel extends BaseViewModel {

    private ArrayList<InsuranceType> items = new ArrayList<>();
    public MutableLiveData<InsuranceTypesAdapter> insuranceTypesAdapter = new MutableLiveData<>();


    public InsuranceTypeViewModel() {
        emptyColor.setValue(R.color.colorWhite);
        insuranceTypesAdapter.setValue(new InsuranceTypesAdapter());
        bottomSheetState.setValue(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void onStart() {
        setVehicles();
    }

    private void setVehicles() {
        items.clear();
        items.add(new InsuranceType("InsuranceType1"));
        items.add(new InsuranceType("InsuranceType2"));
        items.add(new InsuranceType("InsuranceType3"));
        items.add(new InsuranceType("InsuranceType4"));
        items.add(new InsuranceType("InsuranceType5"));
        Objects.requireNonNull(insuranceTypesAdapter.getValue()).setData(items);
    }

    public void onClickClose() {
        doAction.setValue(ACTION_BACK);
    }

    public void onSelectVehicle(int position) {
        Objects.requireNonNull(insuranceTypesAdapter.getValue()).setSelectedPosition(position);
    }

    public void onClickSave() {
        doAction.setValue(AppAction.ACTION_GET_INSURANCE_TYPE);
    }
}
