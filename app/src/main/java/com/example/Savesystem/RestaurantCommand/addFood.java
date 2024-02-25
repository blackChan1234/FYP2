package com.example.Savesystem.RestaurantCommand;

import com.example.Savesystem.Restaurant.*;
import com.example.Savesystem.command.command;

public class addFood implements command {

	private comboMeal cm;
	private Food f;
	/**
	 * 
	 * @param cm
	 * @param f
	 */
	public addFood(comboMeal cm, Food f) {
		this.cm = cm;
		this.f = f;
	}

	@Override
	public void execute() {

	}
}