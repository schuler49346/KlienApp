package com.example.klienapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button mRegBtn;
    private Button mgologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mgologin=(Button) findViewById(R.id.login_btn);
        mgologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_login = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(go_login);
            }
        });

        mRegBtn=(Button) findViewById(R.id.reg_button);
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(reg_intent);
            }
        });
    }
}