package de.behrfried.wikianalyzer.wawebapp.shared.article;

import java.io.Serializable;
import java.util.Date;

public class ShortArticleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String user;
	
	private Date creationDate;

	public ShortArticleInfo() { }

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
