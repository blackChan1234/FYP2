package com.example.fyp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.Savesystem.Restaurant.Ingredient;
import com.example.fyp.R;

import java.util.List;
import com.example.Savesystem.Restaurant.NearbyRestaurant;
public class NearbyRestaurantAdapter extends RecyclerView.Adapter<NearbyRestaurantAdapter.NearbyRestaurantViewHolder> {

    private List<NearbyRestaurant> restaurantList;
    private Fragment fragment;

    public NearbyRestaurantAdapter(List<NearbyRestaurant> restaurantList, Fragment f) {
        this.restaurantList = restaurantList;
        this.fragment = f;
    }

    @NonNull
    @Override
    public NearbyRestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_restaurant, parent, false);
        return new NearbyRestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyRestaurantViewHolder holder, int position) {
        NearbyRestaurant restaurant = restaurantList.get(position);
        Log.d("GlideImageUrl", "Loading image for: " + restaurant.getName() + " | URL: drawable/default_restaurant.png" );
        Log.d("Address", "restaurant " + position + ": " + restaurant.getName());

        Log.d("NearbyRestaurantAdapter", "Binding restaurant at position " + position + ": " + restaurant.getName());


        holder.textViewName.setText(restaurant.getName());
        holder.textViewAddress.setText(restaurant.getLoc());

        Glide.with(fragment)
                //app/src/main/res/drawable/defult_restaurant.png
                .load("drawable/default_restaurant.png") // Now correctly loading from a URL
                .apply(new RequestOptions().placeholder(R.drawable.loading)) // Handling loading and error placeholders
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    static class NearbyRestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName,textViewAddress;

        NearbyRestaurantViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAddress= itemView.findViewById(R.id.textViewAddress);
        }
    }
}
