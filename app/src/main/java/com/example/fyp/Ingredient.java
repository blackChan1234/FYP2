package com.example.fyp;

public class Ingredient {
    private String name;
    private int imageResourceId; // Drawable resource ID for the ingredient image

    // Constructor
    public Ingredient(String name, String imageName, android.content.Context context) {
        this.name = name;
        // Convert drawable name to resource ID
        this.imageResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    // Setters (if needed)
    public void setName(String name) {
        this.name = name;
    }

}
