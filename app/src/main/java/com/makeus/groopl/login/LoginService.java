package com.makeus.groopl.login;


import com.makeus.groopl.login.interfaces.LoginActivityView;
import com.makeus.groopl.login.interfaces.LoginRetrofitInterface;
import com.makeus.groopl.login.models.LoginResponse;
import com.makeus.groopl.login.models.RequestUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.groopl.src.ApplicationClass.getRetrofit;


public class LoginService {
    final LoginActivityView loginActivityView;

    public LoginService(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
    }

    void postUser(RequestUser requestUser) {
        System.out.println("postuser access token : " + requestUser.access_token);
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postUser(requestUser).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    loginActivityView.validateUserFail(response.message());
                    return;
                }
                System.out.println("loginservice : " + loginResponse.getJwt());
                loginActivityView.validateUserSuccess(loginResponse.getJwt(), loginResponse.getIsSuccess(), loginResponse.getCode(), loginResponse.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginActivityView.validateUserFail(t.getMessage());
            }
        });
    }
}
