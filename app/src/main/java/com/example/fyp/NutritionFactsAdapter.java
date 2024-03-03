package com.example.fyp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NutritionFactsAdapter extends RecyclerView.Adapter<NutritionFactsAdapter.NutritionFactViewHolder> {

    private List<NutritionFact> nutritionFacts;

    // Constructor
    public NutritionFactsAdapter(List<NutritionFact> nutritionFacts) {
        this.nutritionFacts = nutritionFacts;
    }

    @NonNull
    @Override
    public NutritionFactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nutrition_fact, parent, false);
        return new NutritionFactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionFactViewHolder holder, int position) {
        NutritionFact nutritionFact = nutritionFacts.get(position);
        holder.bind(nutritionFact);
    }

    @Override
    public int getItemCount() {
        return nutritionFacts.size();
    }

    // ViewHolder class
    static class NutritionFactViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNutritionName;
        private TextView textViewNutritionValue;

        NutritionFactViewHolder(View itemView) {
            super(itemView);
            textViewNutritionName = itemView.findViewById(R.id.textViewNutritionName);
            textViewNutritionValue = itemView.findViewById(R.id.textViewNutritionValue);
        }

        void bind(NutritionFact nutritionFact) {
            textViewNutritionName.setText(nutritionFact.getName());
            textViewNutritionValue.setText(nutritionFact.getValue());
        }
    }

}
