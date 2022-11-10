package com.example.androiddemo.utils;

import android.content.ContentResolver;
import android.net.Uri;

import com.example.androiddemo.MyApplication;

import java.io.File;

public class MyUtils {
    public static Uri getRawUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + MyApplication.getAppPackageName() + "/raw/" + filename);
    }
    public static Uri getDrawableUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + MyApplication.getAppPackageName() + "/drawable/" + filename);
    }
    public static Uri getMipmapUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + MyApplication.getAppPackageName() + "/mipmap/" + filename);
    }
}
