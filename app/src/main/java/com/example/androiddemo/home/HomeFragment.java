package com.example.androiddemo.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androiddemo.R;
import com.example.androiddemo.home.activitydemo.DemoActionBarActivity;
import com.example.androiddemo.home.activitydemo.DemoActionBarTabActivity;
import com.example.androiddemo.home.activitydemo.DemoActionViewActivity;
import com.example.androiddemo.home.activitydemo.DemoActivityActivity;
import com.example.androiddemo.home.activitydemo.DemoIntentActivity;
import com.example.androiddemo.home.activitydemo.DemoMenuActivity;
import com.example.androiddemo.home.activitydemo.DemoTipActivity;
import com.example.androiddemo.home.broadcastdemo.DemoBroadcastActivity;
import com.example.androiddemo.home.uidemo.DemoChronometerActivity;
import com.example.androiddemo.home.uidemo.DemoImageSwitcherActivity;
import com.example.androiddemo.home.uidemo.DemoListViewActivity;
import com.example.androiddemo.home.uidemo.DemoProgressBarActivity;
import com.example.androiddemo.home.uidemo.DemoSpinnerActivity;
import com.example.androiddemo.home.uidemo.DemoTabsActivity;
import com.example.androiddemo.home.uidemo.DemoTextViewActivity;
import com.example.androiddemo.home.webviewdemo.DemoWebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private List<GridChannel> lists = new ArrayList<>();

    public HomeFragment() {
        initListData();
    }

    private void initListData() {

        lists.add(new GridChannel(99, R.drawable.app, "API", DemoAPIActivity.class));
        lists.add(new GridChannel(1, R.drawable.app, "TextView", DemoTextViewActivity.class));
        lists.add(new GridChannel(2, R.drawable.app, "ListView", DemoListViewActivity.class));
        lists.add(new GridChannel(3, R.drawable.app, "ImageSwitcher", DemoImageSwitcherActivity.class));
        lists.add(new GridChannel(4, R.drawable.app, "ProgressBar", DemoProgressBarActivity.class));
        lists.add(new GridChannel(5, R.drawable.app, "Spinner", DemoSpinnerActivity.class));
        lists.add(new GridChannel(6, R.drawable.app, "Chronometer", DemoChronometerActivity.class));
        lists.add(new GridChannel(7, R.drawable.app, "Tabs[D]", DemoTabsActivity.class));
        lists.add(new GridChannel(8, R.drawable.app, "Activity", DemoActivityActivity.class));
        lists.add(new GridChannel(9, R.drawable.app, "Intent", DemoIntentActivity.class));
        lists.add(new GridChannel(10, R.drawable.app, "Broadcast", DemoBroadcastActivity.class));
        lists.add(new GridChannel(11, R.drawable.app, "Webview", DemoWebViewActivity.class));
        lists.add(new GridChannel(12, R.drawable.app, "Menu", DemoMenuActivity.class));
        lists.add(new GridChannel(13, R.drawable.app, "ActionBar", DemoActionBarActivity.class));
        lists.add(new GridChannel(14, R.drawable.app, "ActionView", DemoActionViewActivity.class));
        lists.add(new GridChannel(15, R.drawable.app, "ActionBarTab[D]", DemoActionBarTabActivity.class));
        lists.add(new GridChannel(16, R.drawable.app, "Tip", DemoTipActivity.class));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Log.i(TAG, "onViewCreated: ");
        GridView gridView = root.findViewById(R.id.gridView);
        // 设置网格视图适配器
        gridView.setAdapter(new GridAdapter(lists, getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " + position + ";" + id);
                GridChannel gridChannel = lists.get(position);
                Intent intent = new Intent(view.getContext(), gridChannel.getCls());
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
