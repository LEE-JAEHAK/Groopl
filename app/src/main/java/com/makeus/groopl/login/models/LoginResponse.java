package com.makeus.groopl.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("jwt")
    String jwt;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;


    public String getJwt() {
        return jwt;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
