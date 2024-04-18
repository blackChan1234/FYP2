package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfo extends AppCompatActivity {
    private LinearLayout layout;
    private TextView edit;
    private ImageButton btn_arrow_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information);

        btn_arrow_back = findViewById(R.id.arrow_back);
        btn_arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfo.this, MainActivity.class);
                startActivity(intent);
            }
        });

        layout = findViewById(R.id.userinfo);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof EditText || child instanceof CheckBox) {
                child.setEnabled(false);
            }
        }

        edit = findViewById(R.id.edit_user_info);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View child = layout.getChildAt(i);
                    if (child instanceof EditText || child instanceof CheckBox) {
                        child.setEnabled(true);
                    }
                }
            }
        });
    }
}
