package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class foodrecommend extends AppCompatActivity {
    private RecyclerView nutritionFactsRecyclerView, ingredientsRecyclerView;
    private ArrayList<NutritionFact> nutritionFactsList = new ArrayList<>();
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private TextView Name,mealCategory;
    private ImageView mealImage;
    private NutritionFactsAdapter nutritionFactsAdapter;
    private IngredientsAdapter ingredientsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodrecommend);
        String mealName = getIntent().getStringExtra("MEAL_NAME");

        // Now, you can use mealName to make a request to your PHP script
        fetchMealData(mealName);
        Log.d("RecommendFragment", "Response: " + mealName);
       Name = findViewById(R.id.mealName);
        mealCategory = findViewById(R.id.mealCategory);
        mealImage = findViewById(R.id.mealImage);
        // Initialize  lists with data
        nutritionFactsRecyclerView = findViewById(R.id.nutritionFactsRecyclerView);
        ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);

        // Set up the adapters for both RecyclerViews
        nutritionFactsAdapter = new NutritionFactsAdapter(nutritionFactsList);
        ingredientsAdapter = new IngredientsAdapter(ingredientsList, this);

        nutritionFactsRecyclerView.setAdapter(nutritionFactsAdapter);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        // Set the layout manager to horizontal for both RecyclerViews
        LinearLayoutManager horizontalLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        nutritionFactsRecyclerView.setLayoutManager(horizontalLayoutManager1);
        ingredientsRecyclerView.setLayoutManager(horizontalLayoutManager2);
    }
    private void fetchMealData(String mealName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://10.0.2.2/getDetailData.php?mealName=" + URLEncoder.encode(mealName, "UTF-8"));
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        // Parse the JSON response
                        JSONObject jsonResponse = new JSONObject(response.toString());
                        JSONObject mealDetails = jsonResponse.getJSONObject("mealDetails");
                        JSONArray nutritionFacts = jsonResponse.getJSONArray("nutritionFacts");
                        JSONArray ingredients = jsonResponse.getJSONArray("ingredients");
                        final String imagePath = mealDetails.getString("ImagePath");
                        // Update UI (Remember to do this on the main thread)
                        // Inside fetchMealData, after parsing JSON:
                        Log.d("RecommendFragment", "Response: " + nutritionFacts);
                        Log.d("RecommendFragment", "Response: " + mealDetails);
                        Log.d("RecommendFragment", "Response: " + ingredients);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    // Set meal name and category
                                    Name.setText(mealDetails.getString("Name"));
                                    mealCategory.setText(mealDetails.getString("Type"));

                                    // Set meal image
                                    String resourceName = mealDetails.getString("ImagePath").replace(".jpg", ""); // Assuming your images are in drawable
                                    int imageResId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                                    if (imageResId != 0) {
                                        mealImage.setImageResource(imageResId);
                                    } else {
                                        // Set a default image or handle error
                                        mealImage.setImageResource(R.drawable.default_image); // Make sure you have a default_image in drawable
                                    }

                                    // Clear existing data
                                    nutritionFactsList.clear();
                                    ingredientsList.clear();

                                    // Parse and add nutrition facts
                                    for (int i = 0; i < nutritionFacts.length(); i++) {
                                        JSONObject fact = nutritionFacts.getJSONObject(i);
                                        nutritionFactsList.add(new NutritionFact(
                                                fact.getString("Name"),
                                                fact.getString("Amount"),
                                                fact.optString("n_ImagePath", "default_image"), // Use a default image name if not provided
                                                foodrecommend.this));
                                    }

                                    // Parse and add ingredients
                                    for (int i = 0; i < ingredients.length(); i++) {
                                        JSONObject ingredient = ingredients.getJSONObject(i);
                                        ingredientsList.add(new Ingredient(
                                                ingredient.getString("Name"),
                                                ingredient.optString("i_ImagePath", "default_image"),
                                                foodrecommend.this));
                                    }
                                    nutritionFactsAdapter.notifyDataSetChanged();
                                    ingredientsAdapter.notifyDataSetChanged();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}