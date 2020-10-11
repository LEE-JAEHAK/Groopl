package com.makeus.groopl.group.models;

import com.google.gson.annotations.SerializedName;

public class PostGroupResponse {

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

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
