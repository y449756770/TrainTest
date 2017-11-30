package com.example.incomplete.trainingtest.design_pattern;

/**
 * Created by incomplete on 17/7/25.
 * <p>
 * 静态内部类的单例模式
 * <p>
 * 优点：JVM本身的机制保证了线程安全，不会有性能问题
 * <p>
 * static final
 */

public class SingleTon {
    private SingleTon() {

    }

    public static SingleTon getInstance() {
        return SingleTonHolder.mSingleTon;
    }

    private static class SingleTonHolder {
        private static SingleTon mSingleTon = new SingleTon();
    }
}
