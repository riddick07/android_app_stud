package com.stud;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.stud.dto.DataDto;
import com.stud.helper.DataAdapter;
import com.stud.web.AppWebService;
import com.stud.web.WebService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int ONE_DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 24;

    AppWebService webService;

    ListView lvData;
    DataAdapter dataAdapter;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = getRetrofit();

        webService = retrofit.create(AppWebService.class);

        gson = new Gson();
        dataAdapter = new DataAdapter(MainActivity.this, new ArrayList<DataDto>());

        ListView lvData = findViewById(R.id.lvData);

        lvData.setAdapter(dataAdapter);

        new CountDownTimer(ONE_DAY_IN_MILLISECONDS, 3000) {

            public void onTick(long millisUntilFinished) {
                updateData();
            }

            public void onFinish() {
            }

        }.start();
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                    .baseUrl(WebService.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

    private void updateData() {
        Response<List<DataDto>> execute = null;
        try {
            execute = webService.getData().execute();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<DataDto> data = execute.body();

        if (data != null && !data.isEmpty()) {
            initList(data);
        }

//        webService.getData(MainActivity.this, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                String s = StringHelper.convertByteArrayToString(responseBody);
//
//                Type listType = new TypeToken<ArrayList<DataDto>>() {
//                }.getType();
//
//                List<DataDto> list = gson.fromJson(s, listType);
//                initList(list);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                String s = StringHelper.convertByteArrayToString(responseBody);
//                Log.d("ERROR", s);
//            }
//        });
    }

    public void initList(final List<DataDto> list) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dataAdapter.setItems(list);
            }
        });

    }


}