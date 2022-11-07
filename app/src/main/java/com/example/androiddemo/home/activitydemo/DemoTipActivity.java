package com.example.androiddemo.home.activitydemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.androiddemo.MainActivity;
import com.example.androiddemo.R;

public class DemoTipActivity extends AppCompatActivity implements View.OnClickListener{

    final int NOTIFYID = 0x111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_tip);

        Button btn_toast = findViewById(R.id.btn_toast);
        Button btn_alert = findViewById(R.id.btn_alert);
        Button btn_alert_list = findViewById(R.id.btn_alert_list);
        Button btn_alert_radio = findViewById(R.id.btn_alert_radio);
        Button btn_alert_check = findViewById(R.id.btn_alert_check);
        Button btn_notification = findViewById(R.id.btn_notification);

        btn_toast.setOnClickListener(this);
        btn_alert.setOnClickListener(this);
        btn_alert_list.setOnClickListener(this);
        btn_alert_radio.setOnClickListener(this);
        btn_alert_check.setOnClickListener(this);
        btn_notification.setOnClickListener(this);

    }

    private void handleBtnALert(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setIcon(R.drawable.messages);
        alertDialog.setTitle("提示：");
        alertDialog.setMessage("劝君更尽一杯酒，西出阳关无故人");
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "不喝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DemoTipActivity.this, "赶路不喝", Toast.LENGTH_SHORT).show();;
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "喝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DemoTipActivity.this, "干了", Toast.LENGTH_SHORT).show();;
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "让我想想", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DemoTipActivity.this, "在想想", Toast.LENGTH_SHORT).show();;
            }
        });
        alertDialog.show();
    }

    private void handleBtnAlertList(){

        String[] items = new String[]{"item1","item2","item3","item4"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.setting);
        builder.setTitle("请选择设置");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DemoTipActivity.this, "你选择了"+ items[which], Toast.LENGTH_SHORT).show();;
            }
        });
        builder.create().show();
    }

    private void handleBtnAlertRadio(){
        String[] items1 = new String[]{"item1","item2","item3","item4"};
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setIcon(R.drawable.setting);
        builder1.setTitle("请选择设置");
        builder1.setSingleChoiceItems(items1,0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DemoTipActivity.this, "你选择了"+ items1[which], Toast.LENGTH_SHORT).show();;
            }
        });
        builder1.setPositiveButton("确定", null);
        builder1.create().show();
    }

    private void handleBtnAlertCheck(){
        String[] items = new String[]{"item1","item2","item3","item4"};
        boolean[] checkedItems = new boolean[]{true,false,false,false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.setting);
        builder.setTitle("请选择设置");
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(DemoTipActivity.this, "你选择了"+ items[which], Toast.LENGTH_SHORT).show();;
            }
        });
        builder.setPositiveButton("确定", null);
        builder.create().show();
    }

    private void handleBtnNotificationSdk26(){
        final NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);//通知栏管理器（得到系统服务）
        String id = "channel_1"; //自定义设置通道ID属性
        String description = "123";//自定义设置通道描述属性
        int importance = NotificationManager.IMPORTANCE_HIGH;//通知栏管理重要提示消息声音设定
        /**
         * Oreo不用Priority了，用importance
         * IMPORTANCE_NONE 关闭通知
         * IMPORTANCE_MIN 开启通知，不会弹出，但没有提示音，状态栏中无显示
         * IMPORTANCE_LOW 开启通知，不会弹出，不发出提示音，状态栏中显示
         * IMPORTANCE_DEFAULT 开启通知，不会弹出，发出提示音，状态栏中显示
         * IMPORTANCE_HIGH 开启通知，会弹出，发出提示音，状态栏中显示
         */
        NotificationChannel mChannel = null;//建立通知栏通道类（需要有ID，重要属性）
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(id, "123", importance);
            mChannel.setDescription(description); // 配置通知渠道的属性
            mChannel.enableLights(true);// 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.setLightColor(Color.RED);//设置闪灯颜色为红色
            mChannel.enableVibration(true);   // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            manager.createNotificationChannel(mChannel);//最后在notificationmanager中创建该通知渠道
        }

        // 消息打开的Activity
        Intent intent = new Intent(DemoTipActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(DemoTipActivity.this,0,intent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = null;//运行
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //创建Notification对象。
            notification = new Notification.Builder(this, id)
                    .setContentTitle("付款通知")  //设置通知标题
                    .setSmallIcon(R.drawable.messages) //设置通知小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.messages))//设置通知大图标
                    .setContentText("您已付款1111元")//设置通知内容
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)//设置自动删除通知
                    .build();
        }


        manager.notify((int) System.currentTimeMillis(),notification); //通知栏保留多条通知
    }

    private void handleBtnNotification(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            handleBtnNotificationSdk26();
        } else {
            //第一步：创建通知构造器NotificationCompat.Builder对象。
            NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),"default");
            //第二步：调用NotificationCompat.Builder对象的方法设置通知相关内容。
            builder.setSmallIcon(R.drawable.messages);//设置通知小图标
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.messages));//设置通知大图标
            builder.setContentTitle("付款通知");//设置通知标题
            builder.setContentText("您已付款111元");//设置通知内容
            builder.setAutoCancel(true);//设置自动删除通知
            Notification notification=builder.build();//：创建Notification对象。
            NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);//通知栏管理器（得到系统服务）
            //        manager.notify(1,notification); //通知栏保留单条通知
            manager.notify((int) System.currentTimeMillis(),notification); //通知栏保留多条通知

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_toast:
                Toast.makeText(this, "这是一个Toast", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_alert:
                handleBtnALert();
                break;
            case R.id.btn_alert_list:
                handleBtnAlertList();
                break;
            case R.id.btn_alert_radio:
                handleBtnAlertRadio();
                break;
            case R.id.btn_alert_check:
                handleBtnAlertCheck();
                break;
            case R.id.btn_notification:
                handleBtnNotification();
                break;
        }
    }
}