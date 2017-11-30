package com.example.incomplete.trainingtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.incomplete.trainingtest.R;

/**
 * Created by incomplete on 17/8/17.
 */

public class BitmapShaderView extends View {
    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Paint paint = new Paint();

//        Bitmap bitmap_logo = BitmapFactory.decodeResource(getResources(), R.mipmap.batman_logo);
//        BitmapShader shader_logo = new BitmapShader(bitmap_logo, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);


        // ComposeShader：结合两个 Shader
//        Shader shader_com = new ComposeShader(shader_logo, shader, PorterDuff.Mode.SRC_OVER);
//        paint.setShader(shader);

//        Paint paint = new Paint();
        paint.setShader(shader);
        canvas.drawCircle(width / 2, height / 2, height / 2, paint);
    }
}
