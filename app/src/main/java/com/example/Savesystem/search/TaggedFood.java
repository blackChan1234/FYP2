package com.example.Savesystem.search;

import com.example.Savesystem.Restaurant.*;

import java.util.ArrayList;

public class TaggedFood extends Food {

	private ArrayList<tag> tags;

	public TaggedFood(String name) {
		super(name);
	}

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