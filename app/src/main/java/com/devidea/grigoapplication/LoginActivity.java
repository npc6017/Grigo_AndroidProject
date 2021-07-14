package com.devidea.grigoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login, btn_join;
    private EditText et_id, et_pw;

    ServiceGenerator serviceGenerator;
    TokenManager tokenManager;
    RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PrefsHelper.init(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        serviceGenerator = new ServiceGenerator();
        retrofitService = serviceGenerator.createService(RetrofitService.class);
        tokenManager = new TokenManager();

        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);

        //회원가입 버튼
        btn_join = findViewById(R.id.btn_join);
        btn_join.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
            startActivity(intent);
        });

        //로그인 시도
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(view -> {

            login(et_id.getText().toString(), et_pw.getText().toString());

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void login(String user_id, String pw) {
        JsonObject jsonObjectLogin = new JsonObject();

        jsonObjectLogin.addProperty("email", user_id);
        jsonObjectLogin.addProperty("password", pw);

        retrofitService.login(jsonObjectLogin).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Headers headers = response.headers();
                String token = headers.get("Authorization");

                System.out.println(response.headers());

                tokenManager.set(token);

                Log.d("token", tokenManager.get());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


    }

}