package com.devidea.grigoapplication;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    //수정해야합니다.
    TokenManager tokenManager = new TokenManager();

    public JsonObject jsonObject;
    public JsonObject jsonObjectLogin;
    public JsonObject testJson;

    public RetrofitService retrofitService = retrofit.create(RetrofitService.class);
    public static final String BASE_URL = "http://solac.iptime.org:1234/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor("Bearer " + authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }

    public void signup() {
        jsonObject.addProperty("email", "test@naver.com");
        jsonObject.addProperty("password", "12345678a");
        jsonObject.addProperty("name", "jun");
        jsonObject.addProperty("birth", "980123");
        jsonObject.addProperty("student_id", "60172213");
        jsonObject.addProperty("sex", "man");
        jsonObject.addProperty("phone", "01012345678");

        retrofitService.setPostBody(jsonObject).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("서버", String.valueOf(response.body()));

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("서버", "실패");
            }
        });
    }

    public void login() {
        jsonObjectLogin = new JsonObject();

        jsonObjectLogin.addProperty("email", "solchan@gmail.com");
        jsonObjectLogin.addProperty("password", "12345678");

        retrofitService.login(jsonObjectLogin).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //Headers headers = response.headers();
                //System.out.println(response.headers());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("서버", "실패");
            }
        });


    }

    public void test() {
        //retrofitService = createService(RetrofitService.class, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2xjaGFuQGdtYWlsLmNvbSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNjI1OTQ1MDg2LCJleHAiOjE2MjU5NDg2ODZ9.uzw29Eiauodw6PQgQAYruWMR37q8rGaaJpY2o6Mf5Sygx9lnU7UJrThjhia_efVedHG2ppzcLmI8zl3eqnvMEQ");

        retrofitService.getToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2xjaGFuQGdtYWlsLmNvbSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNjI1OTQ1MDg2LCJleHAiOjE2MjU5NDg2ODZ9.uzw29Eiauodw6PQgQAYruWMR37q8rGaaJpY2o6Mf5Sygx9lnU7UJrThjhia_efVedHG2ppzcLmI8zl3eqnvMEQ").enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {

            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

            }
        });
    }

}