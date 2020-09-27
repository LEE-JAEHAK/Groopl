package com.makeus.groopl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.makeus.groopl.Main.FinishedFragment;
import com.makeus.groopl.Main.OngoingFragment;
import com.makeus.groopl.Main.SuggestFragment;
import com.makeus.groopl.src.BaseActivity;

public class HomeActivity extends BaseActivity {
    SuggestFragment suggestFragment;
    OngoingFragment ongoingFragment;
    FinishedFragment finishedFragment;
    Button mBtnSuggest, mBtnOngoing, mBtnFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouphome);

        suggestFragment = new SuggestFragment();
        ongoingFragment = new OngoingFragment();
        finishedFragment = new FinishedFragment();
        mBtnSuggest = findViewById(R.id.btn_grouphome_suggest);
        mBtnOngoing = findViewById(R.id.btn_grouphome_ongoing);
        mBtnFinished = findViewById(R.id.btn_grouphome_finished);
        mBtnSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag1();
            }
        });
        mBtnOngoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag2();
            }
        });
        mBtnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag3();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_grouphome, suggestFragment).commitAllowingStateLoss();
    }

    void frag1() {
        mBtnSuggest.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        mBtnOngoing.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        mBtnFinished.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_grouphome, suggestFragment).commitAllowingStateLoss();
    }

    void frag2() {
        mBtnSuggest.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        mBtnOngoing.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        mBtnFinished.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_grouphome, ongoingFragment).commitAllowingStateLoss();
    }

    void frag3() {
        mBtnSuggest.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        mBtnOngoing.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        mBtnFinished.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_grouphome, finishedFragment).commitAllowingStateLoss();
    }
}