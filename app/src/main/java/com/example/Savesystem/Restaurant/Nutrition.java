package com.example.Savesystem.Restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Nutrition implements Parcelable {
	private String name;
	private double amount;
	private String unit;

	// Constructor
	public Nutrition(String name, double amount, String unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}

	protected Nutrition(Parcel in) {
		name = in.readString();
		amount = in.readDouble();
		unit = in.readString();
	}

	public static final Creator<Nutrition> CREATOR = new Creator<Nutrition>() {
		@Override
		public Nutrition createFromParcel(Parcel in) {
			return new Nutrition(in);
		}

		@Override
		public Nutrition[] newArray(int size) {
			return new Nutrition[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(name);
		parcel.writeDouble(amount);
		parcel.writeString(unit);
	}

	// Getters
	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}

	public String getUnit() {
		return unit;
	}
}
