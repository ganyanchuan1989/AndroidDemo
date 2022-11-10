package com.example.androiddemo;

import android.app.Application;
import android.content.Context;

/**
 * 自定义application，需要在AndroidManifest的application标签中赋值
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    /**
     * 获取全局Context
     * @return
     */
    public static Context getAppContext(){
        return context;
    }

    public static String getAppPackageName(){
        return context.getPackageName();
    }

}
