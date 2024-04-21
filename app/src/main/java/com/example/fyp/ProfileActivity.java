package com.example.fyp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileName;
    private TextView profileEmail;

    private TextView profileAge;
    private ImageView profileImage;

    private TextView profileWeight;
    private TextView profileHeight;
    private TextView profileDietaryRestrictions;
    private TextView profileCuisinePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profileAge = findViewById(R.id.profile_age);
        profileWeight = findViewById(R.id.profile_weight);
        profileHeight = findViewById(R.id.profile_height);
        profileDietaryRestrictions = findViewById(R.id.profile_dietaryRestrictions);
        profileCuisinePreferences = findViewById(R.id.profile_cuisinePreferences);

        loadProfile();
    }

    private void loadProfile() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String email = prefs.getString("email", "No email defined");
        String password = prefs.getString("password", "No password defined");
        String name = prefs.getString("name", "Foodie Lover");  // Assuming you saved name as well
        String age = String.valueOf(prefs.getInt("age", 0));
        String weight = String.valueOf(prefs.getInt("weight", 0));
        String height = String.valueOf(prefs.getInt("height", 0));
        String gender = prefs.getString("gender", "No gender defined");
        String dietaryRestrictions = prefs.getString("dietaryRestrictions", "No dietaryRestrictions defined");
        String cuisinePreferences = prefs.getString("cuisinePreferences", "No cuisinePreferences defined");
        profileName.setText(name);
        profileAge.setText(age);
        profileWeight.setText(weight);
        profileHeight.setText(height);
        profileDietaryRestrictions.setText(dietaryRestrictions);
        profileCuisinePreferences.setText(cuisinePreferences);
    }
}
