package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private RecyclerView nutritionFactsRecyclerView;
    private RecyclerView ingredientsRecyclerView;
    private List<NutritionFact> nutritionFactsList = new ArrayList<>();
    private List<Ingredient> ingredientsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findrestaurants);

        nutritionFactsList.add(new NutritionFact("Calories", "484 kcal"));
        ingredientsList.add(new Ingredient("Salmon", "@drawable/salmon"));
        nutritionFactsRecyclerView = findViewById(R.id.nutritionFactsRecyclerView);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);

        // Set up the adapters for both RecyclerViews
        // Assuming you have lists for nutrition facts and ingredients
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