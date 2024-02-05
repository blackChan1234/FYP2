package com.example.fyp;

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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;
    private Context context;
    // Constructor
    public IngredientsAdapter(List<Ingredient> ingredients,Context context) {
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
        holder.bind(ingredient,context);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    // ViewHolder class
    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        // UI elements to display the ingredient
        private ImageView imageViewIngredient;
        private TextView textViewIngredientName;

        IngredientViewHolder(View itemView) {
            super(itemView);
            imageViewIngredient = itemView.findViewById(R.id.imageViewIngredient);
            textViewIngredientName = itemView.findViewById(R.id.textViewIngredientName);
        }

        void bind(Ingredient ingredient, Context context) {
            textViewIngredientName.setText(ingredient.getName());
            // Use Glide to load the image
            Glide.with(context)
                    .load(ingredient.getImageResourceId())
                    .apply(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.salmon)) // Placeholder and Error image
                    .into(imageViewIngredient);
        }
    }
}
