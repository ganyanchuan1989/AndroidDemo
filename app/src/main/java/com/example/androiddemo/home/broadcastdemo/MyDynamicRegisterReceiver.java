package com.example.androiddemo.home.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyDynamicRegisterReceiver extends BroadcastReceiver {

    public static final String ACTION_D1 ="action_d1";
    public static final String ACTION_D2 ="action_d2";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION_D1)){
            Toast.makeText(context, "动态注册ActionD1", Toast.LENGTH_SHORT).show();
        } else if(intent.getAction().equals(ACTION_D2)){
            Toast.makeText(context, "动态注册ActionD2", Toast.LENGTH_SHORT).show();
        }
    }
}
