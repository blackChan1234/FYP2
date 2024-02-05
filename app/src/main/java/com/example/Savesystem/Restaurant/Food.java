package Restaurant;

import java.util.ArrayList;
import java.util.Stack;

public class Food {

	private String name;
	private ArrayList<Ingredient> Ingredients;
	private ArrayList<Nutrition> Nutritions;
	private float cost;

	public String getName() {
		return this.name;
	}

	public ArrayList<Ingredient> getALLIngredients() {
		return this.Ingredients;
	}

	public ArrayList<Nutrition> getALLNutritions() {

		return Nutritions;
	}

	/**
	 * 
	 * @param name
	 */
	public Food(String name) {
		this.name = name;
		Ingredients = new ArrayList<Ingredient>();
		Nutritions = new ArrayList<Nutrition>();
	}

	/**
	 * 
	 * @param ings
	 */
	public void setALLIngredients(ArrayList<Ingredient> ings) {
		this.Ingredients = ings;
	}

	/**
	 * 
	 * @param nuts
	 */
	public void setALLNutritions(ArrayList<Nutrition> nuts) {
		this.Nutritions=nuts;
	}

	/**
	 * 
	 * @param cost
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getCost() {
		return this.cost;
	}

	/**
	 * 
	 * @param name
	 * @param Ingredients
	 * @param Nutritions
	 */
	public Food(String name, ArrayList<Ingredient> Ingredients, ArrayList<Nutrition> Nutritions) {
		this.name = name;
		this.Nutritions = Nutritions;
		this.Ingredients = Ingredients;

	}

}