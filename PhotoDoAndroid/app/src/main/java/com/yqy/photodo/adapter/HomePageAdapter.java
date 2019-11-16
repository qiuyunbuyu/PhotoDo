package com.yqy.photodo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yqy on 2019/11/2.
 */

public class HomePageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    public HomePageAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.mFragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
