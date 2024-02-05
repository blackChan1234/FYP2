package RestaurantCommand;

import Restaurant.Food;
import Restaurant.Ingredient;
import command.command;

public class AddIngredient implements command {
    private Food f;
    private Ingredient i;

    public AddIngredient(Food f, Ingredient i) {
        this.f = f;
        this.i = i;

    }
    

}