package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.incomplete.trainingtest.utiles.TouchEventUtil;

/**
 * Created by incomplete on 17/4/12.
 * <p>
 * <p>
 * 1,当onTouchEvent 返回true 和 super时，自己消费事件
 * 2，当onTouchEvent 返回false的时候传递到父控件
 */

public class Btn extends Button {
    ViewGroup parent;

    public Btn(Context context) {
        super(context);
    }

    public Btn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Btn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("sunzn", "TouchEventBtn | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//        return super.dispatchTouchEvent(ev);
//		return true;
//        return false;

        /**
         * 内部拦截法
         */
        switch (ev.getAction()) {
            /**
             * 设置这个以后父控件不能拦截move和up
             */
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            /**
             * 让父控件拦截move和up
             */
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_UP://这里不写是因为事件已经又父控件处理了，事件不会流到这里，写了没有意义
                break;


        }
        return super.dispatchTouchEvent(ev);
    }


    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("sunzn", "TouchEventBtn | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//        return super.onTouchEvent(ev);
        return true;
//        return false;
    }
}
