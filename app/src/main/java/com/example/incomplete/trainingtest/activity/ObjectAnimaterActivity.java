package com.example.incomplete.trainingtest.activity;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.utiles.ViewTools;
import com.example.incomplete.trainingtest.view.RoundProgress;
import com.example.incomplete.trainingtest.view.SaleProgressView;
import com.nineoldandroids.view.ViewHelper;

public class ObjectAnimaterActivity extends AppCompatActivity implements View.OnClickListener {
    RoundProgress rg_animater;
    SaleProgressView rg_saleProgressView;

    private Button fab;
    private Button hide;
    private Button show;
    private int screenWidth;
    private int offX;
    private boolean anminling = false;

    private int mLastX;
    private int mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animater);
        rg_animater = (RoundProgress) findViewById(R.id.rg_animater);

        fab = (Button) findViewById(R.id.fab);
        screenWidth = ViewTools.getScreenWidth(this);
        hide = (Button) findViewById(R.id.hide);
        show = (Button) findViewById(R.id.show);
        hide.setOnClickListener(this);
        show.setOnClickListener(this);


        //应用程序App区域宽高等尺寸获取（包括contentView和appBar）
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Log.i("App区域宽高等尺寸", rect.left + "--" + rect.right + "--" + rect.top + "--" + rect.bottom);

        //获取状态栏高度
        int statusBarHeight = rect.top;
        Log.i("状态栏高度为－－", statusBarHeight + "");

        //View布局区域宽高等尺寸获取（除去状态栏和appBar的尺寸）
        Rect rect1 = new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect1);


        performAnimion();

        saleProgressbar();

        performValueAnimation();

//        fab.setOnClickListener(this);

        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("RawX:", "" + x);
                        Log.i("X:", "" + fab.getX());

                        break;
                    case MotionEvent.ACTION_MOVE:


                        int deltaX = x - mLastX;
                        int deltaY = y - mLastY;
                        int translationX = (int) ViewHelper.getTranslationX(fab) + deltaX;
                        int translationY = (int) ViewHelper.getTranslationY(fab) + deltaY;
                        ViewHelper.setTranslationX(fab, translationX);
                        ViewHelper.setTranslationY(fab, translationY);
                        Log.i("fragTag", "当前位置：" + x);
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                mLastX = x;
                mLastY = y;
                return true;
            }
        });


    }

    public void performAnimion() {

        //时间为0的时候，动画完成度是0
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        //时间为0.5的时候，动画完成度是100
        Keyframe keyframe2 = Keyframe.ofFloat(0.7f, 100);
        //时间为1的时候，动画完成度是80
        Keyframe keyframe3 = Keyframe.ofFloat(1, 80);

        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(rg_animater, holder);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * ValueAnimator相当于是值的发生器
     * 也能实现如上一个函数的效果
     */
    public void performValueAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100, 80);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animationFloat = (float) animation.getAnimatedValue();
                rg_animater.setProgress(animationFloat);

            }
        });
        valueAnimator.start();

    }

    public void saleProgressbar() {

        rg_saleProgressView = (SaleProgressView) findViewById(R.id.rg_saleprogress);
        rg_saleProgressView.setTotalAndCurrentCount(3000, 560);

    }


    @Override
    public void onClick(View v) {
        offX = screenWidth - fab.getWidth() / 2 - fab.getLeft();
        switch (v.getId()) {
            case R.id.fab:
                Toast.makeText(ObjectAnimaterActivity.this, "点击按钮", Toast.LENGTH_SHORT).show();

                break;
            case R.id.hide:

                ObjectAnimator animator = ObjectAnimator.ofFloat(fab, "translationX", offX).setDuration(200);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.e("AnimatorStart", "*********************");
                        Log.i("X:", "" + fab.getX());
                        Log.i("TranslationX:", "" + fab.getTranslationX());
                        Log.i("Left:", "" + fab.getLeft());
                        Log.i("PivotX", "" + fab.getPivotX());
                        Log.i("offX", "" + offX);

                        Log.e("AnimatorStart", "*********************");


                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.e("AnimatorEnd", "*********************");
                        Log.i("X:", "" + fab.getX());
                        Log.i("TranslationX:", "" + fab.getTranslationX());
                        Log.i("Left:", "" + fab.getLeft());
                        Log.i("PivotX", "" + fab.getPivotX());
                        Log.e("AnimatorEnd", "*********************");

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();


                break;
            case R.id.show:

                ObjectAnimator.ofFloat(fab, "translationX", 0).setDuration(200).start();

                break;


        }


    }


}
