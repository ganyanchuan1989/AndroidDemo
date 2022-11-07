package com.example.androiddemo.home.activitydemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.androiddemo.R;

public class DemoActionBarActivity extends AppCompatActivity implements View.OnClickListener{

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_action_bar);

        actionBar = getSupportActionBar();
        actionBar.setTitle("ActionBar Demo");

        // 设置返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        Button btn_action_bar_show = findViewById(R.id.btn_action_bar_show);
        Button btn_action_bar_hide = findViewById(R.id.btn_action_bar_hide);
        Button btn_action_bar_back = findViewById(R.id.btn_action_bar_back);

        btn_action_bar_show.setOnClickListener(this);
        btn_action_bar_hide.setOnClickListener(this);
        btn_action_bar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_action_bar_hide:
                actionBar.hide();
                break;
            case R.id.btn_action_bar_show:
                actionBar.show();
                break;
            case R.id.btn_action_bar_back:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 注意这里不能用 new MenuInflater,要采用ActionBar提供的MenuInflater实例。
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }
}