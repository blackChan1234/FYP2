package com.example.Savesystem.Restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

	public class Food implements Parcelable {

	private String id,_id;
	private String title;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Nutrition> nutritions;
	private String image;
	private String sourceUrl;
	private boolean vegetarian;
	private boolean vegan;
	private boolean glutenFree;
	private boolean dairyFree;
	private int preparationMinutes;
	private int cookingMinutes;
	private int aggregateLikes;
	private double healthScore;
	private String creditsText;
	private double pricePerServing;
	private int readyInMinutes;
	private int servings;
	private String summary;
	private List<String> cuisines;
	private List<String> dishTypes;
	private List<String> diets;

	// Constructor
	public Food(String _id,String id, String title, String image, String sourceUrl, boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, int preparationMinutes, int cookingMinutes, int aggregateLikes, double healthScore, String creditsText, double pricePerServing, int readyInMinutes, int servings, String summary, List<String> cuisines, List<String> dishTypes, List<String> diets,ArrayList<Ingredient> ingredients, ArrayList<Nutrition> nutritions) {
		this.id = id;
		this._id = _id;
		this.title = title;
		this.image = image;
		this.sourceUrl = sourceUrl;
		this.vegetarian = vegetarian;
		this.vegan = vegan;
		this.glutenFree = glutenFree;
		this.dairyFree = dairyFree;
		this.preparationMinutes = preparationMinutes;
		this.cookingMinutes = cookingMinutes;
		this.aggregateLikes = aggregateLikes;
		this.healthScore = healthScore;
		this.creditsText = creditsText;
		this.pricePerServing = pricePerServing;
		this.readyInMinutes = readyInMinutes;
		this.servings = servings;
		this.summary = summary;
		this.cuisines = cuisines;
		this.dishTypes = dishTypes;
		this.diets = diets;
	}

		protected Food(Parcel in) {
			id = in.readString();
			title = in.readString();
			image = in.readString();
			sourceUrl = in.readString();
			vegetarian = in.readByte() != 0;
			vegan = in.readByte() != 0;
			glutenFree = in.readByte() != 0;
			dairyFree = in.readByte() != 0;
			preparationMinutes = in.readInt();
			cookingMinutes = in.readInt();
			aggregateLikes = in.readInt();
			healthScore = in.readDouble();
			creditsText = in.readString();
			pricePerServing = in.readDouble();
			readyInMinutes = in.readInt();
			servings = in.readInt();
			summary = in.readString();
			cuisines = in.createStringArrayList();
			dishTypes = in.createStringArrayList();
			diets = in.createStringArrayList();
			ingredients = in.createTypedArrayList(Ingredient.CREATOR); // Ensure Ingredient implements Parcelable
			nutritions = in.createTypedArrayList(Nutrition.CREATOR); // Ensure Nutrition implements Parcelable
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(id);
			dest.writeString(title);
			dest.writeString(image);
			dest.writeString(sourceUrl);
			dest.writeByte((byte) (vegetarian ? 1 : 0));
			dest.writeByte((byte) (vegan ? 1 : 0));
			dest.writeByte((byte) (glutenFree ? 1 : 0));
			dest.writeByte((byte) (dairyFree ? 1 : 0));
			dest.writeInt(preparationMinutes);
			dest.writeInt(cookingMinutes);
			dest.writeInt(aggregateLikes);
			dest.writeDouble(healthScore);
			dest.writeString(creditsText);
			dest.writeDouble(pricePerServing);
			dest.writeInt(readyInMinutes);
			dest.writeInt(servings);
			dest.writeString(summary);
			dest.writeStringList(cuisines);
			dest.writeStringList(dishTypes);
			dest.writeStringList(diets);
			dest.writeTypedList(ingredients);
			dest.writeTypedList(nutritions);
		}

		@Override
		public int describeContents() {
			return 0;
		}

		public static final Creator<Food> CREATOR = new Creator<Food>() {
			@Override
			public Food createFromParcel(Parcel in) {
				return new Food(in);
			}

			@Override
			public Food[] newArray(int size) {
				return new Food[size];
			}
		};

	// Getters and potentially setters if you need to modify the properties later
	public String getId() { return id; }

		public String get_id() { return _id; }
	public String getTitle() { return title; }
	public String getImage() { return image; }
	public String getSourceUrl() { return sourceUrl; }
	public boolean isVegetarian() { return vegetarian; }
	public boolean isVegan() { return vegan; }
	public boolean isGlutenFree() { return glutenFree; }
	public boolean isDairyFree() { return dairyFree; }
	public int getPreparationMinutes() { return preparationMinutes; }
	public int getCookingMinutes() { return cookingMinutes; }
	public int getAggregateLikes() { return aggregateLikes; }
	public double getHealthScore() { return healthScore; }
	public String getCreditsText() { return creditsText; }
	public double getPricePerServing() { return pricePerServing; }
	public int getReadyInMinutes() { return readyInMinutes; }
	public int getServings() { return servings; }
	public String getSummary() { return summary; }
	public List<String> getCuisines() { return cuisines; }
	public List<String> getDishTypes() { return dishTypes; }
	public List<String> getDiets() { return diets; }
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

	public ArrayList<Nutrition> getNutritions() {
		return nutritions;
	}

	public void setNutritions(ArrayList<Nutrition> nutritions) {
		this.nutritions = nutritions;
	}

	public void addNutrition(Nutrition nutrition) {
		this.nutritions.add(nutrition);
	}
}

