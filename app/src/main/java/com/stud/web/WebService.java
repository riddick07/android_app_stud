package com.stud.web;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.stud.helper.AsyncHttpHelperHelper;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class WebService extends BaseWebService {

    public static final String URL = "http://192.168.178.70:8081/api/";
    public static final String DATA_REQUEST = "getData";

    AsyncHttpHelperHelper httpClient;

    public WebService() {
        this.httpClient = new AsyncHttpHelperHelper();
    }

    @Override
    public AsyncHttpClient getHttpClient() {
        AsyncHttpClient asyncHttpClient = httpClient.getAsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        return asyncHttpClient;
    }

    public Header[] getDataHeaders() {
        Header[] headers = {
                new BasicHeader("Accept", "application/json"),
                new BasicHeader("Content-Type", "application/json")
        };
        return headers;
    }

    public void getData(Context context, AsyncHttpResponseHandler handler) {
        Header[] headers = getDataHeaders();

        String url = URL + DATA_REQUEST;
        get(context, url, headers, null, handler);
    }
}
