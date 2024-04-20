package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Savesystem.Restaurant.Food;
import com.example.Savesystem.Restaurant.Ingredient;
import com.example.Savesystem.Restaurant.Nutrition;
import com.example.fyp.adapters.IngredientsAdapter; // Ensure you have this adapter
import com.example.fyp.adapters.NutritionFactsAdapter; // Ensure you have this adapter

import java.util.ArrayList;

public class foodrecommend extends AppCompatActivity {
    private RecyclerView nutritionFactsRecyclerView, ingredientsRecyclerView;
    private ArrayList<Nutrition> nutritionFactsList = new ArrayList<>();
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private TextView Name, mealCategory;
    private ImageView mealImage;
    private NutritionFactsAdapter nutritionFactsAdapter;
    private IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodrecommend);

        // Retrieve the Food object
        Food currentMeal = getIntent().getParcelableExtra("CURRENT_MEAL");


        if (currentMeal == null) {
            Log.e("foodrecommend", "No CURRENT_MEAL found in intent");
            finish(); // Close the activity or inform the user
            return;
        }

        initializeViews();
        mealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFeedbackDialog();
            }
        });
        Button feedbackButton = findViewById(R.id.feedbackButton);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFeedbackDialog();
            }
        });

        populateData(currentMeal);
    }

    private void initializeViews() {
        Name = findViewById(R.id.mealName);
        mealCategory = findViewById(R.id.mealCategory); // Update or remove based on your data model
        mealImage = findViewById(R.id.mealImage);
        nutritionFactsRecyclerView = findViewById(R.id.nutritionFactsRecyclerView);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);

        // Initialize adapters
        nutritionFactsAdapter = new NutritionFactsAdapter(nutritionFactsList);
        ingredientsAdapter = new IngredientsAdapter(ingredientsList, this);

        // Set adapters
        nutritionFactsRecyclerView = findViewById(R.id.nutritionFactsRecyclerView);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);
        nutritionFactsRecyclerView.setAdapter(nutritionFactsAdapter);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        // Set layout managers
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        nutritionFactsRecyclerView.setLayoutManager(horizontalLayoutManager1);
        ingredientsRecyclerView.setLayoutManager(horizontalLayoutManager2);


    }
    private void showFeedbackDialog() {
        String[] options = {"Like", "Dislike", "Not Interested"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Feedback on Meal");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // Handle "Like" option
                        Log.i("Feedback", "User liked the meal");
                        break;
                    case 1:
                        // Handle "Dislike" option
                        Log.i("Feedback", "User disliked the meal");
                        break;
                    case 2:
                        // Handle "Not Interested" option
                        Log.i("Feedback", "User is not interested in the meal");
                        break;
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void populateData(Food currentMeal) {
        // Set text views and image views based on currentMeal data
        Name.setText(currentMeal.getTitle());
        if (currentMeal.getDishTypes() != null && !currentMeal.getDishTypes().isEmpty()) {
            String dishTypesText = String.join(", ", currentMeal.getDishTypes());
            mealCategory.setText(dishTypesText);
        } else {
            mealCategory.setText("No category"); // Or some default text
        }


        // Assuming you have a method to set the image
        // For example, using Glide:
         Glide.with(this).load(currentMeal.getImage()).into(mealImage);

        // Populate the lists for nutrition facts and ingredients

        if (currentMeal.getNutritions() != null) {
            nutritionFactsList.addAll(currentMeal.getNutritions());
            nutritionFactsAdapter.notifyDataSetChanged(); // Notify the adapter
        }

        if (currentMeal.getIngredients() != null) {
            ingredientsList.addAll(currentMeal.getIngredients());
            ingredientsAdapter.notifyDataSetChanged(); // Notify the adapter
        }


    }
}
