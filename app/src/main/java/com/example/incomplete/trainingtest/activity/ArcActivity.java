package com.example.incomplete.trainingtest.activity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.view.ArcView;

/**
 * 演示新的支付宝刻度盘
 */

public class ArcActivity extends BaseActivity {

    LinearLayout mRootView;
    ArcView arc_view;

    private final int[] mColors = new int[] {
            0xFFFF80AB,
            0xFFFF4081,
            0xFFFF5177,
            0xFFFF7997
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc);
        mRootView=(LinearLayout) findViewById(R.id.rootLayout);
        arc_view=(ArcView)findViewById(R.id.arc_view);
        arc_view.setSesameValues(650);
        startColorChangeAnim();
    }


    public void startColorChangeAnim() {

        ObjectAnimator animator = ObjectAnimator.ofInt(mRootView, "backgroundColor", mColors);
        animator.setDuration(3000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }
}
