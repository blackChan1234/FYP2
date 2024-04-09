package com.example.fyp;

import android.content.ContentProviderOperation;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey); // This is the layout that includes all the other layouts

        progressBar = findViewById(R.id.progressBar);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);

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
        step1Layout.setVisibility(View.GONE);
        step2Layout.setVisibility(View.VISIBLE);
        progressBar.setProgress(66);
    }

    public void goToStep3(View view) {
        step2Layout.setVisibility(View.GONE);
        step3Layout.setVisibility(View.VISIBLE);
        progressBar.setProgress(100);
    }

    public void submitForm(View view) {
        // Collect data from Step 1
        String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        String age = editTextAge.getText().toString();
        String weight = editTextWeight.getText().toString();
        String height = editTextHeight.getText().toString();

        // Collect data from Step 2 and Step 3
        String dietaryRestrictions = getDietaryRestrictions();
        String cuisinePreferences = getCuisinePreferences();

        // Then, make the HTTP POST request to your PHP server
        makePostRequest(gender, age, weight, height, dietaryRestrictions, cuisinePreferences);
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
        if (!(view instanceof Button)) return;
        Button button = (Button) view;
        Object tag = button.getTag();
        button.setSelected(!button.isSelected());
        if (tag != null && tag.toString().equals("selected")) {
            button.setTag(null); // Deselect
            // Optionally change button appearance to indicate it's not selected
        } else {
            button.setTag("selected"); // Select
            // Optionally change button appearance to indicate it's selected
        }
    }


    private void makePostRequest(String gender, String age, String weight, String height, String dietaryRestrictions, String cuisinePreferences) {
        new Thread(() -> {
            try {
                RequestBody formBody = new FormBody.Builder()
                        .add("gender", gender)
                        .add("age", age)
                        .add("weight", weight)
                        .add("height", height)
                        .add("dietaryRestrictions", dietaryRestrictions)
                        .add("cuisinePreferences", cuisinePreferences)
                        .build();

                Request request = new Request.Builder()
                        .url("http://yourserver.com/insert.php") // Your PHP server URL
                        .post(formBody)
                        .build();

                OkHttpClient httpClient = new OkHttpClient();
                try (Response response = httpClient.newCall(request).execute()) {
                    if (response.isSuccessful()) {
                        // Handle success
                    } else {
                        // Handle failure
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the error
            }
        }).start();
    }
}