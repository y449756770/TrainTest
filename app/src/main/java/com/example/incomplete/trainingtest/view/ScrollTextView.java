package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by incomplete on 17/7/31.
 * <p>
 * 实现平滑的滑动的Text
 */


public class ScrollTextView extends TextView {
    Scroller mScroller;


    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);

    }

    public void smoothScrollTo(int desX, int desY) {
        int x = getScrollX();
        int needScrollX = desX - x;

        int y = getScrollY();
        int needScrollY = desY - y;

        mScroller.startScroll(x, y, needScrollX, needScrollY, 5000);
        invalidate();

    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            Log.i("Curr","CurrX is "+mScroller.getCurrX()+":CurrY is "+mScroller.getCurrY());
            postInvalidate();

        }
    }
}
