package com.example.androiddemo.home.uidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoListViewActivity extends AppCompatActivity {

    private static final String TAG = "DemoListViewActivity";
    private final List<Map<String, Object>> lists = new ArrayList<>();

    public DemoListViewActivity() {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", R.drawable.avatar);
            map.put("desc", "我是一段文本" + i);
            lists.add(map);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("ListView 示例");
        setContentView(R.layout.activity_demo_list_view);

        ListView listView = findViewById(R.id.list_view);
        // 采用系统提供的的适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, lists,
                R.layout.demo_list_view_item,
                new String[]{"image", "desc"},
                new int[]{R.id.list_item_img, R.id.list_item_txt});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map map = lists.get(position);
                Toast.makeText(DemoListViewActivity.this, map.get("desc").toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}