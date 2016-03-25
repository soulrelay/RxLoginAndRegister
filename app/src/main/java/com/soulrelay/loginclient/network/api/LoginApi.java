package com.soulrelay.loginclient.network.api;

import com.soulrelay.loginclient.model.ResultReturn;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by sushuai on 2016/3/25.
 */
public interface LoginApi {
    @FormUrlEncoded
    @POST("/LoginServer/login.php")
    Observable<ResultReturn> login(@Field("email") String email, @Field("password") String password);
}
