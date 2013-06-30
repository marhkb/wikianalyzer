package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserInfo implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private String userName, categoryCommits, userclassNumOfCommits, userclassAvgCommits, userclassReverts, userclassCleaness,
	        userclassUserDiscussion, userclassSelfDiscussion, abusesDescription;
	private double reputation, commitsPerDay, revertCommitsRatio, abusesRatio, commentsPerCommitRatio;
	private int userID, totalCommits, numOfReverts, numOfComments, numOfUserDiscussion, numOfSelfDiscussion;
	private Date signInDate;

	private Map<String, Integer> commitsPerCategory;
	private List<ArticleEdited> editedCategories;

	private boolean blocked;

	public UserInfo() {}

	@Override
	public String toString() {
		return "UserInfo{" + "userName='" + userName + '\'' + ", userclassNumOfCommits='" + userclassNumOfCommits + '\'' + ", userclassAvgCommits='"
		        + userclassAvgCommits + '\'' + ", userclassReverts='" + userclassReverts + '\'' + ", userclassCleaness='" + userclassCleaness + '\''
		        + ", reputation=" + reputation + ", commitsPerDay=" + commitsPerDay + ", userclassUserDiscussion='" + userclassUserDiscussion + '\''
		        + ", userclassSelfDiscussion='" + userclassSelfDiscussion + '\'' + ", userID=" + userID + ", totalCommits=" + totalCommits
		        + ", signInDate=" + signInDate + ", blocked=" + blocked + '}';
	}

	public UserInfo(int userID, String username, int totalCommits, String categoryCommits, double commitsPerDay, Date signInDate, double reputation,
	        List<ArticleEdited> editedCategories, boolean blocked, String userclassNumOfCommits, String userclassAvgCommits, String userclassReverts,
	        String userclassCleaness, String userclassUserDiscussion, String userclassSelfDiscussion, Map<String, Integer> commitsPerCategory,
	        String abusesDescription, int abusesRatio, int numOfReverts, int numOfComments, int numOfUserDiscussion, int numOfSelfDiscussion) {
		this.userID = userID;
		this.userName = username;
		this.totalCommits = totalCommits;
		this.categoryCommits = categoryCommits;
		this.signInDate = signInDate;
		this.reputation = reputation;
		this.editedCategories = editedCategories;
		this.commitsPerDay = commitsPerDay;
		this.blocked = blocked;
		this.userclassNumOfCommits = userclassNumOfCommits;
		this.userclassAvgCommits = userclassAvgCommits;
		this.userclassReverts = userclassReverts;
		this.userclassCleaness = userclassCleaness;
		this.userclassUserDiscussion = userclassUserDiscussion;
		this.userclassSelfDiscussion = userclassSelfDiscussion;
		this.commitsPerCategory = commitsPerCategory;
		this.abusesDescription = abusesDescription;
		this.abusesRatio = abusesRatio;
		this.numOfReverts = numOfReverts;
		this.revertCommitsRatio = calcRevertCommitsRatio();
		this.numOfComments = numOfComments;
		this.commentsPerCommitRatio = calcCommentsPerCommitRatio();
		this.numOfSelfDiscussion = numOfSelfDiscussion;
		this.numOfUserDiscussion = numOfUserDiscussion;
	}
	
    public int getNumOfUserDiscussion() {
    	return numOfUserDiscussion;
    }

	
    public void setNumOfUserDiscussion(int numOfUserDiscussion) {
    	this.numOfUserDiscussion = numOfUserDiscussion;
    }

	
    public int getNumOfSelfDiscussion() {
    	return numOfSelfDiscussion;
    }

	
    public void setNumOfSelfDiscussion(int numOfSelfDiscussion) {
    	this.numOfSelfDiscussion = numOfSelfDiscussion;
    }

	private double calcRevertCommitsRatio() {
		return ((double)this.numOfReverts / this.totalCommits);
	}

	private double calcCommentsPerCommitRatio() {
		return ((double)this.numOfComments / this.totalCommits);
	}

	public double getCommentsPerCommitRatio() {
		return commentsPerCommitRatio;
	}

	public void setCommentsPerCommitRatio(double commentsPerCommitRatio) {
		this.commentsPerCommitRatio = commentsPerCommitRatio;
	}

	public int getNumOfComments() {
		return numOfComments;
	}

	public void setNumOfComments(int numOfComments) {
		this.numOfComments = numOfComments;
	}

	public double getRevertCommitsRatio() {
		return revertCommitsRatio;
	}

	public void setRevertCommitsRatio(double revertCommitsRatio) {
		this.revertCommitsRatio = revertCommitsRatio;
	}

	public int getNumOfReverts() {
		return numOfReverts;
	}

	public void setNumOfReverts(int numOfReverts) {
		this.numOfReverts = numOfReverts;
	}

	public double getAbusesRatio() {
		return abusesRatio;
	}

	public void setAbusesRatio(double abusesRatio) {
		this.abusesRatio = abusesRatio;
	}

	public String getAbusesDescription() {
		return abusesDescription;
	}

	public void setAbusesDescription(String abusesDescription) {
		this.abusesDescription = abusesDescription;
	}

	public Map<String, Integer> getCommitsPerCategory() {
		return commitsPerCategory;
	}

	public void setCommitsPerCategory(Map<String, Integer> commitsPerCategory) {
		this.commitsPerCategory = commitsPerCategory;
	}

	public String getUserclassUserDiscussion() {
		return userclassUserDiscussion;
	}

	public void setUserclassUserDiscussion(String userclassUserDiscussion) {
		this.userclassUserDiscussion = userclassUserDiscussion;
	}

	public String getUserclassSelfDiscussion() {
		return userclassSelfDiscussion;
	}

	public void setUserclassSelfDiscussion(String userclassSelfDiscussion) {
		this.userclassSelfDiscussion = userclassSelfDiscussion;
	}

	public String getUserclassAvgCommits() {
		return userclassAvgCommits;
	}

	public void setUserclassAvgCommits(String userclassAvgCommits) {
		this.userclassAvgCommits = userclassAvgCommits;
	}

	public String getUserclassReverts() {
		return userclassReverts;
	}

	public void setUserclassReverts(String userclassReverts) {
		this.userclassReverts = userclassReverts;
	}

	public String getUserclassCleaness() {
		return userclassCleaness;
	}

	public void setUserclassCleaness(String userclassCleaness) {
		this.userclassCleaness = userclassCleaness;
	}

	public String getUserclassNumOfCommits() {
		return userclassNumOfCommits;
	}

	public void setUserclassNumOfCommits(String userclassNumOfCommits) {
		this.userclassNumOfCommits = userclassNumOfCommits;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public double getReputation() {
		return reputation;
	}

	public void setReputation(double reputation) {
		this.reputation = reputation;
	}

	public int getTotalCommits() {
		return totalCommits;
	}

	public void setTotalCommits(int totalCommits) {
		this.totalCommits = totalCommits;
	}

	public double getCommitsPerDay() {
		return commitsPerDay;
	}

	public void setCommitsPerDay(double commitsPerDay) {
		this.commitsPerDay = commitsPerDay;
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
