package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Savesystem.Restaurant.Food;
import com.example.Savesystem.Restaurant.Ingredient;
import com.example.Savesystem.Restaurant.Nutrition;
import com.example.fyp.adapters.IngredientsAdapter;
import com.example.fyp.adapters.NutritionFactsAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class foodrecommend extends AppCompatActivity {
    private RecyclerView nutritionFactsRecyclerView, ingredientsRecyclerView;
    private ArrayList<Nutrition> nutritionFactsList = new ArrayList<>();
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private TextView Name, mealCategory;
    private ImageView mealImage;
    private NutritionFactsAdapter nutritionFactsAdapter;
    private IngredientsAdapter ingredientsAdapter;
    private OkHttpClient client;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodrecommend);

        // Initialize OkHttpClient
        client = new OkHttpClient();

        // Retrieve the Food object
        id = getIntent().getStringExtra("CURRENT_MEAL_ID");
        if (id == null) {
            Log.e("foodrecommend", "No CURRENT_MEAL_ID found in intent");
            finish(); // Close the activity or inform the user
            return;
        }

        initializeViews();

        // Fetch food details using the ID
        fetchFoodDetails(id);
    }

    private void initializeViews() {
        Name = findViewById(R.id.mealName);
        mealCategory = findViewById(R.id.mealCategory); // Update or remove based on your data model
        mealImage = findViewById(R.id.mealImage);
        nutritionFactsRecyclerView = findViewById(R.id.nutritionFactsRecyclerView);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);

        // Set layout managers
        nutritionFactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchFoodDetails(String id) {
        String url = "http://10.0.2.2/FYP/api_getDetailMeal.php?id=" + id;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // Handle network failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.e("succeful","succeful");
                    final String responseData = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseData);
                        Log.v("jsonObject", jsonObject.toString());
                        // Retrieve and set data in UI
                        try {
                            populateData(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void populateData(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString("title");
        String image = jsonObject.getString("image");
        JSONObject nutritionObject = jsonObject.getJSONObject("nutrition");
        JSONArray ingredientsArray = jsonObject.getJSONObject("ingredients").getJSONArray("ingredients");

        // Set text views and image views based on data

            Name.setText(title);
            // Assuming you have a method to set the image
            // For example, using Glide:
            Glide.with(this).load(image).into(mealImage);

        // Populate the lists for nutrition facts and ingredients
        JSONArray nutrientsArray = nutritionObject.getJSONArray("nutrients");
        for (int i = 0; i < nutrientsArray.length(); i++) {
            JSONObject nutrientObject = nutrientsArray.getJSONObject(i);
            String name = nutrientObject.getString("name");
            double amount = nutrientObject.getDouble("amount");
            String unit = nutrientObject.getString("unit");
            Nutrition nutrition = new Nutrition(name, amount, unit);
            nutritionFactsList.add(nutrition);
            Log.d("foodrecommend", "Nutrition Facts List: " + nutritionFactsList);
        }

        for (int i = 0; i < ingredientsArray.length(); i++) {
            JSONObject ingredientObject = ingredientsArray.getJSONObject(i);
            String name = ingredientObject.getString("name");
            String i_image = ingredientObject.getString("image");
            Ingredient ingredient = new Ingredient(name, i_image);
            ingredientsList.add(ingredient);
            Log.d("foodrecommend", "Ingredients List: " + ingredientsList);
        }


        nutritionFactsAdapter = new NutritionFactsAdapter(nutritionFactsList);
        ingredientsAdapter = new IngredientsAdapter(ingredientsList, this);

        // Set adapters
        nutritionFactsRecyclerView.setAdapter(nutritionFactsAdapter);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        // Notify the adapters
        nutritionFactsAdapter.notifyDataSetChanged();
        ingredientsAdapter.notifyDataSetChanged();
    }
    private void fetchFoodDetailsInBackground(String id) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                fetchFoodDetails(id);
            }
        });
        thread.start();
    }
}
