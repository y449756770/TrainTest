package com.example.incomplete.trainingtest.bean;

import android.util.Log;

/**
 * Created by incomplete on 17/8/23.
 */

public class TestWait {

    public synchronized void goWait() {
        while (true) {
            try {
                Log.i("wait ", "********");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }
}
