package com.example.androiddemo.home.activitydemo.tabs;

import android.content.Context;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MyTabListener implements ActionBar.TabListener {

    private Context context;
    private Class mClass;
    private Fragment fragment;

    public MyTabListener(Context context, Class mClass) {
        this.context = context;
        this.mClass = mClass;
    }

    /**
     * 选中后附加fragment
     * @param tab
     * @param ft
     */
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(fragment == null) {
            fragment = Fragment.instantiate(context, mClass.getName());
            ft.add(android.R.id.content, fragment, null);
        }
        ft.attach(fragment);

    }

    /**
     * 未选中情况下移除fragment
     * @param tab
     * @param ft
     */
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(fragment!= null) {
            ft.detach(fragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
