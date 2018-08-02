package com.example.incomplete.trainingtest.design_pattern.coffeebar;

import android.util.Log;

public abstract class Drink {
    public String description = "";
    private float price = 0f;


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description + "-" + this.getPrice();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        Log.i("drink", "setPrice");
    }

    public abstract float cost();

}
