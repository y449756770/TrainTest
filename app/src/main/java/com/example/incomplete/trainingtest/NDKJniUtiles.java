package com.example.incomplete.trainingtest;

/**
 * Created by incomplete on 17/8/30.
 */

public class NDKJniUtiles {

    static {
        System.loadLibrary("LcbKey");//.so文件格式为:lib+库名+.so
    }
    public native String getJniString();//函数名与C代码的函数名保持一致



}

