package com.example.androiddemo.media;

import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

import java.io.File;

public class DemoVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_video);

        VideoView videoView = findViewById(R.id.video_view);

        File file = new File(Environment.getExternalStorageDirectory()+"/Movies/jiaojiao.mp4");
        if(file.exists()){
            videoView.setVideoPath(file.getAbsolutePath());
        } else {
            Toast.makeText(this, file.getAbsolutePath()+"文件不存在", Toast.LENGTH_SHORT).show();
        }

        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        // 设置布局
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        mc.setLayoutParams(lp);
        ((ViewGroup) mc.getParent()).removeView(mc);
        ((FrameLayout) findViewById(R.id.videoViewWrapper)).addView(mc);

        videoView.requestFocus(); // 设置获取焦点

    }
}