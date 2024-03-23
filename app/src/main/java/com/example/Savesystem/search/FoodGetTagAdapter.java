package com.example.Savesystem.search;

import com.example.Savesystem.Restaurant.*;

import java.util.ArrayList;

public class FoodGetTagAdapter {

    private Food food;
    private ArrayList<tag> tags;

	public FoodGetTagAdapter(Food f) {
        this.food= f;
        if( f.isVegan())
            tags.add(new tag("vegan"));
        if(f.isVegetarian())
            tags.add(new tag("Vegetarian"));
		if(f.isGlutenFree())
			tags.add(new tag("glutenFree"));
		if(f.isDairyFree())
			tags.add(new tag("DairyFree"));

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