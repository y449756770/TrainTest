package com.example.incomplete.trainingtest.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.incomplete.trainingtest.R;

/**
 * Created by incomplete on 17/8/15.
 */

public class ArcView extends View {
    Paint mPaint = new Paint();


    public ArcView(Context context) {
        this(context, null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int centreX = getWidth() / 2;   //中心点
        final int centreY = getHeight() / 2; //中心点

        mPaint.setColor(getResources().getColor(R.color.orange2));
        canvas.drawArc(centreX - 200, centreY - 200, centreX + 200, centreY + 200, 0, 30, true, mPaint);

        mPaint.setColor(getResources().getColor(R.color.dimgrey));
        canvas.drawArc(centreX - 240, centreY - 240, centreX + 160, centreY + 160, 40, 40, true, mPaint);

        mPaint.setColor(getResources().getColor(R.color.ald_text));
        canvas.drawArc(centreX - 240, centreY - 240, centreX + 240, centreY + 240, 90, 50, true, mPaint);

        canvas.save();


    }
}
