package com.example.fyp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        Button btnJumpToFoodRecommend = view.findViewById(R.id.btnJumpToFoodRecommend);
        btnJumpToFoodRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private void showDialog() {
        // Create a Dialog instance
        final Dialog dialog = new Dialog(getContext());
        // Set the custom layout
        dialog.setContentView(R.layout.dialog_food_recommendation);

        // Initialize dialog elements if you need to interact with them
        TextView textView = dialog.findViewById(R.id.textView3);
        ImageView foodImage = dialog.findViewById(R.id.food);
        Button anotherButton = dialog.findViewById(R.id.Button1);
        Button okButton = dialog.findViewById(R.id.Button2);

        // Example button click listener
        anotherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Another" button click
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensure the activity context is available
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), foodrecommend.class);
                    startActivity(intent);
                    dialog.dismiss(); // Close the dialog
                }
            }
        });


        // Display the dialog
        dialog.show();
    }
}

