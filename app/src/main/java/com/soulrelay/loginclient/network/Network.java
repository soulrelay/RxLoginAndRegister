package com.soulrelay.loginclient.network;

import com.soulrelay.loginclient.constant.UrlContainer;
import com.soulrelay.loginclient.network.api.LoginApi;
import com.soulrelay.loginclient.network.api.RegisterApi;
import com.squareup.okhttp.OkHttpClient;

import retrofit.CallAdapter;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class Network {
    private static LoginApi loginApi;
    private static RegisterApi registerApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static LoginApi getloginApi() {
        if (loginApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(UrlContainer.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            loginApi = retrofit.create(LoginApi.class);
        }
        return loginApi;
    }

    public static RegisterApi getRegisterApi() {
        if (registerApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(UrlContainer.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            registerApi = retrofit.create(RegisterApi.class);
        }
        return registerApi;
    }

}
