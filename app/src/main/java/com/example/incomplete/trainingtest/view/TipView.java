package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.bean.StudyBean;

/**
 * Created by incomplete on 17/8/21.
 */

public class TipView extends FrameLayout {

    ImageView mIvType1;
    LinearLayout mLlType2;
    ImageView mIvType2;
    TextView mTvType2;

    int tipColor;
    String tipText;
    String tipText1;

    public void setTipText1(String tipText1) {
        this.tipText1 = tipText1;
        invalidate();
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;

    }

    public TipView(@NonNull Context context) {
        this(context, null);
    }

    public TipView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tip_item_layout, this);
        mIvType1 = (ImageView) findViewById(R.id.img_type1);

        mLlType2 = (LinearLayout) findViewById(R.id.ll_type2);
        mIvType2 = (ImageView) findViewById(R.id.img_type2);
        mTvType2 = (TextView) findViewById(R.id.tv_count);


    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);


        int width = getWidth();
        int height = getHeight();

        float tipWidth = (float) (width / (2 * Math.cos(2 * Math.PI / 360 * 45)));
        float tipHeight = (float) (width / 2 * Math.cos(2 * Math.PI / 360 * 45));

        canvas.save();
        canvas.rotate(-45, width / 2, height);

        Paint mBackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackPaint.setColor(tipColor);
        mBackPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(width / 2, height, width / 2 + tipWidth, height + tipHeight, mBackPaint);


        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setTextSize(50);

        canvas.drawText(tipText, width / 2 + (tipWidth - mPaint.measureText(tipText)) / 2, height + tipHeight * 2 / 3, mPaint);
        canvas.restore();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public void setType(int type) {
        switch (type) {
            case StudyBean.Type_unstudy: //未开始

                tipColor = getResources().getColor(R.color.orange2);

                mIvType1.setVisibility(View.VISIBLE);
                mIvType1.setImageResource(R.drawable.w0);
                mLlType2.setVisibility(View.GONE);

                break;
            case StudyBean.Type_studing:  //正在学
                tipColor = Color.RED;

                mIvType1.setVisibility(View.GONE);

                mLlType2.setVisibility(View.VISIBLE);
                mIvType2.setImageResource(R.drawable.w0);
                setTipText(tipText);


                break;

            case StudyBean.Type_studyed:  //学完了

                tipColor = Color.GREEN;

                mIvType1.setVisibility(View.VISIBLE);
                mIvType1.setImageResource(R.drawable.w0);
                mLlType2.setVisibility(View.GONE);

                break;

            case StudyBean.Type_waiting:  //暂缓开通
                tipColor = Color.LTGRAY;

                mIvType1.setVisibility(View.VISIBLE);
                mIvType1.setImageResource(R.drawable.w0);
                mLlType2.setVisibility(View.GONE);
                break;
        }
        invalidate();


    }
}
