package com.example.androiddemo.home.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoBroadcastSystemActivity extends AppCompatActivity {

    private static final String TAG = "DemoBroadcastSystem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_broadcast_system);

        registerNetWorkReceiver();

    }

    private void registerNetWorkReceiver(){
        // 查询默认
        connectedAndType();

        // 监听系统得广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        DemoBroadcastSystemActivity.NetworkReceiver networkReceiver = new DemoBroadcastSystemActivity.NetworkReceiver();
        // 动态注册
        registerReceiver(networkReceiver, intentFilter);
    }

    private void connectedAndType(){
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if(nInfo != null){
            boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            boolean isWiFi = nInfo.getType() == ConnectivityManager.TYPE_WIFI;
            Log.i(TAG, "connectedAndType: "+connected+",isWiFi:"+isWiFi);
        }
    }


    /**
     * 网络状态
     */
    class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive1: "+intent.getAction());
            connectedAndType();
            // 区分系统消息类型
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                //获取当前网络状态
                int networkType = intent.getIntExtra("networkType", -1);
                switch (networkType) {
                    case -1:
                        Toast.makeText(DemoBroadcastSystemActivity.this, "没有联网", Toast.LENGTH_SHORT).show();
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        Toast.makeText(DemoBroadcastSystemActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                        break;
                    case ConnectivityManager.TYPE_WIFI:
                        Toast.makeText(DemoBroadcastSystemActivity.this, "WIIF", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(DemoBroadcastSystemActivity.this, "其他网络", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }
}