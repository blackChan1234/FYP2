package com.example.fyp;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class filterResult extends AppCompatActivity {
    private RecyclerView recyclerView;
    // private RestaurantAdapter adapter;
    private List<String> restaurantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        String result = getIntent().getStringExtra("result");
        recyclerView = findViewById(R.id.recyclerView_searchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //  adapter = new RestaurantAdapter(restaurantList);
        // recyclerView.setAdapter(adapter);
        Log.d("avc", "onCreate: "+result);
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String result) {
                performSearch(result);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Optional: Handle live search suggestions
                return false;
            }
        });
    }

    private void performSearch(String result) {
        // Dummy data for example
        restaurantList.clear();
        restaurantList.add("Restaurant 1 - Query: " + result);
        restaurantList.add("Restaurant 2 - Query: " + result);
        restaurantList.add("Restaurant 3 - Query: " + result);
        // adapter.notifyDataSetChanged();
    }
}