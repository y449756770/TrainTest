package com.example.incomplete.trainingtest.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.incomplete.trainingtest.R;

/**
 * 模拟内存泄漏的的情况
 */

public class LeakActivity extends AppCompatActivity {
    static Leak mLeak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        mLeak = new Leak();
    }

    class Leak {
        Bitmap mBitmap;
        Bitmap mIcon;
        String str;

    }
}
