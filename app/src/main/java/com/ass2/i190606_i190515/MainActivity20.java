package com.ass2.i190606_i190515;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity20 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity21.class));
            finish();
        }, 2000);
    }
}