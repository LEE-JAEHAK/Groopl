package com.makeus.groopl.Splash;

import android.content.Intent;
import android.os.Bundle;

import com.makeus.groopl.R;
import com.makeus.groopl.src.BaseActivity;

public class Splash1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash1);

        Thread splash = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                    next();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splash.start();
    }

    void next() {
        Intent intent = new Intent(this, Splash2.class);
        startActivity(intent);
        finish();
    }
}