package com.stud.web;

import android.content.Context;
import android.support.annotation.NonNull;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.stud.helper.StringHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class BaseWebService {

    public final static String CONTENT_TYPE = "application/json";

    public AsyncHttpClient getHttpClient() {
        return null;
    }

    public void get(Context context, String url, Header[] headers, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (responseHandler == null)
            responseHandler = getAsyncHttpResponseHandler();

        getHttpClient().get(context, url, headers, params, responseHandler);
    }

    @NonNull
    private AsyncHttpResponseHandler getAsyncHttpResponseHandler() {
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = StringHelper.convertByteArrayToString(responseBody);
//                if (s == null)//made for debugging only
//                    return;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                String s = StringHelper.convertByteArrayToString(responseBody);
//                if (s == null)//made for debugging only
//                    return;
            }
        };
        return responseHandler;
    }

    public void post(Context context, String url, Header[] headers, String jsonParams, AsyncHttpResponseHandler responseHandler)
            throws IOException {
        if (headers == null)
            headers = new Header[]{};

        if (responseHandler == null)
            responseHandler = getAsyncHttpResponseHandler();

        StringEntity stringEntity = makeStringEntity(jsonParams);
        getHttpClient().post(context, url, headers, stringEntity, CONTENT_TYPE, responseHandler);
    }

    public void put(Context context, String url, Header[] headers, String jsonParams, AsyncHttpResponseHandler responseHandler)
            throws IOException {
        if (headers == null)
            headers = new Header[]{};

        if (responseHandler == null)
            responseHandler = getAsyncHttpResponseHandler();

        StringEntity stringEntity = makeStringEntity(jsonParams);
        getHttpClient().put(context, url, headers, stringEntity, CONTENT_TYPE, responseHandler);
    }

    public void delete(Context context, String url, Header[] headers, RequestParams params, AsyncHttpResponseHandler responseHandler)
            throws IOException {
        if (headers == null)
            headers = new Header[]{};

        if (responseHandler == null)
            responseHandler = getAsyncHttpResponseHandler();

        getHttpClient().delete(context, url, headers, params, responseHandler);
    }

    public StringEntity makeStringEntity(String jsonParams) throws UnsupportedEncodingException {
        StringEntity stringEntity = new StringEntity(jsonParams, "utf-8");
        return stringEntity;
    }
}
