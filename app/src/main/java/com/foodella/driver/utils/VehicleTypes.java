package com.foodella.driver.utils;

public enum VehicleTypes {

    TYPE_CAR (1),
    TYPE_BUS (2),
    TYPE_BICYCLE (3),
    ;

    private final int type;

    VehicleTypes(int action) {
        this.type = action;
    }
    public int getType() {
        return type;
    }
}
