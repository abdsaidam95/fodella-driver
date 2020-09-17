package com.foodella.driver.features.main.services;

import java.io.Serializable;

public class Services implements Serializable {
    private String cityFrom;
    private String cityTo;
    private String crumbprice;
    private String maximumWeight;
    private String pricepricePerkilometer;

    public Services(String cityFrom, String cityTo, String crumbprice,String maximumWeight,String pricePerKilomitars) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.crumbprice = crumbprice;
        this.maximumWeight = maximumWeight;
        this.pricepricePerkilometer = pricePerKilomitars;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getCrumbprice() {
        return crumbprice;
    }

    public void setCrumbprice(String crumbprice) {
        this.crumbprice = crumbprice;
    }

    public String getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(String maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public String getPricePerKilomitars() {
        return pricepricePerkilometer;
    }

    public void setPricePerKilomitars(String pricePerKilomitars) {
        this.pricepricePerkilometer = pricePerKilomitars;
    }
}
