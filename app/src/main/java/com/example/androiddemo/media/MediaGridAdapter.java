package com.example.androiddemo.media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddemo.R;

import java.util.List;

public class MediaGridAdapter extends BaseAdapter {

    private List<MediaGridItem> list;
    private LayoutInflater layoutInflater;

    public MediaGridAdapter(List<MediaGridItem> list, Context context) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView == null){
            // 填充视图
            convertView = layoutInflater.inflate(R.layout.home_grid_item,null);
            viewHodler = new ViewHodler();
            viewHodler.imageView = convertView.findViewById(R.id.grid_item_img);
            viewHodler.textView = convertView.findViewById(R.id.grid_item_txt);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        MediaGridItem item = list.get(position);
        viewHodler.textView.setText(item.getTitle());
        viewHodler.imageView.setImageResource(item.getImgId());
        return convertView;
    }

    public class ViewHodler {
        ImageView imageView;
        TextView textView;
    }
}
