package com.example.Savesystem.RestaurantCommand;

import com.example.Savesystem.command.command;

import com.example.Savesystem.Restaurant.Food;
import com.example.Savesystem.Restaurant.Ingredient;

public class AddIngredient implements command {
    private Food f;
    private Ingredient i;

    public AddIngredient(Food f, Ingredient i) {
        this.f = f;
        this.i = i;

    }


    @Override
    public void execute() {

    }
}