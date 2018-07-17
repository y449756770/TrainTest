package com.example.incomplete.trainingtest.design_pattern.coffeebar.decorator;


import com.example.incomplete.trainingtest.design_pattern.coffeebar.Drink;

public class Soy extends Decorator {

	public Soy(Drink Obj) {
		super(Obj);
		// TODO Auto-generated constructor stub
		super.setDescription("Soy");
		super.setPrice(1.5f);
	}

}

