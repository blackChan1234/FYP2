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
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profileImage = findViewById(R.id.profile_image);

        loadProfile();
    }

    private void loadProfile() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String email = prefs.getString("email", "No email defined");
        String password = prefs.getString("password", "No password defined");
        String name = prefs.getString("name", "Foodie Lover");  // Assuming you saved name as well
        int age = prefs.getInt("age", 0);
        String gender = prefs.getString("gender", "No gender defined");
        int weight = prefs.getInt("weight", 0);
        int height = prefs.getInt("height", 0);
        String dietaryRestrictions = prefs.getString("dietaryRestrictions", "No dietaryRestrictions defined");
        String cuisinePreferences = prefs.getString("cuisinePreferences", "No cuisinePreferences defined");
        profileEmail.setText(email);
        profileName.setText(name);
        Log.d("SurveyActivity", "Preparing to submit - Email: " + email + ", Password: " + password + ", Gender: " + gender + ", Age: " + age + ", Weight: " + weight + ", Height: " + height + ", Dietary Restrictions: " + dietaryRestrictions + ", Cuisine Preferences: " + cuisinePreferences);
        // You can also set the profile image if you have a path saved
        // For example:
        // String imagePath = prefs.getString("profileImage", null);
        // if (imagePath != null) {
        //     profileImage.setImageURI(Uri.parse(imagePath));
        // }
    }
}
