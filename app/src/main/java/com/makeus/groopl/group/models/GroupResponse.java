package com.makeus.groopl.group.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GroupResponse {

    @SerializedName("result")
    ArrayList<Result> result;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    public class Result {
        @SerializedName("groupIdx")
        int groupIdx;

        @SerializedName("groupName")
        String groupName;

        @SerializedName("groupImage")
        String groupImage;

        @SerializedName("groupMember")
        String groupMember;

        public int getGroupIdx() {
            return groupIdx;
        }

        public String getGroupName() {
            return groupName;
        }

        public String getGroupImage() {
            return groupImage;
        }

        public String getGroupMember() {
            return groupMember;
        }
    }

    public ArrayList<Result> getResult() {
        return result;
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
