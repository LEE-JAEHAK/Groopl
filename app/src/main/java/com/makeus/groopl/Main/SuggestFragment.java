package com.makeus.groopl.Main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeus.groopl.HomeActivity;
import com.makeus.groopl.R;

public class SuggestFragment extends Fragment {

    HomeActivity activity;
    View view, sheetView;
    Button mBtnSuggest, mBtnSubmit;
    BottomSheetDialog bottomSheetDialog;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        view = inflater.inflate(R.layout.fragment_suggest, container, false);
        mBtnSuggest = view.findViewById(R.id.btn_suggest);
        mBtnSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        return view;
    }
    void next(){
        bottomSheetDialog = new BottomSheetDialog(activity);
        sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet2, null);
        mBtnSubmit = sheetView.findViewById(R.id.btn_suggest_submit);
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next2();
            }
        });
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();
    }
    void next2(){
        ((HomeActivity)HomeActivity.context).frag2();
        bottomSheetDialog.cancel();
    }
}