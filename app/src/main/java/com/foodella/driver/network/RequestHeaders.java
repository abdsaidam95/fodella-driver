package com.foodella.driver.network;

import android.util.ArrayMap;

public class RequestHeaders {

    public static ArrayMap<String, String> getHeaders() {
        ArrayMap<String, String> headers = new ArrayMap<>();

        headers.put("Accept-Language", "ar/en");
        headers.put("Content-Type", "Android");
        headers.put("Platform", "Android");
//        if (isUserLoggedIn) {
//            headers.put("Auth-Token", ""); ///get Local Storage
//        }
        return headers;
    }
}
