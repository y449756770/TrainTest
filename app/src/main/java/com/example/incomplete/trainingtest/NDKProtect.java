package com.example.incomplete.trainingtest;

import android.util.Log;

/**
 * Created by incomplete on 17/9/7.
 */

public class NDKProtect {

    public  native String getString();
    public  native String getJniString();
    public  native String getCodeString();
    static{
        try {

            System.loadLibrary("protect1");
        } catch (Exception e) {
            // TODO: handle exception
            Log.d("CCDebug", "load lib exception");
        }

    }
}
