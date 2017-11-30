package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.design_pattern.People;

/**
 * 设计模式
 */

public class DesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
    }

    /**
     * 单例模式
     */

    public void singleTonTest() {

    }

    /**
     * Builder设计模式
     */
    People people = new People.Builder("12")
                              .firstName("liu")
                              .build();
}
