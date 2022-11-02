package com.example.androiddemo.home.uidemo;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoImageSwitcherActivity extends AppCompatActivity {

    // 图标资源
    private int[] pics = new int[]{
            R.drawable.app,
            R.drawable.avatar,
            R.drawable.home_d,
            R.drawable.home_s,
            R.drawable.my_d,
            R.drawable.my_s,
    };

    private int index = 0;
    private float upX = 0;
    private float downX = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_image_switcher);
        getSupportActionBar().setTitle("ImageSwitcher 示例");

        ImageSwitcher is = findViewById(R.id.image_switcher);
        is.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        is.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(DemoImageSwitcherActivity.this);
                imageView.setImageResource(pics[index]);
                return imageView;
            }
        });
        is.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 鼠标按下
                    downX = event.getX();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // 鼠标抬起
                    upX = event.getX();
                    if (upX - downX > 100) {
                        // 从左向右
                        index = index == 0 ? pics.length - 1 : index - 1;
                        is.setImageResource(pics[index]);
                    } else if (upX - downX < 100) {
                        // 从右向左
                        index = index == pics.length - 1 ? 0 : index + 1;
                        is.setImageResource(pics[index]);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}