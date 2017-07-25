package com.example.incomplete.trainingtest.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import com.example.incomplete.trainingtest.R;


/**
 * Created by liuyong on 17/6/25.
 */

public class ResumeTrustView extends View {

    private Paint mBackGroundArcPaint;//背景

    private Paint mSelectArcPaint;//被选择

    private static final int mStroke = 20;//

    private Paint mBackgroundArcDot;//虚线背景

    private Paint mSelectArcDotPaint;//虚线被选择

    private Paint mIndictorPaint = new Paint();

    private Bitmap mIndicator;//箭头指示图标

    private RectF mArcRect = new RectF();

    private RectF mPathArcRect = new RectF();

    private RectF mBitMapRect = new RectF();

    private int mCircleRadius;

    private Path path = new Path();

    private Path dotSelectPath = new Path();

    private PathDashPathEffect sideEffects = null;

    private PathDashPathEffect effects = null;

    private Bitmap mBitmap;

    private float currentValue;

    private float density;//取手机密度

    private int mCircleValue = 130, mArcValue = 50;


    public ResumeTrustView(Context context) {
        this(context, null, 0);
    }

    public ResumeTrustView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResumeTrustView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec) - this.getPaddingRight() - this.getPaddingLeft();

        mCircleRadius = sizeWidth / 2 - mCircleValue;

        mArcRect.top = -mCircleRadius;
        mArcRect.bottom = mCircleRadius;
        mArcRect.left = -mCircleRadius;
        mArcRect.right = mCircleRadius;

        mPathArcRect.top = -mCircleRadius + mArcValue;
        mPathArcRect.bottom = mCircleRadius - mArcValue;
        mPathArcRect.left = -mCircleRadius + mArcValue;
        mPathArcRect.right = mCircleRadius - mArcValue;

        path.reset();
        path.addArc(mPathArcRect, 0, 180);

        mBitMapRect.top = -250;
        mBitMapRect.bottom = 150;
        mBitMapRect.left = -150;
        mBitMapRect.right = 150;


        setMeasuredDimension(sizeWidth, sizeWidth / 2);
    }

    private void initPaint() {

        density = getResources().getDisplayMetrics().density;

        if (density <= 2) {
            mCircleValue = 100;
            mArcValue = 40;
        } else if (density == 2.5) {
            mArcValue = 47;
        }

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.resume_inditor);

        mBackGroundArcPaint = new Paint();
        mBackGroundArcPaint.setAntiAlias(true);
        mBackGroundArcPaint.setColor(getResources().getColor(R.color.color_727272));
        mBackGroundArcPaint.setStrokeWidth(mStroke);
        mBackGroundArcPaint.setStyle(Paint.Style.STROKE);

        mSelectArcPaint = new Paint();
        mSelectArcPaint.setAntiAlias(true);
        mSelectArcPaint.setColor(getResources().getColor(R.color.color_orange));
        mSelectArcPaint.setStrokeWidth(mStroke);
        mSelectArcPaint.setStyle(Paint.Style.STROKE);

        mBackgroundArcDot = new Paint();
        mBackgroundArcDot.setAntiAlias(true);
        mBackgroundArcDot.setColor(getResources().getColor(R.color.color_727272));
        mBackgroundArcDot.setStrokeWidth(2);
        mBackgroundArcDot.setStyle(Paint.Style.STROKE);

        mSelectArcDotPaint = new Paint();
        mSelectArcDotPaint.setAntiAlias(true);
        mSelectArcDotPaint.setColor(getResources().getColor(R.color.color_orange));
        mSelectArcDotPaint.setStrokeWidth(2);
        mSelectArcDotPaint.setStyle(Paint.Style.STROKE);

        mIndictorPaint.setColor(Color.BLACK);
        mIndictorPaint.setAntiAlias(true);


        Path path = new Path();
        path.addCircle(0, 0, 3, Path.Direction.CCW);
        effects = new PathDashPathEffect(path, 10, 0, PathDashPathEffect.Style.ROTATE);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight()); // 将坐标系移动到画布中央
        canvas.scale(-1, -1);

        canvas.drawArc(mArcRect, 180, 360, false, mBackGroundArcPaint);

        canvas.drawArc(mArcRect, 180, 180 + currentValue, false, mSelectArcPaint);

        path.reset();
        path.addArc(mPathArcRect, currentValue, 180);
        mBackgroundArcDot.setPathEffect(effects);
        canvas.drawPath(path, mBackgroundArcDot);


        path.reset();
        path.addArc(mPathArcRect, 0, currentValue);
        mSelectArcDotPaint.setPathEffect(effects);
        canvas.drawPath(path, mSelectArcDotPaint);

        canvas.save();
        canvas.rotate(currentValue + 2);
        canvas.drawBitmap(mBitmap, mPathArcRect.right - mArcValue, -mBitmap.getHeight(), mIndictorPaint);
        canvas.restore();

    }


    public void startAnm(float value) {

        if (currentValue == value) {
            return;
        }

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(currentValue, value);
        valueAnimator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return 1 - (1 - v) * (1 - v) * (1 - v);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }
}
