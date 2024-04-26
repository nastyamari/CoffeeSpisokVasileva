package com.example.coffeespisokvasileva;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface CoffeeAPI {
    @GET("coffee/hot")
    Call<List<Item>> getData();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
