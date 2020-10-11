package com.makeus.groopl.group;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeus.groopl.HomeActivity;
import com.makeus.groopl.R;
import com.makeus.groopl.group.interfaces.GroupActivityView;
import com.makeus.groopl.group.models.GroupResponse;
import com.makeus.groopl.group.models.RequestGroup;
import com.makeus.groopl.src.BaseActivity;

import java.util.ArrayList;

import static com.makeus.groopl.src.ApplicationClass.sSharedPreferences;

public class Group extends BaseActivity implements GroupActivityView {
    Button mBtnCreate, yesbutton;
    FrameLayout mFlGrouppic;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
        String username = sSharedPreferences.getString("nickname","");
        TextView mTvUsername = findViewById(R.id.tv_group_username);
        mTvUsername.setText(username + "님이");

        GroupService groupService = new GroupService(this);
        groupService.getGroup();

        mFlGrouppic = findViewById(R.id.fl_group_pic);
        mFlGrouppic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next2();
            }
        });
        mBtnCreate = findViewById(R.id.btn_group_create);

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }


    void next() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        final EditText mEtName = sheetView.findViewById(R.id.et_bs_groupname);
        yesbutton = sheetView.findViewById(R.id.button);
        yesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mEtName.getText().toString();
                System.out.println("group name : " + name);
                postStart();
            }
        });
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }

    void postStart() {
        GroupService groupService = new GroupService(this);
        RequestGroup requestGroup = new RequestGroup();
        requestGroup.setName(name);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("groupname",name);
        editor.commit();
        groupService.postGroup(requestGroup);
    }

    void next2() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateGroupSuccess(ArrayList<GroupResponse.Result> result, boolean isSuccess, int code, String message) {
        if (isSuccess) {
            showCustomToast(message);
            System.out.println("groupsize : " + result.size());
            TextView textView,textView72,textView82;
            textView = findViewById(R.id.textView7);
            ImageView imageView, imageView1;
            imageView = findViewById(R.id.iv_group_pic);
            imageView1 = findViewById(R.id.iv_group_pic2);
            LinearLayout linearLayout;
            linearLayout = findViewById(R.id.ll_group);
            textView72 = findViewById(R.id.textView72);
            textView82=findViewById(R.id.textView82);
            Button button = findViewById(R.id.btn_group_create);
            if (result.size() == 0) {
                textView.setVisibility(TextView.VISIBLE);
                imageView.setVisibility(ImageView.VISIBLE);
                imageView1.setVisibility(ImageView.INVISIBLE);
                linearLayout.setVisibility(LinearLayout.INVISIBLE);
                button.setVisibility(Button.VISIBLE);
                mFlGrouppic.setEnabled(false);
            } else {
                textView.setVisibility(TextView.INVISIBLE);
                imageView.setVisibility(ImageView.INVISIBLE);
                textView82.setText(result.get(0).getGroupMember());
                textView72.setText(result.get(0).getGroupName());
                imageView1.setVisibility(ImageView.VISIBLE);
                linearLayout.setVisibility(LinearLayout.VISIBLE);
                button.setVisibility(Button.INVISIBLE);
                mFlGrouppic.setEnabled(true);
            }
        }
    }

    @Override
    public void validateGroupFail(String msg) {
        showCustomToast(msg);
    }

    @Override
    public void validatepostGroupSuccess(boolean isSuccess, int code, String message) {
        showCustomToast(message);
        if (isSuccess) next2();
    }

    @Override
    public void validatepostGroupFail(String msg) {
        showCustomToast(msg);
    }
}