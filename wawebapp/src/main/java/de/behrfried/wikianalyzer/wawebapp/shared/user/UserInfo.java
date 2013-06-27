package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserInfo implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private String userID, userName, restrictions, categoryCommits, reputation;
	private int totalCommits;

	private Date signInDate;
	
	private List<ArticleEdited> editedCategories;

	public UserInfo() {}

	public UserInfo(String userID, String username, String restrictions, int totalCommits, String categoryCommits, Date signInDate, String reputation, List<ArticleEdited> editedCategories) {
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
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

		String article, category, quantity;
		int numOfCommits;

		public ArticleEdited() {}

		public ArticleEdited(String article, int commits, String quantity) {
			this.article = article;
			this.numOfCommits = commits;
			this.quantity = quantity;
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

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	}
}
