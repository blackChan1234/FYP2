package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class foodrecommend extends AppCompatActivity {
    private RecyclerView nutritionFactsRecyclerView, ingredientsRecyclerView;
    private ArrayList<NutritionFact> nutritionFactsList = new ArrayList<>();
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodrecommend);

        //example data
        nutritionFactsList.add(new NutritionFact("Calories", "484 kcal"));
        ingredientsList.add(new Ingredient("Salmon", "a001", this)); // Notice 'this' is passed for context

        // Initialize  lists with data
        nutritionFactsRecyclerView = findViewById(R.id.nutritionFactsRecyclerView);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);

        // Set up the adapters for both RecyclerViews
        NutritionFactsAdapter nutritionFactsAdapter = new NutritionFactsAdapter(nutritionFactsList);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredientsList);

        nutritionFactsRecyclerView.setAdapter(nutritionFactsAdapter);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        // Set the layout manager to horizontal for both RecyclerViews
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        nutritionFactsRecyclerView.setLayoutManager(horizontalLayoutManager1);
        ingredientsRecyclerView.setLayoutManager(horizontalLayoutManager2);
    }
}