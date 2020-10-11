package com.makeus.groopl.login.models;

import com.google.gson.annotations.SerializedName;

public class RequestUser {
    @SerializedName("access_token")
    public String access_token;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
