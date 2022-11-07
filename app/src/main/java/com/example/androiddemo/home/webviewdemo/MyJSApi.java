package com.example.androiddemo.home.webviewdemo;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MyJSApi {
    @JavascriptInterface
    public String sayHello(String name) {
        Toast.makeText(DemoWebViewActivity.getContext(), "被js调用,参数："+name, Toast.LENGTH_SHORT).show();
        return "javaOk";
    }
}
