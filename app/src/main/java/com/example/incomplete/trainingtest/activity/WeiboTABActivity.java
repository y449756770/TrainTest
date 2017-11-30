package com.example.incomplete.trainingtest.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.incomplete.trainingtest.BlankOneFragment;
import com.example.incomplete.trainingtest.BlankTwoFragment;
import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.adapter.TabViewPageAdapter;
import com.example.incomplete.trainingtest.view.LikeWeiboHorizentalScrollView;

import java.util.ArrayList;
import java.util.List;

public class WeiboTABActivity extends AppCompatActivity {
    List<Fragment> mFragments = new ArrayList<>();
    String[] mtitles;

    LikeWeiboHorizentalScrollView mTab;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_tab);
        mTab = (LikeWeiboHorizentalScrollView) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mFragments.add(BlankOneFragment.newInstance());
        mFragments.add(BlankTwoFragment.newInstance());
        mFragments.add(BlankOneFragment.newInstance());
        mFragments.add(BlankTwoFragment.newInstance());
        mtitles = new String[]{"关注", "推荐", "视频", "直播"};
        TabViewPageAdapter mAdapter = new TabViewPageAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        mTab.init(mtitles, mViewPager);


    }
}
