package com.makeus.groopl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.makeus.groopl.Splash.Splash3;

public class LoginActivity extends AppCompatActivity {

    ImageView mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mBtnLogin = findViewById(R.id.login_btn);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

    }

    void next() {
        Intent intent = new Intent(this, Splash3.class);
        startActivity(intent);
        finish();
    }
}