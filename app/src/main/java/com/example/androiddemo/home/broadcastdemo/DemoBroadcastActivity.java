package com.example.androiddemo.home.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoBroadcastActivity extends AppCompatActivity {

    private static final String TAG = "DemoBroadcastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_broadcast);

        // 监听系统得广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        NetworkReceiver networkReceiver = new NetworkReceiver();
        registerReceiver(networkReceiver, intentFilter);

    }

    class NetworkReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();

            Log.i(TAG, "onReceive: ");
        }
    }
}