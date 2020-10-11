package com.makeus.groopl.login.interfaces;

import com.makeus.groopl.login.models.LoginResponse;
import com.makeus.groopl.login.models.RequestUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/signin/kakao")
    Call<LoginResponse> postUser(@Body RequestUser params);
}
