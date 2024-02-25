package com.example.Savesystem.search;

import java.util.ArrayList;

public class searchSetting {

	private String loc;
	private ArrayList<tag> tags;

	public String getLoc() {
		return this.loc;
	}

	/**
	 * 
	 * @param loc
	 */
	public void setLoc(String loc) {
		this.loc = loc;
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