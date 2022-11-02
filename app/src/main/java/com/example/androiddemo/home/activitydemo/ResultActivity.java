package com.example.androiddemo.home.activitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class ResultActivity extends AppCompatActivity {

    public static int RET_CODE = 0x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // old get param data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        int age = bundle.getInt("age");

        TextView txt_pass_param = findViewById(R.id.txt_pass_param);
        txt_pass_param.setText("param: name:" + name + ",age" + age);


        Button btn_ret_value = findViewById(R.id.btn_ret_value);
        btn_ret_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                Bundle bundle1 = new Bundle();
                bundle1.putString("ret", "你是个好人");
                intent1.putExtras(bundle1); // 设置返回值
                setResult(RET_CODE, intent1); // 设置返回代码
                finish();
            }
        });

    }
}