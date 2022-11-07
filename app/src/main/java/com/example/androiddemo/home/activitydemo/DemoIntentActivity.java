package com.example.androiddemo.home.activitydemo;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoIntentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DemoIntentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_intent);

        Button btn_intent_cn = findViewById(R.id.btn_intent_cn);
        Button btn_call_phone = findViewById(R.id.btn_call_phone);
        Button btn_send_message = findViewById(R.id.btn_send_message);
        Button btn_back_home = findViewById(R.id.btn_back_home);
        Button btn_intent_filer = findViewById(R.id.btn_intent_filer);


        btn_intent_cn.setOnClickListener(this);
        btn_call_phone.setOnClickListener(this);
        btn_send_message.setOnClickListener(this);
        btn_back_home.setOnClickListener(this);
        btn_intent_filer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_intent_cn:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.androiddemo", "com.example.androiddemo.home.activitydemo.DemoActivityActivity"));
                startActivity(intent);
                break;
            case R.id.btn_call_phone:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_CALL);
                intent1.setData(Uri.parse("tel:10086"));
                startActivity(intent1);
                break;
            case R.id.btn_send_message:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_SENDTO);
                intent2.setData(Uri.parse("smsto:10086"));
                intent2.putExtra("sms_body", "Hello");
                startActivity(intent2);
                break;
            case R.id.btn_back_home:
                Intent intent3 = new Intent();
                intent3.setAction(Intent.ACTION_MAIN);
                intent3.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent3);
                break;
            case R.id.btn_intent_filer:
                Intent intent4 = new Intent();
                intent4.setAction(Intent.ACTION_VIEW);
                startActivity(intent4);
                break;
        }
    }
}