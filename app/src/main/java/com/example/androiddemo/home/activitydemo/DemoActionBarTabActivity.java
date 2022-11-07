package com.example.androiddemo.home.activitydemo;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.home.activitydemo.tabs.Fragment1;
import com.example.androiddemo.home.activitydemo.tabs.Fragment2;
import com.example.androiddemo.home.activitydemo.tabs.MyTabListener;

public class DemoActionBarTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_action_bar_tab);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE); // 隐藏标题栏
        actionBar.addTab(actionBar.newTab().setText("tab1").setTabListener(new MyTabListener(this, Fragment1.class)));
        actionBar.addTab(actionBar.newTab().setText("tab2").setTabListener(new MyTabListener(this, Fragment2.class)));
        actionBar.addTab(actionBar.newTab().setText("tab3").setTabListener(new MyTabListener(this, Fragment1.class)));
        actionBar.addTab(actionBar.newTab().setText("tab4").setTabListener(new MyTabListener(this, Fragment2.class)));

    }
}