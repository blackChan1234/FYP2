package com.example.fyp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;
import com.example.Savesystem.Restaurant.Nutrition; // Make sure to import your Nutrition class

import java.util.List;

public class NutritionFactsAdapter extends RecyclerView.Adapter<NutritionFactsAdapter.NutritionFactViewHolder> {

    private List<Nutrition> nutritions;

    public NutritionFactsAdapter(List<Nutrition> nutritions) {
        this.nutritions = nutritions;
    }

    @NonNull
    @Override
    public NutritionFactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nutrition_fact, parent, false);

        return new NutritionFactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionFactViewHolder holder, int position) {
        Nutrition nutrition = nutritions.get(position);
        Log.d("NutritionFactsAdapter", "Binding nutrition fact at position " + position + ": " + nutrition.getName());
        holder.textViewNutritionName.setText(nutrition.getName());
        holder.textViewNutritionValue.setText(String.format("%s %s", nutrition.getAmount(), nutrition.getUnit()));
    }

    @Override
    public int getItemCount() {
        return nutritions.size();
    }

    static class NutritionFactViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNutritionName, textViewNutritionValue;

        NutritionFactViewHolder(View itemView) {
            super(itemView);
            textViewNutritionName = itemView.findViewById(R.id.textViewNutritionName);
            textViewNutritionValue = itemView.findViewById(R.id.textViewNutritionValue);
        }
    }
}
