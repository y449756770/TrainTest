package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.incomplete.trainingtest.Listener.OnTabPageChangeListener;
import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.utiles.ViewTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by incomplete on 17/8/7.
 * <p>
 * 类似微博的滑动Tab,文字下面的横条可以在切换Tab的时候拉长
 */

public class LikeWeiboHorizentalScrollView extends HorizontalScrollView {
    public String[] mTitles; //Tab的内容
    public List<TextView> mTextViews = new ArrayList<>(); //内部放置的TextView
    public float mMargin;  //TextView之间的空隙
    public float mDefaultTextSize = 18;
    public int mDefaultTextColor = getResources().getColor(R.color.dimgrey);
    public float mSelectTextSize = 22;
    public int mSelectTextColor = getResources().getColor(R.color.black);
    public int mAllTextLenth;
    public LinearLayout.LayoutParams contentParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    public LinearLayout.LayoutParams textParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    public ViewPager viewPager;
    public DynamicView mDynamicView;
    public int mAllTextViewsLenth;
    public int margin;
    public int offset;


    public LikeWeiboHorizentalScrollView(Context context) {
        this(context, null);
    }

    public LikeWeiboHorizentalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LikeWeiboHorizentalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void init(String[] titles, ViewPager viewPager) {
        this.mTitles = titles;
        this.viewPager = viewPager;
        createDynamicLine();
        createTextViews(this.mTitles);
        offset = getFixLeftDis();

        OnTabPageChangeListener mPageLister = new OnTabPageChangeListener(getContext(), this);
        setDefaultIndex(0);
        viewPager.addOnPageChangeListener(mPageLister);


    }


    /**
     * 创建底部下划线
     */
    private void createDynamicLine() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDynamicView = new DynamicView(getContext());
        mDynamicView.setLayoutParams(params);
    }

    private void createTextViews(String[] titles) {
        LinearLayout mLinearLayout = new LinearLayout(getContext());
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mLinearLayout.setLayoutParams(contentParam);
        mLinearLayout.setBackgroundResource(R.color.bkg_base);
        addView(mLinearLayout);

        LinearLayout mHorContainer = new LinearLayout(getContext());
        mHorContainer.setOrientation(LinearLayout.HORIZONTAL);
        mHorContainer.setLayoutParams(contentParam);

        margin = (int) getTextMargins(mTitles);


        textParam.setMargins(margin, 0, margin, 0);

        for (int i = 0; i < titles.length; i++) {
            TextView mText = new TextView(getContext());
            mText.setLayoutParams(textParam);
            mText.setText(titles[i]);
            mText.setTextColor(mDefaultTextColor);
            mText.setGravity(Gravity.CENTER_HORIZONTAL);
            mText.setTextSize(mDefaultTextSize);
            mText.setTag(i);
            mTextViews.add(mText);
            mHorContainer.addView(mText);
        }
        mLinearLayout.addView(mHorContainer);
        mLinearLayout.addView(mDynamicView);

    }

    private float getTextMargins(String[] titles) {
        int defaultMargin = 30;
        float allTextLenth = 0;
        TextView mText = new TextView(getContext());
        mText.setTextSize(mDefaultTextSize);
        TextPaint mTextPaint = mText.getPaint();
        for (int i = 0; i < titles.length; i++) {
            allTextLenth += 2 * defaultMargin + mTextPaint.measureText(titles[i]);
        }
        int screenWidth = ViewTools.getScreenWidth(getContext());

        if (allTextLenth < screenWidth) {
            mAllTextViewsLenth = screenWidth;
            return screenWidth / titles.length - mTextPaint.measureText(titles[0]) / 2;

        } else {
            mAllTextViewsLenth = (int) allTextLenth;
            return defaultMargin;

        }


    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            setCurrentItem((int) v.getTag());
            viewPager.setCurrentItem((int) v.getTag());
        }
    };

    public void setCurrentItem(int index) {
        for (int i = 0; i < mTextViews.size(); i++) {
            if (i == index) {
                mTextViews.get(i).setTextColor(mSelectTextColor);
                mTextViews.get(i).setTextSize(mSelectTextSize);
            } else {
                mTextViews.get(i).setTextColor(mDefaultTextColor);
                mTextViews.get(i).setTextSize(mDefaultTextSize);
            }
        }
    }

    public void setDefaultIndex(int index) {
        setCurrentItem(index);
    }

    private int getFixLeftDis() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(mDefaultTextSize);
        textView.setText(mTitles[0]);
        float defaultTextSize = ViewTools.getTextViewLength(textView);
        textView.setTextSize(mSelectTextSize);
        float selectTextSize = ViewTools.getTextViewLength(textView);
        return (int) (selectTextSize - defaultTextSize) / 2;
    }


}
