package com.example.Savesystem.Restaurant;
import java.util.ArrayList; // import the ArrayList class


public class comboMeal {

	private String name;
	private float price;
	private ArrayList<Food> fs;
	private image img;
	private Restaurant r;
	private schedule sch;
	private String description;

	public String getName() {
		return name;
	}

	public ArrayList<Food> getFoods() {
		return fs;
	}

	public ArrayList<Ingredient> getALLIngredients() {
		// TODO - implement comboMeal.getALLIngredients
		throw new UnsupportedOperationException();
	}

	public ArrayList<Nutrition> getALLNutritions() {
		// TODO - implement comboMeal.getALLNutritions
		throw new UnsupportedOperationException();
	}

	public Restaurant getResturant() {
		return r;
	}


	public comboMeal(String name, float price, ArrayList<Food> fs) {
		this.name = name;
		this.price = price;
		this.fs = fs;
	}

	/**
	 * 
	 * @param r
	 */
	public void setRestaurant(Restaurant r) {
		this.r = r;
	}

	/**
	 * 
	 * @param fs
	 */
	public void setFoods(ArrayList<Food> fs) {
		this.fs = fs;
	}
	/**
	 *
	 */

	public image getImg() {
		return img;
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
	 * @param f
	 */
	public void addFood(Food f) {
		this.fs.add(f);
	}

	/**
	 * 
	 * @param index
	 */
	public Food removeFoodByIndex(int index) {
		return this.fs.remove(index);
	}

	/**
	 * 
	 * @param f
	 */
	public boolean removeFood(Food f) {
		return this.fs.remove(f);
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(float price) {
		this.price =price;
	}

	public float getPrice() {
		return this.price;
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

	public String getDescription() {
		return this.description;
	}

}