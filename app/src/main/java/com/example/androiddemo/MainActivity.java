package com.example.androiddemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androiddemo.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private ImageView btnHome, btnMy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(new HomeFragment());

        btnHome = findViewById(R.id.btn_home);
        btnMy = findViewById(R.id.btn_my);
        btnHome.setOnClickListener(this);
        btnMy.setOnClickListener(this);
    }

    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        btnHome.setImageResource(R.drawable.home_d);
        btnMy.setImageResource(R.drawable.my_d);

        Fragment fragment = null;
        switch (v.getId()){
            case R.id.btn_home:
                btnHome.setImageResource(R.drawable.home_s);
                fragment = new HomeFragment();
                break;
            case R.id.btn_my:
                btnMy.setImageResource(R.drawable.my_s);
                fragment = new MyFragment();
                break;
            default:
                break;
        }

        changeFragment(fragment);
    }
}