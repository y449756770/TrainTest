package com.example.incomplete.trainingtest;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.incomplete.trainingtest.activity.AsyncActivity;
import com.example.incomplete.trainingtest.activity.BaseActivity;
import com.example.incomplete.trainingtest.activity.CardActivity;
import com.example.incomplete.trainingtest.activity.CollectionActivity;
import com.example.incomplete.trainingtest.activity.CustomTrainingActivity;
import com.example.incomplete.trainingtest.activity.DataActivity;
import com.example.incomplete.trainingtest.activity.DesignActivity;
import com.example.incomplete.trainingtest.activity.DialActivity;
import com.example.incomplete.trainingtest.activity.GenericActivity;
import com.example.incomplete.trainingtest.activity.JniActivity;
import com.example.incomplete.trainingtest.activity.LeakActivity;
import com.example.incomplete.trainingtest.activity.LockActivity;
import com.example.incomplete.trainingtest.activity.MetialDesignActivity;
import com.example.incomplete.trainingtest.activity.ObjectAnimaterActivity;
import com.example.incomplete.trainingtest.activity.ScrollActivity;
import com.example.incomplete.trainingtest.activity.TipActivity;
import com.example.incomplete.trainingtest.activity.TouchEventActivity;
import com.example.incomplete.trainingtest.activity.WeiboTABActivity;
import com.example.incomplete.trainingtest.activity.WindowActivity;
import com.example.incomplete.trainingtest.activity.rxjavaActivity;
import com.example.incomplete.trainingtest.view.RoundProgressBar;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    Button btn_touch;
    Button btn_lock;
    Button btn_collection;
    Button btn_data;
    Button btn_leak;
    Button btn_async;
    Button btn_generic;
    Button btn_recyler;
    Button btn_rxjava;
    Button btn_dial;
    Button btn_desiagn;
    Button btn_md;
    Button btn_scroll;
    Button btn_weibo_tab;
    Button btn_scroll_card;
    Button btn_custom_view;
    Button btn_drag_view_helper;
    Button btn_tip;
    Button btn_window;
    Button btn_jni;
    Button btn_objectAnimater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_touch = (Button) findViewById(R.id.btn_touch_event);
        btn_touch.setOnClickListener(this);

        btn_lock = (Button) findViewById(R.id.btn_lock);
        btn_lock.setOnClickListener(this);

        btn_collection = (Button) findViewById(R.id.btn_collection);
        btn_collection.setOnClickListener(this);

        btn_data = (Button) findViewById(R.id.btn_data);
        btn_data.setOnClickListener(this);


        /**
         *  模拟内存泄漏
         */
        btn_leak = (Button) findViewById(R.id.btn_leak);
        btn_leak.setOnClickListener(this);

        btn_async = (Button) findViewById(R.id.btn_async);
        btn_async.setOnClickListener(this);

        btn_generic = (Button) findViewById(R.id.btn_generic);
        btn_generic.setOnClickListener(this);

        btn_recyler = (Button) findViewById(R.id.btn_recyler);
        btn_recyler.setOnClickListener(this);


        btn_rxjava = (Button) findViewById(R.id.btn_rxjava);
        btn_rxjava.setOnClickListener(this);


        btn_dial = (Button) findViewById(R.id.btn_dial);
        btn_dial.setOnClickListener(this);


        btn_desiagn = (Button) findViewById(R.id.btn_design);
        btn_desiagn.setOnClickListener(this);

        btn_md = (Button) findViewById(R.id.btn_md);
        btn_md.setOnClickListener(this);

        btn_scroll = (Button) findViewById(R.id.btn_scroll);
        btn_scroll.setOnClickListener(this);

        btn_weibo_tab = (Button) findViewById(R.id.btn_weibo_tab);
        btn_weibo_tab.setOnClickListener(this);

        btn_scroll_card = (Button) findViewById(R.id.btn_scroll_card);
        btn_scroll_card.setOnClickListener(this);


        btn_custom_view = (Button) findViewById(R.id.btn_custom_view);
        btn_custom_view.setOnClickListener(this);

        btn_drag_view_helper = (Button) findViewById(R.id.btn_drag_view_helper);
        btn_drag_view_helper.setOnClickListener(this);

        btn_tip = (Button) findViewById(R.id.btn_tip);
        btn_tip.setOnClickListener(this);

        btn_window = (Button) findViewById(R.id.btn_window);
        btn_window.setOnClickListener(this);

        btn_jni = (Button) findViewById(R.id.btn_jni);
        btn_jni.setOnClickListener(this);

        btn_objectAnimater=(Button)findViewById(R.id.btn_objectAnimater);
        btn_objectAnimater.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_touch_event:  //事件分发
                Intent intent = new Intent(MainActivity.this, TouchEventActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_lock:  //线程的锁
                Intent intentLock = new Intent(MainActivity.this, LockActivity.class);
                startActivity(intentLock);
                break;
            case R.id.btn_collection:  //集合
                Intent intentCollection = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intentCollection);
                break;

            case R.id.btn_data:  //数据结构
                Intent intentData = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intentData);
                break;

            case R.id.btn_leak:  //数据结构
                Intent intentLeak = new Intent(MainActivity.this, LeakActivity.class);
                startActivity(intentLeak);
                break;


            case R.id.btn_async:  //数据结构
                Intent intentAsync = new Intent(MainActivity.this, AsyncActivity.class);
                startActivity(intentAsync);
                break;

            case R.id.btn_generic:  //范型
                Intent intentGeneric = new Intent(MainActivity.this, GenericActivity.class);
                startActivity(intentGeneric);
                break;

            case R.id.btn_recyler:  //数据结构
                Intent intentRecycler = new Intent(MainActivity.this, RecylerActivity.class);
                startActivity(intentRecycler);
                break;
            case R.id.btn_rxjava:  //rxjava
                Intent intentRxJava = new Intent(MainActivity.this, rxjavaActivity.class);
                startActivity(intentRxJava);
                break;

            case R.id.btn_dial:  //rxjava
                Intent btn_dialIntent = new Intent(MainActivity.this, DialActivity.class);
                startActivity(btn_dialIntent);
                break;
            case R.id.btn_design://设计模式

                Intent designIntent = new Intent(MainActivity.this, DesignActivity.class);
                startActivity(designIntent);
                break;


            case R.id.btn_md: //metialDesign

                Intent mdIntent = new Intent(MainActivity.this, MetialDesignActivity.class);
                startActivity(mdIntent);
                break;

            case R.id.btn_scroll: //滑动视图

                Intent mScroll = new Intent(MainActivity.this, ScrollActivity.class);
                startActivity(mScroll);
                break;

            case R.id.btn_weibo_tab: //模仿微博的Tab

                Intent mWeiboTabb = new Intent(MainActivity.this, WeiboTABActivity.class);
                startActivity(mWeiboTabb);
                break;

            case R.id.btn_scroll_card: // 滑动卡片

                Intent mCardIntent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(mCardIntent);
                break;


            case R.id.btn_custom_view: // 滑动卡片

                Intent mCustomIntent = new Intent(MainActivity.this, CustomTrainingActivity.class);
                startActivity(mCustomIntent);
                break;

            case R.id.btn_drag_view_helper: // 拖拽view

                Intent mDragIntent = new Intent(MainActivity.this, CustomTrainingActivity.class);
                startActivity(mDragIntent);
                break;
            case R.id.btn_tip:

                Intent mTipsIntent = new Intent(MainActivity.this, TipActivity.class);
                startActivity(mTipsIntent);


                break;

            case R.id.btn_window:

                Intent mWindowIntent = new Intent(MainActivity.this, WindowActivity.class);
                startActivity(mWindowIntent);

                break;

            case R.id.btn_jni:

                Intent jniIntent = new Intent(MainActivity.this, JniActivity.class);
                startActivity(jniIntent);

                break;


            case R.id.btn_objectAnimater:

                Intent objectAnimaterIntent = new Intent(MainActivity.this, ObjectAnimaterActivity.class);
                startActivity(objectAnimaterIntent);

                break;







        }

    }

    /**
     * 带标示的刻度盘
     *
     * @param context
     */
    public static Dialog showCompletionDialog(final Context context, final int integrity, final boolean isPreRead, final CharSequence message) {

        final int inter = integrity < 20 ? 20 : integrity;
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_completion, null);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        if (isPreRead) {
            cancel.setText("预览");
        } else {
            cancel.setText("取消");
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        TextView commit = (TextView) view.findViewById(R.id.edit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView msg = (TextView) view.findViewById(R.id.msg);
        msg.setText(message);

        final RoundProgressBar round = (RoundProgressBar) view.findViewById(R.id.roundProgressBar);
        round.setProgress(0);
        dialog.setContentView(view);
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.width = getScreenWidth(context);
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(R.style.dialogAnim1);
        mWindow.setAttributes(lp);
        dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    round.threadDraw(inter);
                }
            }
        }, 600);
        return dialog;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

}
