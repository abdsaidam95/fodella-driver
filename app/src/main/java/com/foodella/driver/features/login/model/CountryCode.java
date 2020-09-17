package com.foodella.driver.features.login.model;

import java.io.Serializable;

public class CountryCode implements Serializable {

    private String image = "";
    private String countryCode = "";

    public CountryCode() {
    }

    public CountryCode(String image, String countryCode) {
        this.image = image;
        this.countryCode = countryCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return image + "" + countryCode;
    }


}
