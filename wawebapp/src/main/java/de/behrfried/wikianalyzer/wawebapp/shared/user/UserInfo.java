package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserInfo implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	
	private int userID;
	
	private String userName, restrictions, categoryCommits, reputation;
	private int totalCommits;

	private Date signInDate;
	
	private List<ArticleEdited> editedCategories;

	public UserInfo() {}

	public UserInfo(int userID, String username, String restrictions, int totalCommits, String categoryCommits, Date signInDate, String reputation, List<ArticleEdited> editedCategories) {
		this.userID = userID;
		this.userName = username;
		this.restrictions = restrictions;
		this.totalCommits = totalCommits;
		this.categoryCommits = categoryCommits;
		this.signInDate = signInDate;
		this.reputation = reputation;
		this.editedCategories = editedCategories;
	}
	
    public String getReputation() {
    	return reputation;
    }

	
    public void setReputation(String reputation) {
    	this.reputation = reputation;
    }

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	public int getCommits() {
		return totalCommits;
	}

	public void setCommits(int commits) {
		this.totalCommits = commits;
	}

	public String getCategoryCommits() {
		return categoryCommits;
	}

	public void setCategoryCommits(String categoryCommits) {
		this.categoryCommits = categoryCommits;
	}
	
	public Date getSignInDate() {
		return signInDate;
	}

	public void setSignInDate(Date signInDate) {
		this.signInDate = signInDate;
	}

	public List<ArticleEdited> getEditedCategories() {
		return editedCategories;
	}

	public void setEditedCategories(List<ArticleEdited> editedCategories) {
		this.editedCategories = editedCategories;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public final static class ArticleEdited implements Serializable {

		/**
         * 
         */
		private static final long serialVersionUID = 1L;

		String article, category;
		double sizediff;
		int numOfCommits;

		public ArticleEdited() {}

		public ArticleEdited(String article, int commits, int sizediff, String category) {
			this.article = article;
			this.numOfCommits = commits;
			this.sizediff = sizediff;
			this.category = category;
		}
		
		public String getArticle() {
			return article;
		}

		public void setArticle(String article) {
			this.article = article;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public int getNumOfCommits() {
			return numOfCommits;
		}

		public void setNumOfCommits(int numOfCommits) {
			this.numOfCommits = numOfCommits;
		}

		public double getSizediff() {
			return sizediff;
		}

		public void setSizediff(double sizediff) {
			this.sizediff = sizediff;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	}
}
