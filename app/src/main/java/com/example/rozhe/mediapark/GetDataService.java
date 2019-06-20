package com.example.rozhe.mediapark;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/api/mobile/public/availablecars")
    Call<List<Info>> getAllCars();
}
