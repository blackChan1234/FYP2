package com.example.fyp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class filter extends AppCompatActivity {
    private Context mContext;
    private LinearLayout dropdownLayout;
    private ImageButton filter_hide,area_hide,preference_hide,cuisine_hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findrestaurants);
        mContext = filter.this;

        ImageButton btn_arrow_back = findViewById(R.id.arrow_back);
        btn_arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(filter.this, MainActivity.class);
                startActivity(intent);
            }
        });

        filter_hide = findViewById(R.id.filter_hide);
        filter_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.filter);
                if (dropdownLayout.getVisibility() == View.VISIBLE) {
                    dropdownLayout.setVisibility(View.GONE);
                    filter_hide.setImageResource(R.drawable.baseline_arrow_drop_down_24);
                } else {
                    dropdownLayout.setVisibility(View.VISIBLE);
                    filter_hide.setImageResource(R.drawable.baseline_arrow_drop_up_24);
                }
            }
        });

        area_hide = findViewById(R.id.area_hide);
        area_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.area);
                if (dropdownLayout.getVisibility() == View.VISIBLE) {
                    dropdownLayout.setVisibility(View.GONE);
                    area_hide.setImageResource(R.drawable.baseline_arrow_drop_down_24);
                } else {
                    dropdownLayout.setVisibility(View.VISIBLE);
                    area_hide.setImageResource(R.drawable.baseline_arrow_drop_up_24);
                }
            }
        });

        preference_hide = findViewById(R.id.preference_hide);
        preference_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.preference);
                if (dropdownLayout.getVisibility() == View.VISIBLE) {
                    dropdownLayout.setVisibility(View.GONE);
                    preference_hide.setImageResource(R.drawable.baseline_arrow_drop_down_24);
                } else {
                    dropdownLayout.setVisibility(View.VISIBLE);
                    preference_hide.setImageResource(R.drawable.baseline_arrow_drop_up_24);
                }
            }
        });

        cuisine_hide = findViewById(R.id.area_hide);
        cuisine_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.cuisine);
                if (dropdownLayout.getVisibility() == View.VISIBLE) {
                    dropdownLayout.setVisibility(View.GONE);
                    cuisine_hide.setImageResource(R.drawable.baseline_arrow_drop_down_24);
                } else {
                    dropdownLayout.setVisibility(View.VISIBLE);
                    cuisine_hide.setImageResource(R.drawable.baseline_arrow_drop_up_24);
                }
            }
        });
    }
}

