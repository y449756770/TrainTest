package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.incomplete.trainingtest.R;


/**
 * 仿iphone带进度的进度条，线程安全的View，可直接在线程中更新进度
 *
 * @author liuyong
 * @author
 */
public class RoundProgressBar extends View {

    public static final int STROKE = 0;
    public static final int FILL = 1;


    /**
     * 画笔对象的引用
     */
    private Paint mPaint;

    /**
     * 圆环的颜色
     */
    private int mRoundColor;

    /**
     * 圆环进度的颜色
     */
    private int mRoundProgressColor;

    /**
     * 第二圆环进度的颜色
     */
    private int mRoundSecondProgressColor;

    /**
     * 标准完整度65%的tip的颜色
     */
    private int mDefaultTextColor;

    /**
     * 中间进度百分比的tip的颜色
     */
    private int mTipTextColor;

    /**
     * 中间进度百分比的字符串的颜色
     */
    private int mTextColor;

    /**
     * 中间进度百分比的字符串的字体
     */
    private float mTextSize;

    /**
     * 中间进度百分比tip
     */
    private float mTipTextSize;

    /**
     * 圆环的宽度
     */
    private float mRoundWidth;

    /**
     * 最大进度
     */
    private int mMax;

    /**
     * 当前进度
     */
    private int mProgress;

    /**
     * 是否显示中间的进度
     */
    private boolean mTextIsDisplayable;

    /**
     * 进度的风格，实心或者空心
     */
    private int mStyle;

    /**
     * 第二环进度
     */
    private int mSecondProgress;

    /**
     * 右上角文字颜色
     */
    private int mDefaultTipTextColor;

    /**
     * 圆环半径
     */
    private float mRadius;

    private String mTip = "当前进度";

    private String mTip2 = "标准进度:";

    /**
     * 中间进度百分比的字符串的字体
     */
    private float mTip2TextSize;


    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mPaint = new Paint();

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        //获取自定义属性和默认值
        mRoundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        mRoundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        mRoundSecondProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundSecondProgressColor, Color.GREEN);
        mDefaultTextColor = mTypedArray.getColor(R.styleable.RoundProgressBar_tipTextColor, Color.TRANSPARENT);
        mDefaultTipTextColor = mTypedArray.getColor(R.styleable.RoundProgressBar_defaultTipTextColor, Color.TRANSPARENT);
        mTipTextColor = mTypedArray.getColor(R.styleable.RoundProgressBar_tipTextColor, Color.GREEN);
        mTextColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
        mTipTextSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_tipTextSize, 12);
        mTextSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 18);
        mRoundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 25);
        mMax = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
        mTextIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        mStyle = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);
        mSecondProgress = mTypedArray.getInt(R.styleable.RoundProgressBar_secondProgress, 0);
        mRadius = mTypedArray.getDimension(R.styleable.RoundProgressBar_radius, 45);
        mTip2TextSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_tip2TextSize, 33);

        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int centreX = getWidth() / 2;   //中心点
        final int centreY = getHeight() / 2; //中心点
        final int radius = (int) (mRadius); //圆环的半径

        final float degree = 360 * mSecondProgress / 100f + 90;
        canvas.save();
        canvas.rotate(degree, centreX, centreY);

        if (mSecondProgress > 0) {

            //右上角斜线
            int lineLength = dip2px(30);
            mPaint.setColor(mDefaultTipTextColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            mPaint.setAntiAlias(true); //消除锯齿
            int startX = centreX + radius;
            int startY = centreY;
            int stopX = centreX + radius + lineLength;
            int stopY = centreY;
            canvas.drawLine(startX, startY, stopX, stopY, mPaint);
            canvas.restore();

            //计算右上角文字宽度
            final int space = 10;
            final String tip = mTip2 + mSecondProgress + "%";
            mPaint.setStrokeWidth(0);
            mPaint.setTextSize(mTip2TextSize);
            float tw = mPaint.measureText(tip); //测量字体宽度，我们需要根据字体的宽度设置在圆环中间

            //画右上角横线
            mPaint.setColor(mDefaultTipTextColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            float x = (float) (centreX + (radius + lineLength) * Math.cos(2 * Math.PI / 360 * (360 - degree)));
            float y = (float) (centreY - (radius + lineLength) * Math.sin(2 * Math.PI / 360 * (360 - degree)));

            float endX = x + tw + space * 2;   //字两边留出空隙
            float endY = y;
            canvas.drawLine(x, y, endX, endY, mPaint);

            //画右上角文字
            if (mTextIsDisplayable) {
                mPaint.setStrokeWidth(0);
                mPaint.setTextSize(mTip2TextSize);
                mPaint.setTypeface(Typeface.DEFAULT); //设置字体
                canvas.drawText(tip, x + space, y - mTip2TextSize / 2, mPaint); //画出文字
            }
        }


        /**
         * 画最外层的大圆环
         */
        mPaint.setColor(mRoundColor); //设置圆环的颜色
        mPaint.setStyle(Paint.Style.STROKE); //设置空心
        mPaint.setStrokeWidth(mRoundWidth); //设置圆环的宽度
        mPaint.setAntiAlias(true); //消除锯齿
        canvas.drawCircle(centreX, centreY, radius, mPaint); //画出圆环

        /**
         * 第二圆环,65标准宽度
         */
        mPaint.setStrokeWidth(mRoundWidth); //设置圆环的宽度
        mPaint.setColor(mRoundSecondProgressColor); //设置进度的颜色
        RectF oval = new RectF(centreX - radius, centreY - radius, centreX + radius, centreY + radius); //用于定义的圆弧的形状和大小的界限
        canvas.save();
        canvas.rotate(90, centreX, centreY);
        switch (mStyle) {
            case STROKE:
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(oval, 0, 360 * mSecondProgress / 100f, false, mPaint); //根据进度画圆弧
                break;
            case FILL:
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (mProgress != 0) {
                    canvas.drawArc(oval, 0, 360 * mSecondProgress / 100f, true, mPaint); //根据进度画圆弧
                }
                break;
            default:
                break;
        }
        canvas.restore();


        /**
         * "当前完整度"
         */
        final String tip = mTip;
        mPaint.setStrokeWidth(0);
        mPaint.setColor(Color.parseColor("#999999"));
        mPaint.setTextSize(mTipTextSize);
        mPaint.setTypeface(Typeface.DEFAULT); //设置字体
        int percent = (int) (((float) mProgress / (float) mMax) * 100); //中间的进度百分比，先转换成float在进行除法运算，不然都为0
        float tw = mPaint.measureText(tip); //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        if (mTextIsDisplayable && percent != 0 && mStyle == STROKE) {
            canvas.drawText(tip, centreX - tw / 2, centreY - mTextSize / 3, mPaint); //画出文字
        }

        /**
         * "当前完整度" 百分比
         */
        mPaint.setStrokeWidth(0);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.setTypeface(Typeface.DEFAULT); //设置字体
        float textWidth = mPaint.measureText(String.valueOf(percent)); //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
        float percentWidth = mPaint.measureText("%"); //测量字体宽度
        if (mTextIsDisplayable && percent != 0 && mStyle == STROKE) {
            canvas.drawText(percent + "%", centreX - (textWidth + percentWidth) / 2, centreY + mTextSize, mPaint); //画出进度百分比
        }


        /**
         * 画圆弧 ，画圆环的进度
         */
        mPaint.setStrokeWidth(mRoundWidth); //设置圆环的宽度
        mPaint.setColor(mRoundProgressColor); //设置进度的颜色
        RectF rectF = new RectF(centreX - radius, centreY - radius, centreX + radius, centreY + radius); //用于定义的圆弧的形状和大小的界限
        canvas.save();
        canvas.rotate(90, centreX, centreY);
        switch (mStyle) {
            case STROKE:
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawArc(rectF, 0, 360 * mProgress / mMax, false, mPaint); //根据进度画圆弧


                break;
            case FILL:
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (mProgress != 0) {
                    canvas.drawArc(rectF, 0, 360 * mProgress / mMax, true, mPaint); //根据进度画圆弧
                }
                break;
            default:
                break;
        }
        canvas.restore();


    }

    public synchronized int getMax() {
        return mMax;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("mMax not less than 0");
        }
        this.mMax = max;
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int getProgress() {
        return mProgress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("mProgress not less than 0");
        }
        if (progress > mMax) {
            progress = mMax;
        }
        if (progress <= mMax) {
            this.mProgress = progress;
            postInvalidate();
        }

    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 刷新界面调用postInvalidate()能在非UI线程刷新 动态绘制
     *
     * @param progress
     */
    public synchronized void threadDraw(int progress) {
        new DrawThread(progress).start();
    }

    class DrawThread extends Thread {

        private int mProgress;

        public DrawThread(int progress) {
            if (progress < 0) {
                progress = 0;
            }
            if (progress > mMax) {
                progress = mMax;
            }

            this.mProgress = progress;
            RoundProgressBar.this.mProgress = 0;
        }

        @Override
        public void run() {

            while (RoundProgressBar.this.mProgress < this.mProgress) {
                postInvalidate();
                RoundProgressBar.this.mProgress++;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Log.e("TAG", "Exception:" + e.getMessage());
                }
            }
        }

    }

    public int getCricleColor() {
        return mRoundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.mRoundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return mRoundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.mRoundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float textSize) {
        this.mTextSize = textSize;
    }

    public float getRoundWidth() {
        return mRoundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.mRoundWidth = roundWidth;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
