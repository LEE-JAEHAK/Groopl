package com.makeus.groopl.group.interfaces;


import com.makeus.groopl.group.models.GroupResponse;

import java.util.ArrayList;


public interface GroupActivityView {
    void validateGroupSuccess(ArrayList<GroupResponse.Result> result, boolean isSuccess, int code, String message);

    void validateGroupFail(String msg);

    void validatepostGroupSuccess(boolean isSuccess, int code, String message);

    void validatepostGroupFail(String msg);
}
