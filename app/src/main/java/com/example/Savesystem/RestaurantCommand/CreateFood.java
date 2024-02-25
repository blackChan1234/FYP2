package com.example.Savesystem.RestaurantCommand;

import com.example.Savesystem.command.command;

import java.util.ArrayList;
import java.util.Stack;
import com.example.Savesystem.Restaurant.*;


public class CreateFood implements command {

	private Food f;
	private Restaurant r;

	/**
	 * 
	 * @param r
	 * @param name
	 * @param Ingredients
	 * @param Nutritions
	 */
	public CreateFood(Restaurant r, String name, ArrayList<Ingredient> Ingredients, ArrayList<Nutrition> Nutritions) {
		this.f = new Food( name,Ingredients, Nutritions);
		this.r = r;
	}

	/**
	 * 
	 * @param r
	 * @param name
	 */
	public CreateFood(Restaurant r, String name) {
		this.f = new Food( name);
		this.r = r;
	}

	@Override
	public void execute() {

	}
}