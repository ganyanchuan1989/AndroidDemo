package com.example.androiddemo.media;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

public class MediaFragment extends Fragment {


    final List<MediaGridItem> lists = new ArrayList<>();

    public MediaFragment() {
        lists.add(new MediaGridItem(1,R.drawable.app,"MediaPlayer", DemoMediaPlayActivity.class));
        lists.add(new MediaGridItem(2,R.drawable.app,"Video", DemoVideoActivity.class));
        lists.add(new MediaGridItem(3,R.drawable.app,"Camera", DemoCameraActivity.class));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);

        GridView gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(new MediaGridAdapter(lists, getActivity()));
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            MediaGridItem item = lists.get(position);
            Intent intent = new Intent(getActivity(), item.getCls());
            startActivity(intent);
        });
        return view;
    }
}
