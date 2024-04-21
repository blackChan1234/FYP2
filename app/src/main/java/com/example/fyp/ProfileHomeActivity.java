package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Button btnReview = findViewById(R.id.btnReview);
        Button btnSetting1 = findViewById(R.id.btnSetting1);
        Button btnPerson = findViewById(R.id.btnPerson);
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileHomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        btnPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileHomeActivity.this, PersonActivity.class);
                startActivity(intent);
            }
        });
        btnSetting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileHomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileHomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
