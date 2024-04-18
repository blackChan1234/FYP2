package com.example.fyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileHomeActivity extends AppCompatActivity {

    private TextView profileName;
    private TextView profileEmail;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile); // 替換成你的佈局文件名稱

        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        profileImage = findViewById(R.id.profile_image);

        Button btnSetting = findViewById(R.id.btnReview);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileHomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
