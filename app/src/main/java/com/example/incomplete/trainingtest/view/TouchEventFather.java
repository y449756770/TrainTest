package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.incomplete.trainingtest.utiles.TouchEventUtil;

public class TouchEventFather extends LinearLayout {

    public TouchEventFather(Context context) {
        super(context);
    }

    public TouchEventFather(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("sunzn", "TouchEventFather | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("sunzn", "TouchEventFather | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//        boolean intercepted = false;
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                intercepted = false;
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                intercepted = true;
//                break;
//
//
//            case MotionEvent.ACTION_UP:
//                intercepted = false;
//                break;
//
//
//        }
//        return intercepted;

        /**
         * 内部拦截，父控件拦截除了down以外的所有事件
         */

//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            return false;
//
//        } else {
//            return true;
//
//        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("sunzn", "TouchEventFather | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onTouchEvent(ev);

    }

}
