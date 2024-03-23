package com.example.Savesystem.Restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Ingredient implements Parcelable {
	private String name;
	private String image;


	// Constructor
	public Ingredient(String name, String image) {
		this.name = name;
		this.image = image;

	}

	protected Ingredient(Parcel in) {
		name = in.readString();
		image = in.readString();

	}

	public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
		@Override
		public Ingredient createFromParcel(Parcel in) {
			return new Ingredient(in);
		}

		@Override
		public Ingredient[] newArray(int size) {
			return new Ingredient[size];
		}
	};

    public Ingredient(String name, String image, Amount amount) {
    }


    @Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(name);
		parcel.writeString(image);
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





	// Parse ingredients from JSON array
	public static List<Ingredient> parseIngredients(JSONArray jsonArray) throws JSONException {
		List<Ingredient> ingredientsList = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject ingredientObject = jsonArray.getJSONObject(i);
			String name = ingredientObject.getString("name");
			String image = ingredientObject.getString("image");
			String unit = ingredientObject.getString("unit");

			Ingredient ingredient = new Ingredient(name, image);
			ingredientsList.add(ingredient);
		}
		return ingredientsList;
	}
}
