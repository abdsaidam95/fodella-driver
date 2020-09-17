package com.foodella.driver.utils;

public enum OrderStatus {

    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    HISTORY("HISTORY"),
    RETURNED("RETURNED"),
    ;

    private final String action;

    OrderStatus(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
