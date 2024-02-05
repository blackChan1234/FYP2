package com.example.Savesystem.Restaurant;

import java.util.ArrayList;
import java.util.Stack;

public class Restaurant {

	private String loc;
	private String name;
	private ArrayList<comboMeal> cms;
	private ArrayList<Food> fs;
	private schedule sch;

	public String getLoc() {
		return loc;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<comboMeal> getALLComboMeals() {
		return cms;
	}

	public ArrayList<Food> getALLFoods() {
		return fs;
	}
	/**
	 * 
	 * @param loc
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param cm
	 */
	public void removeComboMeal(comboMeal cm) {
		cms.remove(cm);
	}

	/**
	 * 
	 * @param index
	 */
	public comboMeal removeComboMealByIndex(int index) {
		return cms.remove(index);
	}

	/**
	 * 
	 * @param comboMeal
	 */
	public void addComboMeal(comboMeal cm) {
		cms.add(cm);
	}

	public ArrayList<Food> getFoods() {
		return fs;
	}

	public schedule getSch() {
		return this.sch;
	}

	/**
	 * 
	 * @param sch
	 */
	public void setSch(schedule sch) {
		this.sch = sch;
	}

}