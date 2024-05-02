package com.example.pbday9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pbday9.data.ApiClient;
import com.example.pbday9.data.ApiInterface;
import com.example.pbday9.data.model.Register;
import com.example.pbday9.databinding.ActivityLoginBinding;
import com.example.pbday9.databinding.ActivityRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLogin.setOnClickListener( view -> {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        });

        binding.btnRegister.setOnClickListener(view -> {
            String Username = binding.etLoginR.getText().toString();
            String Name = binding.etNameR.getText().toString();
            String Password = binding.etPassR.getText().toString();
            register(Username, Name, Password);
        });
    }

    private void register(String username, String name, String password) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.RegisterResponse(username, name, password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.isSuccessful() && response.body() != null) {
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(RegisterActivity.this, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show();
                    startActivity(i);

                } else {
                    Toast.makeText(RegisterActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal Register : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}