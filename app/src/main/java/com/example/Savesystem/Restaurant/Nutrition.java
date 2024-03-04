package com.example.Savesystem.Restaurant;

public class Nutrition {
	private String name;
	private double amount;
	private String unit;

	public Nutrition(String name, double amount, String unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}

	// Getters
	public String getName() { return name; }
	public double getAmount() { return amount; }
	public String getUnit() { return unit; }
}
