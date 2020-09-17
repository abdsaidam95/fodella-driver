package com.foodella.driver.features.main.payments.adapter;

import java.io.Serializable;

public class Payment implements Serializable {
    private String orderDate;
    private String orderPrice;
    private String deliveryPrice;
    private String orderStatus;

    public Payment() {
    }

    public Payment(String orderDate, String orderPrice, String deliveryPrice, String orderStatus) {
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
