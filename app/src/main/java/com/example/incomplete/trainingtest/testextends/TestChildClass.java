package com.example.incomplete.trainingtest.testextends;

import android.util.Log;

/**
 * Created by incomplete on 17/7/23.
 */

/**
 * 结果如下所示
 * I/father: StaticBlock
 * 07-23 21:26:20.186 8293-8293/com.example.incomplete.trainingtest I/child: StaticBlock
 * 07-23 21:26:20.186 8293-8293/com.example.incomplete.trainingtest I/father: NonStaticBlock
 * 07-23 21:26:20.186 8293-8293/com.example.incomplete.trainingtest I/father: ConstrustMethod
 * 07-23 21:26:20.186 8293-8293/com.example.incomplete.trainingtest I/child: NonStaticBlock
 * 07-23 21:26:20.186 8293-8293/com.example.incomplete.trainingtest I/child: ConstrustMethod
 */

public class TestChildClass extends TestFatherClass {
    static int childInt;
    public String name;

    public static void fatherStaticMethod() {
        Log.i("child", "fatherMethod");
    }

    {
        Log.i("child", "NonStaticBlock");
    }

    static {
        Log.i("child", "StaticBlock");
    }

    public TestChildClass() {
        Log.i("child", "ConstrustMethod");
        overMethod();
    }

    public TestChildClass(String str) {

        Log.i("child", "HaveParamConstrustMethod");
//        overMethod();
    }


    @Override
    public void overMethod() {
//        super.overMethod();
        Log.i("child", "overMethod");
    }
}
