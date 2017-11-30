package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.utiles.ViewTools;

/**
 * Created by incomplete on 17/9/23.
 */

public class RoundProgress extends View {
    float radus= ViewTools.dpToPixel(80);
    float progress=0;
    RectF mRectF=new RectF();



    public RoundProgress(Context context) {
        this(context,null);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        Paint mPaint =new Paint();
        mPaint.setColor(Color.parseColor("#E91E63"));
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE); //描边
        mPaint.setStrokeCap(Paint.Cap.ROUND); //设置圆角的
        mPaint.setStrokeWidth(ViewTools.dpToPixel(15));
        mRectF.set(centerX-radus,centerY-radus,centerX+radus,centerY+radus);
        //顺时针转过135度，扫过270度
        canvas.drawArc(mRectF,90, (float) (progress*2.7),false,mPaint);

        mPaint.setColor(getResources().getColor(R.color.orange));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(100);
        String progressText=(int) progress + "%";
        canvas.drawText(progressText, (getWidth()-mPaint.measureText(progressText))/2, centerY - (mPaint.ascent() + mPaint.descent()) / 2, mPaint);



    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }
}
