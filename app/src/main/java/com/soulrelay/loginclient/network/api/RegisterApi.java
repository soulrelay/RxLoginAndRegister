package com.soulrelay.loginclient.network.api;

import com.soulrelay.loginclient.model.ResultReturn;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by sushuai on 2016/3/25.
 */
public interface RegisterApi {
    @FormUrlEncoded
    @POST("/LoginServer/register.php")
    Observable<ResultReturn> register(@Field("name") String name, @Field("email") String email, @Field("contact") String contact, @Field("password") String password);
}
