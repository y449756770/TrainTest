package com.example.incomplete.trainingtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.data.BinaryTree;

/**
 * 数据结构
 */

public class DataActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_binaryTree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        btn_binaryTree = (Button) findViewById(R.id.btn_binary_tree);
        btn_binaryTree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_binary_tree:
                testBinaryTree();

                break;
        }

    }

    /**
     * 实现二叉树，用中序遍历
     */

    public void testBinaryTree() {
        BinaryTree bt = new BinaryTree();
        bt.add(3);
        bt.add(2);
        bt.add(14);
        bt.add(5);
        bt.add(6);
        bt.add(54);
        bt.add(43);
        bt.add(46);
        bt.add(12);
        bt.add(15);
        bt.add(13);
        bt.add(7);
        bt.print();


    }
}
