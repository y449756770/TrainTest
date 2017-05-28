package com.example.incomplete.trainingtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.incomplete.trainingtest.R;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 互斥锁test
 */

public class LockActivity extends AppCompatActivity {
    Printer printer = new Printer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);


        new Thread(new Runnable() {
            @Override
            public void run() {
                printer.printe1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                printer.printe2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                printer.printe3();
            }
        }).start();


    }

    class Printer {
        private int flag = 1;
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        public void printe1() {
            lock.lock();
            if (flag != 1) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Log.i("print*****", "" + flag);
            flag = 2;
            c2.signal();
            lock.unlock();
        }

        public void printe2() {
            lock.lock();
            if (flag != 2) {
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Log.i("print*****", "" + flag);
            flag = 3;
            c3.signal();
            lock.unlock();
        }

        public void printe3() {
            lock.lock();
            if (flag != 3) {
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Log.i("print*****", "" + flag);
            flag = 1;
            c1.signal();
            lock.unlock();
        }


    }
}
