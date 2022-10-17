package com.ass2.i190606_i190515;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
//        new Handler().postDelayed(() -> {
//            startActivity(new Intent(this, MainActivity11.class));
//            finish();
//        }, 2000);
    }
}