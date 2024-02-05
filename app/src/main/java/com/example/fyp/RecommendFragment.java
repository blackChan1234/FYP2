package com.example.fyp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.fyp.foodrecommend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        Log.d("RecommendFragment", "showDialog() called");
        // Create a Dialog instance
        final Dialog dialog = new Dialog(getContext());
        // Set the custom layout
        dialog.setContentView(R.layout.dialog_food_recommendation);

        // Initialize dialog elements if you need to interact with them
        TextView mealNameTextView = dialog.findViewById(R.id.mealname);
        ImageView foodimageView = dialog.findViewById(R.id.food);
        Button btnAnother = dialog.findViewById(R.id.another);
        // Example button click listener
        Button okButton = dialog.findViewById(R.id.Button2);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), foodrecommend.class);
                    // Put the meal name as an extra in the Intent
                    intent.putExtra("MEAL_NAME", mealNameTextView.getText().toString());
                    startActivity(intent);
                    dialog.dismiss(); // Close the dialog
                }
            }
        });


        // Fetch JSON data and update UI elements
        fetchJsonAndPopulateUI(mealNameTextView,foodimageView, dialog);

        // Display the dialog
        dialog.show();
    }

    private void fetchJsonAndPopulateUI(final TextView mealNameTextView,final ImageView foodimageView, final Dialog dialog) {
        // Perform network request in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2/getData.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.connect();


                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                        StringBuilder builder = new StringBuilder();
                        String line;

                        while ((line = bufReader.readLine()) != null) {
                            builder.append(line).append("\n");
                        }

                        inputStream.close();
                        String jsonResult = builder.toString();

                        Log.d("RecommendFragment", "Response: " + jsonResult);

                        // Parse JSON and update UI
                        try {
                            JSONArray jsonArray = new JSONArray(jsonResult);
                            if (jsonArray.length() > 0) {
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                final String mealName = jsonObject.getString("Name");
                                final String imagePath = jsonObject.getString("ImagePath");
                                final String FoodID = jsonObject.getString("FoodID");
                                // Update UI on the main thread
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                                public void run() {
                                                    mealNameTextView.setText(mealName);
                                                    if (imagePath != null) {
                                                        String resourceName = imagePath.replace(".jpg", ""); // Assuming imagePath has the extension
                                                        int imageResId = getResources().getIdentifier(resourceName, "drawable", getActivity().getPackageName());

                                                        RequestOptions requestOptions = new RequestOptions()
                                                                .placeholder(R.drawable.loading) // Placeholder image while loading
                                                                .transform(new RoundedCorners(10)); // Adjust the radius as needed

                                                        if (imageResId != 0) { // Resource ID found
                                                            Glide.with(requireContext())
                                                                    .load(imageResId)
                                                                    .apply(requestOptions)
                                                                    .into(foodimageView);
                                                        } else { // Fallback if the resource ID is not found
                                                            foodimageView.setImageResource(R.drawable.salmon); // You can replace 'salmon' with your default image resource
                                            }
                                        }
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("RecommendFragment", "IOException: " + e.getMessage());
                }
            }
        }).start();
    }
}
