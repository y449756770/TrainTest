package com.example.incomplete.trainingtest.testextends;

import android.util.Log;

/**
 * Created by incomplete on 17/7/23.
 */

public class TestFatherClass {
    static int fatherInt;

    public static void fatherStaticMethod() {
        Log.i("father", "fatherMethod");
    }

    {
        Log.i("father", "NonStaticBlock");
    }

    static {
        Log.i("father", "StaticBlock");
    }

    public TestFatherClass() {
        Log.i("father", "ConstrustMethod");
    }
    public void overMethod(){
        Log.i("father","overMethod");
    }
}
