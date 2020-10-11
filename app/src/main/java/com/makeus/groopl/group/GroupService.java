package com.makeus.groopl.group;


import com.makeus.groopl.group.interfaces.GroupActivityView;
import com.makeus.groopl.group.interfaces.GroupRetrofitInterface;
import com.makeus.groopl.group.models.GroupResponse;
import com.makeus.groopl.group.models.RequestGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.makeus.groopl.src.ApplicationClass.getRetrofit;


public class GroupService {
    final GroupActivityView groupActivityView;

    public GroupService(GroupActivityView groupActivityView) {
        this.groupActivityView = groupActivityView;
    }

    public void getGroup() {
        final GroupRetrofitInterface groupRetrofitInterface = getRetrofit().create(GroupRetrofitInterface.class);
        groupRetrofitInterface.getgroups().enqueue(new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                final GroupResponse groupResponse = response.body();
                groupActivityView.validateGroupSuccess(groupResponse.getResult(), groupResponse.getIsSuccess(), groupResponse.getCode(), groupResponse.getMessage());
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
                groupActivityView.validateGroupFail(t.getMessage());
            }
        });
    }

    public void postGroup(RequestGroup requestGroup) {
        final GroupRetrofitInterface groupRetrofitInterface = getRetrofit().create(GroupRetrofitInterface.class);
        groupRetrofitInterface.postGroup(requestGroup).enqueue(new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                final GroupResponse groupResponse = response.body();
                if (groupResponse == null) {
                    groupActivityView.validatepostGroupFail(response.message());
                    return;
                }
                groupActivityView.validatepostGroupSuccess(groupResponse.getIsSuccess(), groupResponse.getCode(), groupResponse.getMessage());
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
            }
        });
    }
}
