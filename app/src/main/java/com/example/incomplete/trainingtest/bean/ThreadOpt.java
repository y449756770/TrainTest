package com.example.incomplete.trainingtest.bean;

import android.util.Log;

/**
 * Created by liuyong on 2017/12/1.
 */

public class ThreadOpt {

    //线程会共享成员变量
    private int num;

    public void count() {
//         int num=0;
        //如果有多个线程共享num则这个num会一直累加
        for (int i = 0; i <= 10; i++) {
            num += i;
        }
        Log.i("num is:", num + "");
    }

}
