package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fyp.adapters.ImageSliderAdapter;

public class HomeFragment extends Fragment {
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());
    private Runnable sliderRunnable;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager2 = view.findViewById(R.id.viewPager);
        int[] images = {R.drawable.default_restaurant, R.drawable.restaurant2, R.drawable.restaurant3};
        ImageSliderAdapter adapter = new ImageSliderAdapter(images);
        viewPager2.setAdapter(adapter);

        setupAutoSlider();

        Button btnSetting = view.findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), SettingsActivity.class);
            startActivity(intent);
        });

        Button btnReview = view.findViewById(R.id.btnReview);
        btnReview.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), ProfileHomeActivity.class);
            startActivity(intent);
        });
    }

    private void setupAutoSlider() {
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager2.getAdapter() != null) {
                    int currentItem = viewPager2.getCurrentItem();
                    int itemCount = viewPager2.getAdapter().getItemCount();
                    int nextItem = (currentItem + 1) % itemCount;
                    viewPager2.setCurrentItem(nextItem, true);
                }
                sliderHandler.postDelayed(this, 3000); // Slide duration 3 seconds
            }
        };
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable); // Stop the slider when the Fragment is not visible
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000); // Resume the slider when the Fragment comes back
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewPager2.setAdapter(null); // Clean up the adapter
        sliderHandler.removeCallbacks(sliderRunnable); // Ensure we clean up the handler when the View is destroyed
    }
}
