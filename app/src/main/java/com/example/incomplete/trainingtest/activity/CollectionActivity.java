package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.incomplete.trainingtest.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * compareTo
 * <p>
 * 返回0  只存一个元素
 * <p>
 * 返回正数  正序存储
 * <p>
 * 返回负数  倒序存储
 */

public class CollectionActivity extends BaseActivity implements View.OnClickListener {

    Button btn_test_set;
    ArrayList<Persion> mPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        btn_test_set = (Button) findViewById(R.id.btn_test_set);
        btn_test_set.setOnClickListener(this);
        setSwipeBackEnable(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test_set:

                addPerson();
                break;
        }

    }

    /**
     * 用TreeSet存储persion
     */

    public void addPerson() {
        mPersons = new ArrayList<>();
        mPersons.add(new Persion("wangwu", 23));
        mPersons.add(new Persion("wangwu2", 15));
        mPersons.add(new Persion("wangwu3", 32));
        mPersons.add(new Persion("wangwu4", 12));
        mPersons.add(new Persion("wangwu5", 43));
        mPersons.add(new Persion("wangwu6", 45));
        mPersons.add(new Persion("wangwu7", 65));
        Collections.sort(mPersons);

        Log.i("the result", mPersons.toString());

    }

    class Persion implements Comparable<Persion> {
        public String name;
        public int age;

        public Persion(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Persion another) {
            return this.age - another.age;  //按照age正序排列
        }

        @Override
        public String toString() {
            return "name is:" + name + "age is:" + age;
        }
    }
}
