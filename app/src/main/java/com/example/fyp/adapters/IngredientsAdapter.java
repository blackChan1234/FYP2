package com.example.fyp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fyp.R;
import com.example.Savesystem.Restaurant.Ingredient; // Make sure to import your Ingredient class

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;
    private Context context;

    public IngredientsAdapter(List<Ingredient> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        Log.d("IngredientsAdapter", "Binding ingredient at position " + position + ": " + ingredient.getName());
        holder.textViewIngredientName.setText(ingredient.getName());
        Glide.with(context)
                .load(ingredient.getImage()) // Assuming getImage() returns a valid URL
                .apply(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.error)) // Assuming you have error and loading drawables
                .into(holder.imageViewIngredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewIngredient;
        TextView textViewIngredientName;

        IngredientViewHolder(View itemView) {
            super(itemView);
            imageViewIngredient = itemView.findViewById(R.id.imageViewIngredient);
            textViewIngredientName = itemView.findViewById(R.id.textViewIngredientName);
        }
    }
}
