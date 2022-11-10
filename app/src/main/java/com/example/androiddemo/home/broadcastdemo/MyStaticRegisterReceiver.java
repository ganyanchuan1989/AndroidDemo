package com.example.androiddemo.home.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 采用静态注册的Receiver
 */
public class MyStaticRegisterReceiver extends BroadcastReceiver {
    public static final String ACTION_1 = "action1";
    public static final String ACTION_2 = "action2";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION_1)){
            Toast.makeText(context, "接收到Action1", Toast.LENGTH_SHORT).show();
        } else if(intent.getAction().equals(ACTION_2)) {
            Toast.makeText(context, "接收到Action2", Toast.LENGTH_SHORT).show();
        }
    }
}
