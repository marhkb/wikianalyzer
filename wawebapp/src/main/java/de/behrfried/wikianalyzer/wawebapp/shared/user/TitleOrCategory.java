package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;

/**
 * @author marcus
 */
public class TitleOrCategory implements Serializable {

	private String name;

	private boolean isCategory;

	public TitleOrCategory() {
	}

	public TitleOrCategory(String name, boolean category) {
		this.name = name;
		isCategory = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCategory() {
		return isCategory;
	}

	public void setCategory(boolean category) {
		isCategory = category;
	}
}
