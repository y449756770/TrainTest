package com.example.incomplete.trainingtest;

import android.util.Log;

/**
 * Created by incomplete on 17/8/11.
 * <p>
 * 模拟生产者消费者的类
 */

public class ProductAndConsume {
    public int count = 1;
    public String name = "KaoYa";
    public boolean flag = false;
    public String str;


    public synchronized void produce() throws InterruptedException {
        if (flag) {
            wait();
            Log.i("produce", "product唤醒");

        }

        str = name + count;
        count++;
        Log.i("produce", "product a str:" + str);
        flag = true;
        notifyAll();


    }

    public synchronized void consume() throws InterruptedException {
        if (!flag) {
            wait();
            Log.i("consume", "consume唤醒");

        }

        Log.i("consume", "sonsume a str:" + str);
        flag = false;
        notifyAll();


    }
}
