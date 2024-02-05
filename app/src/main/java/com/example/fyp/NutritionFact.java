package com.example.fyp;

public class NutritionFact {
    private String name; // The name of the nutrient (e.g., "Calories")
    private String value; // The value of the nutrient (e.g., "484 kcal")
    private int imageResourceId; // Drawable resource ID for the nutrition fact image

    // Adjusted Constructor
    public NutritionFact(String name, String value, String imageName, android.content.Context context) {
        this.name = name;
        this.value = value;
        // Convert drawable name to resource ID
        this.imageResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    // Getters and setters
    public String getName() { return name; }
    public String getValue() { return value; }
    public int getImageResourceId() { return imageResourceId; }

    // Setters (if needed)
    public void setName(String name) { this.name = name; }
    public void setValue(String value) { this.value = value; }
    public void setImageResourceId(int imageResourceId) { this.imageResourceId = imageResourceId; }
}
