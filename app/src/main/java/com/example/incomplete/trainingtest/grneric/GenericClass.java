package com.example.incomplete.trainingtest.grneric;

import android.util.Log;

/**
 * Created by incomplete on 17/4/26.
 * 范型类
 */

public class GenericClass<T> {

    private T t;


    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    /**
     * 用类的范型
     *
     * @param t
     */

    public void print(T t) {
        Log.i("Generic is *****", t.toString());
    }

    /**
     * @param e
     * @param <E> 自己的范型类型
     */
    public <E> void showGene(E e) {
        Log.i("Generic is *****", e.toString());

    }

    /**
     * 静态方法必须有自己的范型 ，这里的Q和类里的Q不一样
     *
     * @param q
     * @param <R>
     */
    public static <R> void showStatic(R q) {


    }
}
