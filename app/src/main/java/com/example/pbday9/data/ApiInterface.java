package com.example.pbday9.data;

import com.example.pbday9.data.model.Login;
import com.example.pbday9.data.model.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> LoginResponse (
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> RegisterResponse (
            @Field("username") String username,
            @Field("name") String name,
            @Field("password") String password
    );
}
