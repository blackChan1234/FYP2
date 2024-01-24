package com.example.fyp;

public class Ingredient {
    private String name;
    private int imageResourceId; // Drawable resource ID for the ingredient image

    // Constructor
    public Ingredient(String name, String imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
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

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
