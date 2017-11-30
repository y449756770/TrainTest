package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.bean.StudyBean;
import com.example.incomplete.trainingtest.view.TipView;

public class TipActivity extends AppCompatActivity {
    TipView mTipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tip);
        setContentView(R.layout.type_item);

        mTipView = (TipView) findViewById(R.id.tip_view);
        mTipView.setType(StudyBean.Type_studyed);
        mTipView.setTipText("03期");
        mTipView.setBackgroundColor(getResources().getColor(R.color.dimgrey));


        test();
    }

    private void test() {
//        float avprice = 234.5678f;
//        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
//        String aveprice = df.format(avprice);
//        System.out.println(aveprice); //234.57
//
//        double av=0.1;
//        String av1 = df.format(av);
//        System.out.println(av1); //0.10
//
//
//        double avprice1 = 234.5678;
//        String aveprice1 = df.format(avprice1);
//        System.out.println(aveprice1); //234.57


        java.text.NumberFormat  formater  =  java.text.DecimalFormat.getInstance();
        formater.setMaximumFractionDigits(2);
        formater.setMinimumFractionDigits(2);
        System.out.println(formater.format(3.1415927));
        System.out.println(formater.format(3.1475927));
        System.out.println(formater.format(3.1));
        System.out.println(formater.format(0.0));






    }

}
