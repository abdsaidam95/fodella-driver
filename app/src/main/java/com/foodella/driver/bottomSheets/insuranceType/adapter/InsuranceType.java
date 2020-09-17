package com.foodella.driver.bottomSheets.insuranceType.adapter;

public class InsuranceType {

    private String insuranceName = "";

    boolean isSelected = false;

    public InsuranceType() {
    }

    public InsuranceType(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public InsuranceType(String cityName, boolean isSelected) {
        this.insuranceName = cityName;
        this.isSelected = isSelected;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
