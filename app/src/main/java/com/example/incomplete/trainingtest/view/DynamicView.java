package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.incomplete.trainingtest.R;

/**
 * Created by incomplete on 17/8/7.
 * <p>
 * 微博滑动Tab的滑动条
 */

public class DynamicView extends View {
    private float startX, stopX;//的起始X,终止X坐标。
    private Paint mPaint;
    private RectF rectF = new RectF(startX, 0, stopX, 0);


    public DynamicView(Context context) {
        this(context, null);
    }

    public DynamicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
        //渐变色
//        mPaint.setShader(new LinearGradient(0, 100, ViewTools.getScreenWidth(context), 100, Color.parseColor("#ffc125"), Color.parseColor("#ff4500"), Shader.TileMode.MIRROR));
        mPaint.setColor(getResources().getColor(R.color.orange2));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(20, MeasureSpec.getMode(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rectF.set(startX, 0, stopX, 10);

        canvas.drawRoundRect(rectF, 5, 5, mPaint);//圆角矩形的圆角的曲率
    }

    /**
     * 重新绘制View
     *
     * @param startX
     * @param stopX
     */

    public void updateView(float startX, float stopX) {
        this.startX = startX;
        this.stopX = stopX;
        invalidate();

    }

}
