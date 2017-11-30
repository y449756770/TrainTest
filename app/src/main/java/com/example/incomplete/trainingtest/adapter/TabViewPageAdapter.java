package com.example.incomplete.trainingtest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by incomplete on 17/8/8.
 */

public class TabViewPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public TabViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabViewPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
}
