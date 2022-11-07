package com.example.androiddemo.home.activitydemo;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_menu);

        TextView txt_context_menu = findViewById(R.id.txt_context_menu);
        // 注册上下文菜单
        registerForContextMenu(txt_context_menu);

    }

    /**
     * 上下文菜单
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this,"我被选中了:"+ item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onContextItemSelected(item);
    }

    /**
     * 选项菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        Toast.makeText(this, "选中了:"+title, Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}