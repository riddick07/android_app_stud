package com.stud.helper;

import com.loopj.android.http.AsyncHttpClient;

public class AsyncHttpHelperHelper {
    private AsyncHttpClient asyncHttpClient;

    public AsyncHttpHelperHelper() {
        this.asyncHttpClient = new AsyncHttpClient(true, 80, 443);

        this.asyncHttpClient.setConnectTimeout(5000);
    }

    public AsyncHttpClient getAsyncHttpClient() {
        return asyncHttpClient;
    }

    public void setAsyncHttpClient(AsyncHttpClient asyncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
    }
}
