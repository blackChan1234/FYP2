package com.example.fyp;

// NutritionFact class to hold the data for each nutrition fact
public class NutritionFact {
    private String name; // The name of the nutrient (e.g., "Calories")
    private String value; // The value of the nutrient (e.g., "484 kcal")

    // Constructor
    public NutritionFact(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
