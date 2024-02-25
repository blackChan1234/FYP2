package com.example.Savesystem.search;

import com.example.Savesystem.Restaurant.*;

import java.util.ArrayList;

public class TaggedRestaurant extends Restaurant {

	private ArrayList<tag> tags;

	public ArrayList<tag> getTags() {
		return this.tags;
	}

	/**
	 * 
	 * @param tags
	 */
	public void setTags(ArrayList<tag> tags) {
		this.tags = tags;
	}

}