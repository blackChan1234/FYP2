package com.example.fyp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;

    // Constructor
    public IngredientsAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
        holder.bind(ingredient);
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

        void bind(Ingredient ingredient) {
            imageViewIngredient.setImageResource(ingredient.getImageResourceId());
            textViewIngredientName.setText(ingredient.getName());
        }
    }
}
