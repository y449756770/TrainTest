package com.example.incomplete.trainingtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.incomplete.trainingtest.activity.AsyncActivity;
import com.example.incomplete.trainingtest.activity.CollectionActivity;
import com.example.incomplete.trainingtest.activity.DataActivity;
import com.example.incomplete.trainingtest.activity.GenericActivity;
import com.example.incomplete.trainingtest.activity.LeakActivity;
import com.example.incomplete.trainingtest.activity.LockActivity;
import com.example.incomplete.trainingtest.activity.TouchEventActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_touch;
    Button btn_lock;
    Button btn_collection;
    Button btn_data;
    Button btn_leak;
    Button btn_async;
    Button btn_generic;
    Button btn_recyler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_touch = (Button) findViewById(R.id.btn_touch_event);
        btn_touch.setOnClickListener(this);

        btn_lock = (Button) findViewById(R.id.btn_lock);
        btn_lock.setOnClickListener(this);

        btn_collection = (Button) findViewById(R.id.btn_collection);
        btn_collection.setOnClickListener(this);

        btn_data = (Button) findViewById(R.id.btn_data);
        btn_data.setOnClickListener(this);


        /**
         *  模拟内存泄漏
         */
        btn_leak = (Button) findViewById(R.id.btn_leak);
        btn_leak.setOnClickListener(this);

        btn_async = (Button) findViewById(R.id.btn_async);
        btn_async.setOnClickListener(this);

        btn_generic = (Button) findViewById(R.id.btn_generic);
        btn_generic.setOnClickListener(this);

        btn_recyler = (Button) findViewById(R.id.btn_recyler);
        btn_recyler.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_touch_event:  //事件分发
                Intent intent = new Intent(MainActivity.this, TouchEventActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_lock:  //线程的锁
                Intent intentLock = new Intent(MainActivity.this, LockActivity.class);
                startActivity(intentLock);
                break;
            case R.id.btn_collection:  //集合
                Intent intentCollection = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intentCollection);
                break;

            case R.id.btn_data:  //数据结构
                Intent intentData = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intentData);
                break;

            case R.id.btn_leak:  //数据结构
                Intent intentLeak = new Intent(MainActivity.this, LeakActivity.class);
                startActivity(intentLeak);
                break;


            case R.id.btn_async:  //数据结构
                Intent intentAsync = new Intent(MainActivity.this, AsyncActivity.class);
                startActivity(intentAsync);
                break;

            case R.id.btn_generic:  //数据结构
                Intent intentGeneric = new Intent(MainActivity.this, GenericActivity.class);
                startActivity(intentGeneric);
                break;

            case R.id.btn_recyler:  //数据结构
                Intent intentRecycler = new Intent(MainActivity.this, RecylerActivity.class);
                startActivity(intentRecycler);
                break;


        }

    }
}
