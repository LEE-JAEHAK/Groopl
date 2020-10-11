package com.makeus.groopl.group.interfaces;


import com.makeus.groopl.group.models.GroupResponse;
import com.makeus.groopl.group.models.RequestGroup;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GroupRetrofitInterface {
    @GET("/groups")
    Call<GroupResponse> getgroups();

    @POST("/group")
    Call<GroupResponse> postGroup(@Body RequestGroup params);
}
