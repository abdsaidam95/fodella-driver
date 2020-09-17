package com.foodella.driver.bottomSheets.selectMultiCity.model;

import java.io.Serializable;

public class City implements Serializable {

    private String cityName = "";
    boolean isSelected = false;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(String cityName, boolean isSelected) {
        this.cityName = cityName;
        this.isSelected = isSelected;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
