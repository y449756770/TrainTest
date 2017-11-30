package com.example.incomplete.trainingtest.Listener;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.incomplete.trainingtest.utiles.ViewTools;
import com.example.incomplete.trainingtest.view.DynamicView;
import com.example.incomplete.trainingtest.view.LikeWeiboHorizentalScrollView;

import java.util.ArrayList;

import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;

/**
 * Created by incomplete on 17/8/8.
 */

public class OnTabPageChangeListener implements ViewPager.OnPageChangeListener {
    private int fixLeftDis;
    private ArrayList<TextView> textViews;
    private LikeWeiboHorizentalScrollView viewPagerTitle;
    private DynamicView dynamicView;
    private ViewPager pager;
    private int pagerCount;
    private int screenWidth;
    private int lineWidth;
    private int everyLength;
    private int lastPosition;
    private int dis;
    private int[] location = new int[2];
    private Context context;


    public OnTabPageChangeListener(Context context, LikeWeiboHorizentalScrollView viewPagerTitle) {
        this.context = context;
        this.viewPagerTitle = viewPagerTitle;
        this.pager = viewPagerTitle.viewPager;
        this.dynamicView = viewPagerTitle.mDynamicView;
        textViews = (ArrayList) viewPagerTitle.mTextViews;
        pagerCount = textViews.size();
        screenWidth = ViewTools.getScreenWidth(context);
        lineWidth = (int) ViewTools.getTextViewLength(textViews.get(0));
        everyLength = viewPagerTitle.mAllTextViewsLenth / pagerCount;
        dis = viewPagerTitle.margin;
        this.fixLeftDis = viewPagerTitle.offset;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (lastPosition > position) {//页面向右滚动 /**
//             *档页面向右滚动时，dynamicLine的右边的stopX位置不变，startX在变化。
//             */
            dynamicView.updateView((position + positionOffset) * everyLength + dis + fixLeftDis, (lastPosition + 1) * everyLength - dis);
        } else { //页面向左滚动 /**
//             *档页面向左滚动时，dynamicLine的左边的startX位置不变，stopX在变化。
//             */
            if (positionOffset > 0.5f) {
                positionOffset = 0.5f;
            }
            dynamicView.updateView(lastPosition * everyLength + dis + fixLeftDis, (position + positionOffset * 2) * everyLength + dis + lineWidth);
        }


    }


    @Override
    public void onPageSelected(int position) {

        viewPagerTitle.setCurrentItem(position);

    }

    /**
     * state 的几个状态：
     * SCROLL_STATE_IDLE  挂起，空闲，页面处于静止状态
     * SCROLL_STATE_DRAGGING 拖拽，页面处于拖拽状态
     * SCROLL_STATE_SETTLING 设置，手指滑动后当手指离开页面时
     *
     * @param state
     */

    @Override
    public void onPageScrollStateChanged(int state) {

        boolean scrollRight;//页面向右
         if (state == SCROLL_STATE_SETTLING) {
         scrollRight = lastPosition < pager.getCurrentItem();
         lastPosition = pager.getCurrentItem();
//             *下面几行代码，解决页面滑到的TAB页时对应的TextView对应，TextView处于屏幕外面，
//             *这个时候就需要将HorizontalScrollView滑动到屏幕中间。
//             */
        if (lastPosition + 1 < textViews.size() && lastPosition - 1 >= 0) {
            textViews.get(scrollRight ? lastPosition + 1 : lastPosition - 1).getLocationOnScreen(location);
            if (location[0] > screenWidth) {
                viewPagerTitle.smoothScrollBy(screenWidth / 2, 0);
            } else if (location[0] < 0) {
                viewPagerTitle.smoothScrollBy(-screenWidth / 2, 0);
            }
        }
    }


}
}
