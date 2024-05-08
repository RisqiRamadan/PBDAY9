package com.example.pbday9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.pbday9.data.ApiClient;
import com.example.pbday9.data.ApiInterface;
import com.example.pbday9.data.model.Login;
import com.example.pbday9.data.model.LoginData;
import com.example.pbday9.data.sessionManager;
import com.example.pbday9.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private String Username;
    private String Password;
    private sessionManager sessionmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvCNAccount.setOnClickListener(v -> {
            if (v.getId() == R.id.tvCNAccount){
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnlogin.setOnClickListener(v -> {
            Username = binding.etLoginL.getText().toString();
            Password = binding.etRegisterL.getText().toString();
            login(Username, Password);
        });
    }

    public void login(String Username, String Password){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> call = apiService.LoginResponse(Username,Password);
        call.enqueue(new Callback<Login>(){

            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()){

                    sessionmanager = new sessionManager(LoginActivity.this);
                    LoginData loginData = response.body().getData();
                    sessionmanager.loginSession(loginData);

                    Snackbar.make(findViewById(android.R.id.content), "Berhasil Login", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Snackbar.make(findViewById(android.R.id.content), "Gagal Login : " + response.message(), Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "Gagal Login : " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}