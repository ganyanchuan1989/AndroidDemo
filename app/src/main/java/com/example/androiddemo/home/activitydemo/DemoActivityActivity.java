package com.example.androiddemo.home.activitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoActivityActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DemoActivityActivity";
    public static int REQ_CODE = 0x1;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_activity);

        Button btnCallOther = findViewById(R.id.btn_call_other);
        Button btnCallRet = findViewById(R.id.btn_call_result);
        Button btnCallRetOld = findViewById(R.id.btn_call_result_old);

        btnCallOther.setOnClickListener(this);
        btnCallRet.setOnClickListener(this);
        btnCallRetOld.setOnClickListener(this);

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == ResultActivity.RET_CODE) {
                            Intent data = result.getData();
                            Bundle bundle = data.getExtras();
                            String ret = bundle.getString("ret");
                            Log.i(TAG, "onActivityResult: " + result);
                            Toast.makeText(DemoActivityActivity.this, "回调参数：" + ret, Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call_other:
                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_call_result:
                Intent intent2 = new Intent(this, ResultActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("name", "gxz1");
                bundle2.putInt("age", 20);
                intent2.putExtras(bundle2);
                someActivityResultLauncher.launch(intent2);
                break;
            case R.id.btn_call_result_old:
                Log.i(TAG, "onClick: ");
                Intent intent1 = new Intent(this, ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "gxz");
                bundle.putInt("age", 18);
                intent1.putExtras(bundle);
                startActivityForResult(intent1, REQ_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == ResultActivity.RET_CODE) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("ret");
            Log.i(TAG, "onActivityResult: " + result);
            Toast.makeText(this, "回调参数：" + result, Toast.LENGTH_LONG).show();
        }
    }
}