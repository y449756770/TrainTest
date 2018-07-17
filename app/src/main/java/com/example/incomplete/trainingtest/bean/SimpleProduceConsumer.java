package com.example.incomplete.trainingtest.bean;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyong on 2017/12/2.
 */

public class SimpleProduceConsumer {

    /**
     * 装鸡蛋的盘子
     */
    List<Object> eggs = new ArrayList<Object>();


    public synchronized void getEggs() {
        while (eggs.size() == 0) {
            try {
//                Log.i("拿鸡蛋", "拿鸡蛋等待");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        eggs.get(0);
        eggs.clear();
        notifyAll();//这里会唤醒阻塞队列中的一个线程，也有可能唤醒自身
        Log.i("拿鸡蛋", "拿鸡蛋完成");


    }

    public synchronized void putEggs(Object egg) {
        while (eggs.size() != 0) {
            try {
//                Log.e("放鸡蛋", "放鸡蛋等待");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        eggs.add(egg);
        notifyAll();
        Log.e("放鸡蛋", "放鸡蛋完成");


    }

    public static class getEggRunnable implements Runnable {
        SimpleProduceConsumer bean;

        public  getEggRunnable(SimpleProduceConsumer bean) {
            this.bean = bean;

        }

        @Override
        public void run() {
            bean.getEggs();

        }
    }

    public static class putEggRunnable implements Runnable {
        SimpleProduceConsumer bean;

        public putEggRunnable(SimpleProduceConsumer bean) {
            this.bean = bean;
        }

        @Override
        public void run() {
            bean.putEggs(new SimpleProduceConsumer());


        }
    }


}
