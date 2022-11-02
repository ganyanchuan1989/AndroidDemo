package com.example.androiddemo.home.uidemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoTabsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_tabs);
        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();// 初始化

        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.tab1, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab2, tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("Tab111").setIndicator("精选表情").setContent(R.id.tab_id1));
        tabHost.addTab(tabHost.newTabSpec("Tab222").setIndicator("XX表情").setContent(R.id.tab_id2));
    }
}