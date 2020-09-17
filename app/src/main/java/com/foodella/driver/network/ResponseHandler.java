package com.foodella.driver.network;

public interface ResponseHandler {

    void onSuccess(String response);

    void onFailure(String error);

}
