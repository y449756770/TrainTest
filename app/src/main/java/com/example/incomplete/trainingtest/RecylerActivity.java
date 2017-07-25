package com.example.incomplete.trainingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;


/**
 * 测试RecylerView
 */

public class RecylerActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
//    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);


    }
}
