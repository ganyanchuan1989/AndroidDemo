package com.example.androiddemo.home;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoAPIActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isFull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_apiactivity);
        getSupportActionBar().setTitle("系统API 示例");

        Button btnFullScreen = findViewById(R.id.btn_full_screen);
        btnFullScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_full_screen:
                handleBtnFullScreen(v);
        }
    }

    private void handleBtnFullScreen(View v) {
        Button button = (Button) v;
        if (!isFull) {
            isFull = true;
            button.setText("取消全屏");
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN; // 设置全屏
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            isFull = false;
            button.setText("设置全屏");
            WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN); // 去除全屏
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}