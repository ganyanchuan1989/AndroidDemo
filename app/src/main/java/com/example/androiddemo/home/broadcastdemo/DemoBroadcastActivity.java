package com.example.androiddemo.home.broadcastdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.androiddemo.R;

public class DemoBroadcastActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "DemoBroadcastActivity";

    private MyDynamicRegisterReceiver myDynamicRegisterReceiver;
    private MyDynamicRegisterReceiver localReceiver;

    private LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_broadcast);

        Button btn_broadcast_net = findViewById(R.id.btn_broadcast_net);
        Button btn_broadcast_custom = findViewById(R.id.btn_broadcast_custom);
        Button btn_broadcast_custom2 = findViewById(R.id.btn_broadcast_custom2);
        Button btn_broadcast_custom_dynamic = findViewById(R.id.btn_broadcast_custom_dynamic);
        Button btn_broadcast_custom_dynamic2 = findViewById(R.id.btn_broadcast_custom_dynamic2);
        Button btn_broadcast_local = findViewById(R.id.btn_broadcast_local);
        Button btn_broadcast_order = findViewById(R.id.btn_broadcast_order);

        btn_broadcast_net.setOnClickListener(this);
        btn_broadcast_custom.setOnClickListener(this);
        btn_broadcast_custom2.setOnClickListener(this);
        btn_broadcast_custom_dynamic.setOnClickListener(this);
        btn_broadcast_custom_dynamic2.setOnClickListener(this);
        btn_broadcast_local.setOnClickListener(this);
        btn_broadcast_order.setOnClickListener(this);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        // 注册全局动态广播接收器
        dynamicRegisterReceiver();
        // 注册本地动态广播接收器
        dynamicRegisterLocalReceiver();


    }

    private void dynamicRegisterReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyDynamicRegisterReceiver.ACTION_D1);
        intentFilter.addAction(MyDynamicRegisterReceiver.ACTION_D2);
        myDynamicRegisterReceiver = new MyDynamicRegisterReceiver();
        registerReceiver(myDynamicRegisterReceiver, intentFilter);
    }

    private void dynamicRegisterLocalReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyDynamicRegisterReceiver.ACTION_D1);
        localReceiver = new MyDynamicRegisterReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_broadcast_net:
                Intent intent = new Intent(this, DemoBroadcastSystemActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_broadcast_custom:
                Log.i(TAG, "onClick: btn_broadcast_custom");
                Intent intent1 = new Intent();
                intent1.setAction(MyStaticRegisterReceiver.ACTION_1);
                // Android8(API26)开始，静态注册广播接收器，隐式方式发送不再接收。需要显示指定接收器。
                intent1.setComponent(new ComponentName(DemoBroadcastActivity.this,MyStaticRegisterReceiver.class));
                sendBroadcast(intent1);
                break;
            case R.id.btn_broadcast_custom2:
                Intent intent2 = new Intent();
                intent2.setAction(MyStaticRegisterReceiver.ACTION_2);
                intent2.setComponent(new ComponentName(DemoBroadcastActivity.this,MyStaticRegisterReceiver.class));
                sendBroadcast(intent2);
                break;
            case R.id.btn_broadcast_custom_dynamic:
                Intent intent3 = new Intent();
                intent3.setAction(MyDynamicRegisterReceiver.ACTION_D1);
                sendBroadcast(intent3);
                break;
            case R.id.btn_broadcast_custom_dynamic2:
                Intent intent4 = new Intent();
                intent4.setAction(MyDynamicRegisterReceiver.ACTION_D2);
                sendBroadcast(intent4);
                break;
            case R.id.btn_broadcast_local:
                Intent intent5 = new Intent();
                intent5.setAction(MyDynamicRegisterReceiver.ACTION_D1);
                // 发送本地消息
                localBroadcastManager.sendBroadcast(intent5);
                break;
            case R.id.btn_broadcast_order:
                // 发送有序广播接口，可以在OnReceiver中调用abortBroadcast中止广播继续发送。
//                sendOrderedBroadcast();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myDynamicRegisterReceiver != null){
            unregisterReceiver(myDynamicRegisterReceiver);
        }
        if(localReceiver != null){
            localBroadcastManager.unregisterReceiver(localReceiver);
        }
    }
}