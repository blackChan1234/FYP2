package com.example.fyp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.Savesystem.Restaurant.Amount;
import com.example.Savesystem.Restaurant.Food;
import com.example.Savesystem.Restaurant.Ingredient;
import com.example.Savesystem.Restaurant.Nutrition;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecommendFragment extends Fragment {
    private List<Food> meals = new ArrayList<>();
    private int currentMealIndex = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        Button btnJumpToFoodRecommend = view.findViewById(R.id.btnJumpToFoodRecommend);

        btnJumpToFoodRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private void showDialog() {
        Log.d("RecommendFragment", "showDialog() called");
        // Create a Dialog instance
        final Dialog dialog = new Dialog(getContext());
        // Set the custom layout
        dialog.setContentView(R.layout.dialog_food_recommendation);

        // Initialize dialog elements if you need to interact with them
        TextView mealNameTextView = dialog.findViewById(R.id.mealname);
        ImageView foodimageView = dialog.findViewById(R.id.food);
        Button btnAnother = dialog.findViewById(R.id.another);
        // Example button click listener
        Button okButton = dialog.findViewById(R.id.Button2);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), foodrecommend.class);
                    // Put the meal name as an extra in the Intent
                    intent.putExtra("MEAL_NAME", mealNameTextView.getText().toString());
                    startActivity(intent);
                    dialog.dismiss(); // Close the dialog
                }
            }
        });
        btnAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayMeal(mealNameTextView, foodimageView); // Show next meal
            }
        });


        // Fetch JSON data and update UI elements
        fetchJsonAndPopulateUI(mealNameTextView, foodimageView, dialog);

        // Display the dialog
        dialog.show();
    }

    private void displayMeal(final TextView mealNameTextView, final ImageView foodimageView) {
        if (getActivity() == null || meals.isEmpty()) return;

        final Food currentMeal = meals.get(currentMealIndex % meals.size());
        currentMealIndex++; // Prepare index for the next call

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mealNameTextView.setText(currentMeal.getTitle());
                Glide.with(requireContext())
                        .load(currentMeal.getImage())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.loading)
                                .error(R.drawable.error) // Error placeholder
                                .transform(new RoundedCorners(10)))
                        .into(foodimageView);
            }
        });
    }
    private void fetchJsonAndPopulateUI(final TextView mealNameTextView, final ImageView foodimageView, final Dialog dialog) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2/FYP/getData.php"; // Ensure this URL is correct and accessible

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // Log error or notify user
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseData = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(responseData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        boolean isVegetarian = jsonObject.getBoolean("vegetarian");
                        boolean isGlutenFree = jsonObject.getBoolean("glutenFree");
                        // Filter based on user preferences
                       // if (matchesUserPreferences(isVegetarian, isGlutenFree)) {
                            Food meal = new Food(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("title"),
                                    jsonObject.getString("image"),
                                    jsonObject.getString("sourceUrl"),
                                    isVegetarian,
                                    jsonObject.getBoolean("vegan"),
                                    isGlutenFree,
                                    jsonObject.getBoolean("dairyFree"),
                                    jsonObject.getInt("preparationMinutes"),
                                    jsonObject.getInt("cookingMinutes"),
                                    jsonObject.getInt("aggregateLikes"),
                                    jsonObject.getDouble("healthScore"),
                                    jsonObject.getString("creditsText"),
                                    jsonObject.getDouble("pricePerServing"),
                                    jsonObject.getInt("readyInMinutes"),
                                    jsonObject.getInt("servings"),
                                    jsonObject.getString("summary"),
                                    convertJsonArrayToList(jsonObject.getJSONArray("cuisines")),
                                    convertJsonArrayToList(jsonObject.getJSONArray("dishTypes")),
                                    convertJsonArrayToList(jsonObject.getJSONArray("diets")),
                                    // Assume methods to parse and create lists of ingredients and nutritions
                                    parseIngredients(jsonObject),
                                    parseNutritions(jsonObject)
                            );
                            displayMeal(mealNameTextView, foodimageView); // Show next meal
                            dialog.show();
                            meals.add(meal);
                        }
                   // }
                    // Proceed to display the first meal and set up button listeners
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            private ArrayList<Ingredient> parseIngredients(JSONObject jsonObject) {
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                try {
                    JSONObject ingredientsJSONObject = jsonObject.getJSONObject("ingredients");
                    JSONArray ingredientsJsonArray = ingredientsJSONObject.getJSONArray("ingredients");
                    for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                        JSONObject ingredientObject = ingredientsJsonArray.getJSONObject(i);
                        JSONObject amountObject = ingredientObject.getJSONObject("amount");
                        JSONObject metricObject = amountObject.getJSONObject("metric");
                        double metricValue = metricObject.getDouble("value");
                        String metricUnit = metricObject.getString("unit");
                        JSONObject usObject = amountObject.getJSONObject("us");
                        double usValue = usObject.getDouble("value");
                        String usUnit = usObject.getString("unit");

                        Ingredient ingredient = new Ingredient(
                                ingredientObject.getString("name"),
                                ingredientObject.getString("image"),
                                new Amount(metricValue, metricUnit, usValue, usUnit)
                        );
                        ingredients.add(ingredient);
                    }
                } catch (JSONException e) {
                    Log.e("parseIngredients", "Error parsing ingredients", e);
                }
                return ingredients;
            }

            private ArrayList<Nutrition> parseNutritions(JSONObject jsonObject) {
                ArrayList<Nutrition> nutritions = new ArrayList<>();
                // Check if "nutritions" key exists
                if (!jsonObject.has("nutrition")) {
                    Log.e("parseNutritions", "No 'nutritions' key found in JSON");
                    // Handle the missing key case, e.g., by returning an empty list
                    return nutritions;
                }
                try {
                    JSONObject nutritionJSONObject = jsonObject.getJSONObject("nutrition");
                    JSONArray nutritionJsonArray = nutritionJSONObject.getJSONArray("nutrients");
                    for (int i = 0; i < nutritionJsonArray.length(); i++) {
                        JSONObject nutritionObject = nutritionJsonArray.getJSONObject(i);
                        Nutrition nutrition = new Nutrition(
                                nutritionObject.getString("name"),
                                nutritionObject.getDouble("amount"),
                                nutritionObject.getString("unit")
                        );
                        nutritions.add(nutrition);
                    }
                } catch (JSONException e) {
                    Log.e("parseNutritions", "Error parsing nutritions", e);
                }
                return nutritions;
            }





            private List<String> convertJsonArrayToList(JSONArray jsonArray) throws JSONException {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add(jsonArray.getString(i));
                }
                return list;
            }



});

    }
    }