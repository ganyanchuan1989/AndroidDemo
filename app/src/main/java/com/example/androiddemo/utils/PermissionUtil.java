package com.example.androiddemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

    // Android6.0 版本之后才需要动态申请权限
    public static boolean isHigherM = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;

    /**
     * 判断是否有权限
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasPermission(Context context, String permission){
        if(isHigherM){
            if(ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    /**
     * 注册权限
     * @param activity
     * @param requestCode
     */
    public static void requestPermission(Activity activity, String permission, int requestCode){
        ActivityCompat.requestPermissions(activity, new String[]{permission},requestCode);
    }

    /**
     * 获取未授权的所有权限
     * @param context
     * @param permission
     * @return
     */
    public static String[] getNoGrantPermission(Context context, String[] permission){
        List<String> noGrantPermission = new ArrayList<>();
        for (String s : permission) {
            if(!hasPermission(context, s)){
                noGrantPermission.add(s);
            }
        }
        return (String[]) noGrantPermission.toArray();
    }
}
