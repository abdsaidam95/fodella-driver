package com.foodella.driver.network;

import android.util.ArrayMap;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


public class RequestManager {

    private static AsyncHttpClient mAsyncHttpClient;

    public static void initialize() {
        mAsyncHttpClient = new AsyncHttpClient();
        setHeaders();
    }

    public void makeGetRequest(String url, ResponseHandler responseHandler) {
        mAsyncHttpClient.get(url, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                responseHandler.onSuccess(responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                responseHandler.onFailure(responseString);
            }
        });
    }

    public void makePostRequest(String url, RequestParams params, ResponseHandler responseHandler) {

        mAsyncHttpClient.post(url, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                responseHandler.onSuccess(responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                responseHandler.onFailure(responseString);
            }
        });
    }

    private static void setHeaders() {
        ArrayMap<String, String> headers = RequestHeaders.getHeaders();
        for (String key : headers.keySet()) {
            mAsyncHttpClient.addHeader(key, headers.get(key));
        }
    }

}
