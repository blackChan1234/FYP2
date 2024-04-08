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
	private String imageUrl;
	private Amount amount;

	// Constructor
	public Ingredient(String name, String imageName,Amount amount) {
		this.name = name;
		this.imageUrl = "https://spoonacular.com/cdn/ingredients_100x100/" + imageName;
	}

	protected Ingredient(Parcel in) {
		name = in.readString();
		imageUrl = in.readString();

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



    @Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(name);
		parcel.writeString(imageUrl);
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageName(String imageName) {
		this.imageUrl = "https://spoonaulous.com/cdn/ingredients_100x100/" + imageName;
	}
}
