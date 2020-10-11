package com.makeus.groopl.Splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.makeus.groopl.R;
import com.makeus.groopl.group.Group;

public class Splash3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash3);

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
        Intent intent = new Intent(this, Group.class);
        startActivity(intent);
        finish();
    }
}