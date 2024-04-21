package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fyp.adapters.ImageSliderAdapter;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnSetting = view.findViewById(R.id.btnSetting);
        Button btnReview = view.findViewById(R.id.btnReview);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use Fragment's context to start the activity
                Intent intent = new Intent(requireActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use Fragment's context to start the activity
                Intent intent = new Intent(requireActivity(), ProfileHomeActivity.class);
                startActivity(intent);
            }
        });

        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager);
        int[] images = {R.drawable.default_restaurant, R.drawable.ba6d6a3282c393ca02f9bd49b880d054, R.drawable.food}; // 图片资源 ID
        ImageSliderAdapter adapter = new ImageSliderAdapter(images);
        viewPager2.setAdapter(adapter);
    }

}
