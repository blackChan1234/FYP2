package com.example.fyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton,registerButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);
        if (isUserLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return;
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void logoutUser() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("isLoggedIn");
        editor.remove("email");
        editor.apply();

        // Redirect to Login Activity or wherever appropriate
    }

    private boolean isUserLoggedIn() {
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        return prefs.getBoolean("isLoggedIn", false);
    }
    private void saveLoginStatus(boolean isLoggedIn, String email) {
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.putString("email", email);  // Optionally store user email or any other identifier
        editor.apply();
        fetchUserData(email);
    }
    private void fetchUserData(String email) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2/database/phpServer/fetch_user_data.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                String postData = "email=" + URLEncoder.encode(email, "UTF-8");
                OutputStream os = conn.getOutputStream();
                os.write(postData.getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                parseUserDataAndSave(response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void parseUserDataAndSave(String jsonData) {
        runOnUiThread(() -> {
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("id", jsonObject.optString("id", "N/A"));
                // Assume your JSON object has keys like 'name', 'email', 'age', etc.
                editor.putString("name", jsonObject.optString("name", "N/A"));
                editor.putString("email", jsonObject.optString("email", "N/A"));
                editor.putInt("age", jsonObject.optInt("age", 0)); // Assuming age is an integer
                editor.putString("gender", jsonObject.optString("gender", "N/A"));
                editor.putInt("weight", jsonObject.optInt("weight", 0));
                editor.putInt("height", jsonObject.optInt("height", 0));
                editor.putString("dietaryRestrictions", jsonObject.optString("dietaryRestrictions", "N/A"));
                editor.putString("cuisinePreferences", jsonObject.optString("cuisinePreferences", "N/A"));

                // Add other user details similarly
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Close login activity upon success

            } catch (JSONException e) {
                Toast.makeText(LoginActivity.this, "Error parsing user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logoutUser();
    }

    private void performLogin() {
        new Thread(() -> {
            try {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                URL url = new URL("http://10.0.2.2/database/phpServer/api_login.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                String postData = "email=" + URLEncoder.encode(username, "UTF-8") +
                        "&password=" + URLEncoder.encode(password, "UTF-8");

                OutputStream os = conn.getOutputStream();
                os.write(postData.getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                final String responseStr = response.toString();
                runOnUiThread(() -> {
                    if (responseStr.equalsIgnoreCase("isLoginSuccessful")) {
                        saveLoginStatus(true, username);
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();  // Close login activity upon success
                    } else {
                        Toast.makeText(LoginActivity.this, responseStr, Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



}

