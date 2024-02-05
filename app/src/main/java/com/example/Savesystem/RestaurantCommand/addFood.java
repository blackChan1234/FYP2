package RestaurantCommand;

import command.*;
import Restaurant.*;

public class addFood implements command {

	private comboMeal cm;
	private Food f;
	/**
	 * 
	 * @param r
	 * @param f
	 */
	public addFood(comboMeal cm, Food f) {
		this.cm = cm;
		this.f = f;
	}
}