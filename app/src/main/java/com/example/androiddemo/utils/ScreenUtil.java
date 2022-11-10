package com.example.androiddemo.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenUtil {

    public static DisplayMetrics getScreenDisplayMetrics(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static int getWindowWidth(Activity activity){
        DisplayMetrics dm = getScreenDisplayMetrics(activity);
        return dm.widthPixels;
    }

    public static int getWindowHeigh(Activity activity){
        DisplayMetrics dm = getScreenDisplayMetrics(activity);
        return dm.heightPixels;
    }
}
