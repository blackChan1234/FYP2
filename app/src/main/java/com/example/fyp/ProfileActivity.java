package com.example.fyp;

import android.content.SharedPreferences;
import android.os.Bundle;
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
        String name = prefs.getString("name", "Foodie Lover");  // Assuming you saved name as well

        profileEmail.setText(email);
        profileName.setText(name);

        // You can also set the profile image if you have a path saved
        // For example:
        // String imagePath = prefs.getString("profileImage", null);
        // if (imagePath != null) {
        //     profileImage.setImageURI(Uri.parse(imagePath));
        // }
    }
}
