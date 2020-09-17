package com.foodella.driver.features.main.orders.adapter;

public class Order {
    private String dateOrder;
    private String dateRecive;
    private String delivaryTime;
    private String typeOrder;

    public String getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }

    private String priceOrder;

    public Order(String dateOrder, String dateRecive, String delivaryTime, String typeOrder) {
        this.dateOrder = dateOrder;
        this.dateRecive = dateRecive;
        this.delivaryTime = delivaryTime;
        this.typeOrder = typeOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDateRecive() {
        return dateRecive;
    }

    public void setDateRecive(String dateRecive) {
        this.dateRecive = dateRecive;
    }

    public String getDelivaryTime() {
        return delivaryTime;
    }

    public void setDelivaryTime(String delivaryTime) {
        this.delivaryTime = delivaryTime;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }
}
