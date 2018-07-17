package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liuyong on 2017/12/20.
 * <p>
 * http://www.jianshu.com/p/d11892bbe055
 */

public class XfermodeDetailView extends View {

    Bitmap srcBit;
    Bitmap desBit;


    public XfermodeDetailView(Context context) {
        this(context, null);
    }

    public XfermodeDetailView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeDetailView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawARGB(255, 139, 197, 186);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int layerId = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
        /**
         * 绘制圆形  目标图
         */
        desBit = createDesBitmap(width, height);
        canvas.drawBitmap(desBit, 0, 0, mPaint);


        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        /**
         * 绘制矩形  原图
         */
        srcBit = createSrcBitmap(width, height);
        canvas.drawBitmap(srcBit, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);


    }

    /**
     * 创建目标bitmap
     * 圆形
     *
     * @param w
     * @param h
     * @return
     */
    public Bitmap createDesBitmap(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        /**
         * canvas绘制完以后会把
         */
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;


    }

    /**
     * 创建源bitmap
     * 矩形
     *
     * @param w
     * @param h
     * @return
     */
    public Bitmap createSrcBitmap(int w, int h) {

        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }


}
