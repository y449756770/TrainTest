package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.view.ScrollTextView;

/**
 * 这个类用于实现各种滑动效果  共有三种滑动效果
 * <p>
 * 第一种是通过scrollTo和scrollBy实现
 * 第二种是通过动画实现
 * 第三种是通过改变View的LayoutParams实现
 *
 * @auther liuyong
 * @date 17/7/31
 */

public class ScrollActivity extends AppCompatActivity {

    ScrollTextView mScrollTv;
    Button mBeginScrollBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        mScrollTv = (ScrollTextView) findViewById(R.id.tv_scrollTo);
        mBeginScrollBtn = (Button) findViewById(R.id.btn_begin);
        mBeginScrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 移动View中的内容，水平方向，正数是向左，竖直方向，正数是向上
                 */
                mScrollTv.smoothScrollTo(-200, -300);
            }
        });



    }
}
