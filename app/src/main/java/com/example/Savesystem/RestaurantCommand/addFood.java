package com.example.Savesystem.RestaurantCommand;

import com.example.Savesystem.command.*;
import com.example.Savesystem.Restaurant.*;

public class addFood implements command.command {

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
}