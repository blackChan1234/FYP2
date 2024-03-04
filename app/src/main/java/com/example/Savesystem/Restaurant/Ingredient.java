package com.example.Savesystem.Restaurant;

import java.util.List;

public class Ingredient {
	private String name;
	private String image; // Assuming the image is represented by its URL as a String
   private Amount amount;

	// Constructor
	public Ingredient(String name, String image,Amount amount) {
		this.name = name;
		this.image = image;
		this.amount = amount;

	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}

