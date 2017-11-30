package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;

import com.example.incomplete.trainingtest.swipeback.SwipeBackActivity;

public class BaseActivity extends SwipeBackActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        super.setSwipeBackEnable(enable);
    }

}
