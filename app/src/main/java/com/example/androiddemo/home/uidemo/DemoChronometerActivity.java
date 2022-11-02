package com.example.androiddemo.home.uidemo;

import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoChronometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_chronometer);
        getSupportActionBar().setTitle("Chronometer 示例");

        Button button = findViewById(R.id.btn_start_chronometer);
        Button btnCountDown = findViewById(R.id.btn_countdown);
        Chronometer chronometer = findViewById(R.id.chronometer);
        Chronometer countdown = findViewById(R.id.countdown);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.setFormat("%S");
                chronometer.start();
            }
        });

        btnCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    countdown.setCountDown(true);
                    countdown.setBase(SystemClock.elapsedRealtime());
                    countdown.start();
                }

            }
        });
    }
}