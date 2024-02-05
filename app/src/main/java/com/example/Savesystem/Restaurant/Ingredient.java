package com.example.Savesystem.Restaurant;

public class Ingredient {

	private String name;
	private image img;
	public image getImg() {
		return img;
	}
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param name
	 */
	public Ingredient(String name) {
		this.name = name;
		this.img = new image();
	}
	public Ingredient(String name, image img) {
		this.name = name;
		this.img = img;
	}
	public Ingredient(String name, String imgPath) {
		this.name = name;
		this.img = new image(imgPath);
	}

}