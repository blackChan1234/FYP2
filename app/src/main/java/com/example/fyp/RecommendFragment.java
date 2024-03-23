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
import android.widget.Toast;

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
                    // Assuming 'currentMeal' is the meal you want to pass
                    Food currentMeal = meals.get(currentMealIndex % meals.size());
                    Intent intent = new Intent(getActivity(), foodrecommend.class);
                    intent.putExtra("CURRENT_MEAL", currentMeal);
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
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();
                    // Parse the JSON data and update the UI on the main thread
                    try {
                        JSONArray jsonArray = new JSONArray(responseData);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Food meal = parseFoodFromJsonObject(jsonObject);
                            meals.add(meal);
                        }
                        // Ensure UI updates are run on the main thread
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Update the UI with the first meal as an example
                                    displayMeal(mealNameTextView, foodimageView);
                                    dialog.show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
            private ArrayList<Ingredient> parseIngredients(JSONObject jsonObject) {
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                try {
                    JSONArray ingredientsJsonArray = jsonObject.getJSONArray("ingredients");
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
    private Food parseFoodFromJsonObject(JSONObject jsonObject) throws JSONException {
        String id = jsonObject.getString("id");
        String _id = jsonObject.getString("_id");
        String title = jsonObject.getString("title");
        String image = jsonObject.getString("image");
        String sourceUrl = jsonObject.getString("sourceUrl");
        boolean vegetarian = jsonObject.getBoolean("vegetarian");
        boolean vegan = jsonObject.getBoolean("vegan");
        boolean glutenFree = jsonObject.getBoolean("glutenFree");
        boolean dairyFree = jsonObject.getBoolean("dairyFree");
        int preparationMinutes = jsonObject.getInt("preparationMinutes");
        int cookingMinutes = jsonObject.getInt("cookingMinutes");
        int aggregateLikes = jsonObject.getInt("aggregateLikes");
        double healthScore = jsonObject.getDouble("healthScore");
        String creditsText = jsonObject.getString("creditsText");
        double pricePerServing = jsonObject.getDouble("pricePerServing");
        int readyInMinutes = jsonObject.getInt("readyInMinutes");
        int servings = jsonObject.getInt("servings");
        String summary = jsonObject.getString("summary");
        List<String> cuisines = convertJsonArrayToList(jsonObject.getJSONArray("cuisines"));
        List<String> dishTypes = convertJsonArrayToList(jsonObject.getJSONArray("dishTypes"));
        List<String> diets = convertJsonArrayToList(jsonObject.getJSONArray("diets"));
        ArrayList<Ingredient> ingredients = parseIngredients(jsonObject.getJSONObject("ingredients"));
        ArrayList<Nutrition> nutritions = parseNutritions(jsonObject.getJSONObject("nutrition"));

        return new Food(id,_id, title, image, sourceUrl, vegetarian, vegan, glutenFree, dairyFree,
                preparationMinutes, cookingMinutes, aggregateLikes, healthScore, creditsText,
                pricePerServing, readyInMinutes, servings, summary, cuisines, dishTypes, diets, ingredients, nutritions);
    }


    private ArrayList<Nutrition> parseNutritions(JSONObject jsonObject) {
                ArrayList<Nutrition> nutritions = new ArrayList<>();
                try {

                    if (jsonObject.has("nutritions")) {
                        JSONArray nutritionJsonArray = jsonObject.getJSONArray("nutritions");
                        for (int i = 0; i < nutritionJsonArray.length(); i++) {
                            JSONObject nutritionObject = nutritionJsonArray.getJSONObject(i);
                        Nutrition nutrition = new Nutrition(
                                nutritionObject.getString("name"),
                                nutritionObject.getDouble("amount"),
                                nutritionObject.getString("unit")
                        );
                        nutritions.add(nutrition);
                    }
                }} catch (JSONException e) {
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



}
