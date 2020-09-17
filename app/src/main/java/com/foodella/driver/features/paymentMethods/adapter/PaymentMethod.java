package com.foodella.driver.features.paymentMethods.adapter;

import java.io.Serializable;

public class PaymentMethod implements Serializable {

    private String paymentName = "";
    private String paymentDate = "";
    private String paymentImage = "";
    private String paymentNumber = "";
    private Boolean paymentSelection = false;

    public PaymentMethod() {
    }

    public PaymentMethod(String paymentName, String paymentDate, String paymentImage, String paymentNumber, Boolean paymentSelection) {
        this.paymentName = paymentName;
        this.paymentDate = paymentDate;
        this.paymentImage = paymentImage;
        this.paymentNumber = paymentNumber;
        this.paymentSelection = paymentSelection;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentImage() {
        return paymentImage;
    }

    public void setPaymentImage(String paymentImage) {
        this.paymentImage = paymentImage;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public Boolean getPaymentSelection() {
        return paymentSelection;
    }

    public void setPaymentSelection(Boolean paymentSelection) {
        this.paymentSelection = paymentSelection;
    }
}
