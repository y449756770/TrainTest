package com.example.incomplete.trainingtest.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.example.incomplete.trainingtest.R;

public class WindowActivity extends AppCompatActivity {
    static WindowManager mWindowManager;
    WindowManager.LayoutParams mLayoutParams;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        mContext = getApplicationContext();
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//
        Button mBtn = new Button(this);
        mBtn.setText("TestTest");
        mBtn.setTextSize(30);

//        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, 0, 0, PixelFormat.TRANSPARENT);
//        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        mLayoutParams.gravity = Gravity.TOP;
//        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
//        mLayoutParams.x = 100;
//        mLayoutParams.y = 300;
//        mWindowManager.addView(mBtn, mLayoutParams);


        ConstraintLayout.LayoutParams mLayoutParam =new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        getWindow().addContentView(mBtn,mLayoutParam);


    }
}
