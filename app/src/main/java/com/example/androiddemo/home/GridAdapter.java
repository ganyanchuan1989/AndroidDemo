package com.example.androiddemo.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddemo.R;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private static final String TAG = "GridAdapter";
    private List<GridChannel> lists;
    private LayoutInflater layoutInflater;

    public GridAdapter(List<GridChannel> lists, Context mContext) {
        this.lists = lists;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView: 1");
        ViewHolder viewHolder = null;
        if(convertView == null){
            Log.i(TAG, "getView: 2");
            convertView = layoutInflater.inflate(R.layout.home_grid_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.grid_item_img);
            viewHolder.textView = convertView.findViewById(R.id.grid_item_txt);
            convertView.setTag(viewHolder);

        } else {
            Log.i(TAG, "getView: 3");
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GridChannel gridChannel = lists.get(position);

        viewHolder.imageView.setImageResource(gridChannel.getImgId());
        viewHolder.textView.setText(gridChannel.getDesc());
        Log.i(TAG, "getView: 4");

        return convertView;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
