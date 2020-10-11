package com.makeus.groopl.group.models;

import com.google.gson.annotations.SerializedName;

public class RequestGroup {
    @SerializedName("name")
    public String name;

    public void setName(String name) {
        this.name = name;
    }
}
