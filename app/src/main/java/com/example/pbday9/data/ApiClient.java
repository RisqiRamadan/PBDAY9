package com.example.pbday9.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://lazykoding.com/logreg/";
    private static Retrofit retrofit;

    public static  Retrofit getClient () {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
