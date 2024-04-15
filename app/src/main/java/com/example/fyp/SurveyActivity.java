package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SurveyActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private LinearLayout step1Layout, step2Layout, step3Layout;
    private RadioGroup radioGroupGender;
    private EditText editTextAge, editTextWeight, editTextHeight;
    // Assume you have Button variables for all your buttons
    private String dietaryRestrictions = "";
    private String cuisinePreferences = "";
    private String gender, age, weight, height,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey); // This is the layout that includes all the other layouts

        progressBar = findViewById(R.id.progressBar);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        Intent intent = getIntent();
         email = intent.getStringExtra("email");
         password = intent.getStringExtra("password");
        // Initialize step layouts
        step1Layout = findViewById(R.id.layoutStep1);
        step2Layout = findViewById(R.id.layoutStep2);
        step3Layout = findViewById(R.id.layoutStep3);

        // Initialize step 1 layout
        step1Layout.setVisibility(View.VISIBLE);
        step2Layout.setVisibility(View.GONE);
        step3Layout.setVisibility(View.GONE);
        showStep(1);

    }

    private void showStep(int step) {
        step1Layout.setVisibility(step == 1 ? View.VISIBLE : View.GONE);
        step2Layout.setVisibility(step == 2 ? View.VISIBLE : View.GONE);
        step3Layout.setVisibility(step == 3 ? View.VISIBLE : View.GONE);
        progressBar.setProgress(step * 33);
    }

    public void goToStep2(View view) {
        // Store the values from step 1
        gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        age = editTextAge.getText().toString();
        weight = editTextWeight.getText().toString();
        height = editTextHeight.getText().toString();

        // Show the next step
        step1Layout.setVisibility(View.GONE);
        step2Layout.setVisibility(View.VISIBLE);
        progressBar.setProgress(66);
    }

    public void goToStep3(View view) {
        // Store the values from step 2
        dietaryRestrictions = getDietaryRestrictions();

        // Show the next step
        step2Layout.setVisibility(View.GONE);
        step3Layout.setVisibility(View.VISIBLE);
        progressBar.setProgress(100);
    }


    public void submitForm(View view) {
        // Collect data from Step 1
        cuisinePreferences = getCuisinePreferences();

        // Then, make the HTTP POST request to your PHP server
        makePostRequest();
    }




    private String getDietaryRestrictions() {
        StringBuilder restrictions = new StringBuilder();
        ViewGroup layout = (ViewGroup) findViewById(R.id.layoutStep2);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof Button && child.isSelected()) {
                Button button = (Button) child;
                if (restrictions.length() > 0) restrictions.append(",");
                restrictions.append(button.getText().toString());
            }
        }
        return restrictions.toString();
    }



    private String getCuisinePreferences() {
        StringBuilder preferences = new StringBuilder();
        ViewGroup layout = (ViewGroup) findViewById(R.id.layoutStep3);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                Object tag = button.getTag();
                if (tag != null && tag.toString().equals("selected")) {
                    if (preferences.length() > 0) preferences.append(",");
                    preferences.append(button.getText().toString());
                }
            }
        }
        return preferences.toString();
    }

    public void onDietaryRestrictionButtonClick(View view) {
        Button button = (Button) view;
        // Toggle the selected state of the button
        button.setSelected(!button.isSelected());

        // Update dietaryRestrictions based on the button's selected state
        updateDietaryRestrictions(button.getText().toString(), button.isSelected());
    }
    public void onCuisinePreferenceButtonClick(View view) {
        Button button = (Button) view;
        // Toggle the selected state of the button
        button.setSelected(!button.isSelected());

        // Update cuisinePreferences based on the button's selected state
        updateCuisinePreferences(button.getText().toString(), button.isSelected());
    }
    private void updateDietaryRestrictions(String restriction, boolean isSelected) {
        if (isSelected) {
            if (!dietaryRestrictions.isEmpty()) {
                dietaryRestrictions += ",";
            }
            dietaryRestrictions += restriction;
        } else {
            // Remove the restriction if it's already in the string
            String[] parts = dietaryRestrictions.split(",");
            dietaryRestrictions = "";
            for (String part : parts) {
                if (!part.equals(restriction)) {
                    if (!dietaryRestrictions.isEmpty()) {
                        dietaryRestrictions += ",";
                    }
                    dietaryRestrictions += part;
                }
            }
        }
    }
    private void updateCuisinePreferences(String cuisine, boolean isSelected) {
        if (isSelected) {
            if (!cuisinePreferences.isEmpty()) {
                cuisinePreferences += ",";
            }
            cuisinePreferences += cuisine;
        } else {
            // Remove the cuisine if it's already in the string
            String[] parts = cuisinePreferences.split(",");
            cuisinePreferences = "";
            for (String part : parts) {
                if (!part.equals(cuisine)) {
                    if (!cuisinePreferences.isEmpty()) {
                        cuisinePreferences += ",";
                    }
                    cuisinePreferences += part;
                }
            }
        }
    }

    private void makePostRequest() {
        new Thread(() -> {
            try {
                RequestBody formBody = new FormBody.Builder()
                        .add("email", email)  // Pass email
                        .add("password", password)  // Pass password
                        .add("gender", gender)
                        .add("age", age)
                        .add("weight", weight)
                        .add("height", height)
                        .add("dietaryRestrictions", dietaryRestrictions)
                        .add("cuisinePreferences", cuisinePreferences)
                        .build();
                if (gender == null || age.isEmpty() || weight.isEmpty() || height.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields before submitting", Toast.LENGTH_LONG).show();
                    return;
                }

                Request request = new Request.Builder()
                        .url("http://10.0.2.2/FYP/insertuser.php")
                        .post(formBody)
                        .build();

                OkHttpClient httpClient = new OkHttpClient();
                try (Response response = httpClient.newCall(request).execute()) {
                    String responseBody = response.body().string();
                    if (response.isSuccessful()) {
                        runOnUiThread(() -> Toast.makeText(SurveyActivity.this, "Response: " + responseBody, Toast.LENGTH_LONG).show());
                    } else {
                        runOnUiThread(() -> Toast.makeText(SurveyActivity.this, "Failed to submit data: " + responseBody, Toast.LENGTH_LONG).show());
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(SurveyActivity.this, "Error connecting to server: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}