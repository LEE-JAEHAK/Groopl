package com.makeus.groopl.Splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.makeus.groopl.R;
import com.makeus.groopl.login.LoginActivity;

public class Splash2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash2);

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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}