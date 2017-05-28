package com.example.incomplete.trainingtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.grneric.GenericClass;
import com.example.incomplete.trainingtest.grneric.GrnericInter;
import com.example.incomplete.trainingtest.grneric.SubclassGr;
import com.example.incomplete.trainingtest.grneric.SubclassGr2;

/**
 * 范型测试
 */

public class GenericActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);

        GrnericInter<String> genericClass = new SubclassGr();
        genericClass.showGe("dtr");

        GenericClass<String> testClass = new GenericClass<>();
        testClass.setT("test");
        testClass.showGene("string");

        SubclassGr2<String> subClass1 = new SubclassGr2<>();
        SubclassGr2<Integer> subClass2 = new SubclassGr2<>();

        test(subClass1);
//        test(subClass2);


    }

    /**
     * 设置类的上限
     *
     * @param subClass
     */

    void test(SubclassGr2<? extends String> subClass) {


    }
}
