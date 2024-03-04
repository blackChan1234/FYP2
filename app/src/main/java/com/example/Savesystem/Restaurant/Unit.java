package com.example.Savesystem.Restaurant;


public class Unit {
    private double value;
    private String unit;

    // Constructor
    public Unit(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    // Getters and setters
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
