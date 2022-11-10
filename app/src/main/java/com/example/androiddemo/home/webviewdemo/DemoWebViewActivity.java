package com.example.androiddemo.home.webviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoWebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DemoWebViewActivity";
    private static Context context;

    private WebView webView;
    private EditText editText;


    public static Context getContext(){
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_web_view);
        context = getApplicationContext();

        Button btn_java_call_js = findViewById(R.id.btn_java_call_js);
        Button btn_load_url = findViewById(R.id.btn_load_url);

        btn_java_call_js.setOnClickListener(this);
        btn_load_url.setOnClickListener(this);

        editText = findViewById(R.id.txt_url);
        webView = findViewById(R.id.webview);

        // 启用js脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // 此參數值設定為true是為了當內容大於viewport時，系統將會自動縮小內容以適屏幕寬度。
        webView.getSettings().setLoadWithOverviewMode(true);
        // 設定true時會將viewport的meta tag啟動。
        webView.getSettings().setUseWideViewPort(true);

        webView.addJavascriptInterface(new MyJSApi(), "myJsApi");
        webView.loadUrl("file:///android_asset/test.html");
        editText.setText("file:///android_asset/test.html");

//      采用服务器加载，如果是http，需要设置
//      android:usesCleartextTraffic="true"
//        webView.loadUrl("http://192.168.137.1:5500/test.html");
//        editText.setText("http://192.168.137.1:5500/test.html");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i(TAG, "onPageStarted: ");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "onPageFinished: ");
                super.onPageFinished(view, url);
                callJs();
            }
        });

        // 监听控制台错误消息
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.i(TAG, "onConsoleMessage: "+consoleMessage);
                return super.onConsoleMessage(consoleMessage);
            }
        });
    }

    private void callJs(){
        String params = "\"gxz\"";
        webView.evaluateJavascript("javascript:javaCallMethod(" + params + ")", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Toast.makeText(DemoWebViewActivity.this, "返回值:"+ value, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_load_url:
                webView.loadUrl(editText.getText().toString());
                break;
            case R.id.btn_java_call_js:
                callJs();
                break;

        }
    }
}