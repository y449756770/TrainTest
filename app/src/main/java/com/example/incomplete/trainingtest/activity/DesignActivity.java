package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.design_pattern.People;
import com.example.incomplete.trainingtest.design_pattern.coffeebar.Drink;
import com.example.incomplete.trainingtest.design_pattern.coffeebar.coffee.Decaf;
import com.example.incomplete.trainingtest.design_pattern.coffeebar.coffee.LongBlack;
import com.example.incomplete.trainingtest.design_pattern.coffeebar.decorator.Chocolate;
import com.example.incomplete.trainingtest.design_pattern.coffeebar.decorator.Milk;

/**
 * 设计模式
 */

public class DesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        decorDesigh();
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

    /**
     * 装饰者模式，业务需求，设计不同的咖啡，不同的配料，自由组合
     */

    public void decorDesigh() {
//        Drink order;
//        order = new Decaf();
//        System.out.println("order1 price:" + order.cost());
//        System.out.println("order1 desc:" + order.getDescription());
//
//        System.out.println("****************");
//        order = new LongBlack();
//        order = new Milk(order);
//        order = new Chocolate(order);
//        order = new Chocolate(order);
//        System.out.println("order2 price:" + order.cost());
//        System.out.println("order2 desc:" + order.getDescription());


        new Decaf();


    }


}
