package com.example.androiddemo.home.uidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_progress_bar);
        getSupportActionBar().setTitle("ProgressBar 示例");

        Button btnSetProgress = findViewById(R.id.btn_set_progress);
        ProgressBar progressBar = findViewById(R.id.progress_bar);

        btnSetProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(progressBar.getProgress() + 10);
            }
        });

    }
}