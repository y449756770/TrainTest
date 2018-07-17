package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.incomplete.trainingtest.utiles.TouchEventUtil;

public class TouchEventChilds extends LinearLayout {

    public TouchEventChilds(Context context) {
        super(context);
    }

    public TouchEventChilds(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("sunzn", "TouchEventChilds | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
        /**
         * 内部拦截规则
         */
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//
//        }
//        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("sunzn", "TouchEventChilds | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                break;

            case MotionEvent.ACTION_MOVE:
                intercepted = true;
                break;


            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;


        }
        return intercepted;
//        return super.onInterceptTouchEvent(ev);

    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("sunzn", "TouchEventChilds | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));

//        return true;
        /**
         * 外部拦截要消耗move事件
         */
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                return true;
//
//        }
        return super.onTouchEvent(ev);
    }

}
