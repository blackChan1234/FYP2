package com.example.Savesystem.Restaurant;

public class Amount {
    private double metricValue;
    private String metricUnit;
    private double usValue; // Changed from String to double to match constructor
    private String usUnit;

    // Constructor
    public Amount(double metricValue, String metricUnit, double usValue, String usUnit) {
        this.metricValue = metricValue;
        this.metricUnit = metricUnit;
        this.usValue = usValue;
        this.usUnit = usUnit;
    }

    // Getters
    public double getMetricValue() {
        return metricValue;
    }

    public String getMetricUnit() {
        return metricUnit;
    }

    public double getUsValue() {
        return usValue;
    }

    public String getUsUnit() {
        return usUnit;
    }

    // Setters
    public void setMetricValue(double metricValue) {
        this.metricValue = metricValue;
    }

    public void setMetricUnit(String metricUnit) {
        this.metricUnit = metricUnit;
    }

    public void setUsValue(double usValue) {
        this.usValue = usValue;
    }

    public void setUsUnit(String usUnit) {
        this.usUnit = usUnit;
    }
}
