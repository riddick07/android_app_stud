package com.stud.web;

import com.stud.dto.DataDto;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppWebService {

    @GET("/getData")
    Call<List<DataDto>> getData();

}
