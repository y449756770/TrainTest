package com.example.incomplete.trainingtest.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.view.RoundProgressBar;

//自定义View练习

public class DialActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_trust;
    Button btn_enter_window;

    /**
     * 天气仪表盘
     */
    Button btn_weather_dial;

    Button btn_arc_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);

        btn_trust = (Button) findViewById(R.id.btn_trust);
        btn_trust.setOnClickListener(this);

        btn_enter_window = (Button) findViewById(R.id.btn_enter_window);
        btn_enter_window.setOnClickListener(this);


        btn_weather_dial = (Button) findViewById(R.id.btn_weather_dial);
        btn_weather_dial.setOnClickListener(this);


        btn_arc_test = (Button) findViewById(R.id.btn_arc_test);
        btn_arc_test.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_trust:  //刻度盘
                Intent intentResume = new Intent(DialActivity.this, ResumeTrustActivity.class);
                startActivity(intentResume);
                break;

            case R.id.btn_enter_window:  //从下而上出现的刻度盘

                showCompletionDialog(this, 70, true, "取消");

                break;


            case R.id.btn_weather_dial:  //天气仪表盘
                Intent intentWeather = new Intent(DialActivity.this, WeatherDialActivity.class);
                startActivity(intentWeather);


                break;

            case R.id.btn_arc_test:  //天气仪表盘
                Intent intentArc = new Intent(DialActivity.this, ArcActivity.class);
                startActivity(intentArc);


                break;


        }
    }

    /**
     * 带标示的刻度盘
     *
     * @param context
     */
    public static Dialog showCompletionDialog(final Context context, final int integrity, final boolean isPreRead, final CharSequence message) {

        final int inter = integrity < 20 ? 20 : integrity;
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_completion, null);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        if (isPreRead) {
            cancel.setText("预览");
        } else {
            cancel.setText("取消");
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView commit = (TextView) view.findViewById(R.id.edit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView msg = (TextView) view.findViewById(R.id.msg);
        msg.setText(message);

        final RoundProgressBar round = (RoundProgressBar) view.findViewById(R.id.roundProgressBar);
        round.setProgress(0);
        dialog.setContentView(view);
        Window mWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = mWindow.getAttributes();
        lp.width = getScreenWidth(context);
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setWindowAnimations(R.style.dialogAnim1);
        mWindow.setAttributes(lp);
        dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null && dialog.isShowing()) {
                    round.threadDraw(inter);
                }
            }
        }, 600);
        return dialog;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
