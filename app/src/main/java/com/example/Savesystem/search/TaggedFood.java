package search;

import Restaurant.*;

public class TaggedFood extends Food {

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