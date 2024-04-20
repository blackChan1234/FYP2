package com.example.fyp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
public class filter extends AppCompatActivity {
    private LinearLayout dropdownLayout;
    private ImageButton filter_hide,area_hide,preference_hide,cuisine_hide;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findrestaurants);

        searchView = findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    Search(query);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange (String newText) {
                return true;
            }});
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
                hideFilter(filter_hide, dropdownLayout);
            }
        });

        area_hide = findViewById(R.id.area_hide);
        area_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.area);
                hideFilter(area_hide, dropdownLayout);
            }
        });

        preference_hide = findViewById(R.id.preference_hide);
        preference_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.preference);
                hideFilter(preference_hide, dropdownLayout);
            }
        });

        cuisine_hide = findViewById(R.id.cuisine_hide);
        cuisine_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout = findViewById(R.id.cuisine);
                hideFilter(cuisine_hide, dropdownLayout);
            }
        });
    }


    @SuppressLint("SuspiciousIndentation")
    public void Search(String query) throws UnsupportedEncodingException {

        String apiURL = "http://10.0.2.2/phpcode/fypTest/api_getFoodByDatailTag.php";

        ArrayList<CheckBox> Cusines = null;
        String postData="";
        for (int i = 0; i < Cusines.size(); i++) {
            CheckBox Cusine =Cusines.get(i);
            if( Cusine.isChecked())
            postData += "cusine=" + URLEncoder.encode((String) Cusine.getText(), "UTF-8");
        }
        new PostRequestFoodByDatailTag(postData).execute(apiURL);
        Log.d("Search",query);
    }
    public void hideFilter(ImageButton btn, LinearLayout dropdownLayout) {
        if (dropdownLayout.getVisibility() == View.VISIBLE) {
            dropdownLayout.setVisibility(View.GONE);
            btn.setImageResource(R.drawable.baseline_arrow_drop_down_24);
        } else {
            dropdownLayout.setVisibility(View.VISIBLE);
            btn.setImageResource(R.drawable.baseline_arrow_drop_up_24);
        }
    }


}

