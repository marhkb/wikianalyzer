package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;

public class UserComparisonInfo implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private double cooperationRatio, categoryCongruency, similarityRatio;
	private int amountAbusesUser1, amountAbusesUser2;
	private String congruentArticles, congruentCategories;
	private UserInfo userInfo1, userInfo2;

	// private int userID;
	//
	// private String userName, restrictions, categoryCommits, reputation;
	// private int totalCommits;
	//
	// private Date signInDate;
	//
	// private List<ArticleEdited> editedCategories;

	public UserComparisonInfo() {}

	public UserComparisonInfo(UserInfo userInfo1, UserInfo userInfo2, double cooperationRatio, double categoryCongruency, int amountAbusesUser1,
	        int amountAbusesUser2, String congruentArticles, String congruentCategories, double similarityRatio) {
		this.userInfo1 = userInfo1;
		this.userInfo2 = userInfo2;
		this.cooperationRatio = cooperationRatio;
		this.categoryCongruency = categoryCongruency;
		this.amountAbusesUser1 = amountAbusesUser1;
		this.amountAbusesUser2 = amountAbusesUser2;
		this.congruentArticles = congruentArticles;
		this.congruentCategories = congruentCategories;
		this.similarityRatio = similarityRatio;
	}
	
    public double getSimilarityRatio() {
    	return similarityRatio;
    }

	
    public void setSimilarityRatio(double similarityRatio) {
    	this.similarityRatio = similarityRatio;
    }

	
    public String getCongruentArticles() {
    	return congruentArticles;
    }

	
    public void setCongruentArticles(String congruentArticles) {
    	this.congruentArticles = congruentArticles;
    }

	
    public String getCongruentCategories() {
    	return congruentCategories;
    }

	
    public void setCongruentCategories(String congruentCategories) {
    	this.congruentCategories = congruentCategories;
    }

	public double getCooperationRatio() {
		return cooperationRatio;
	}

	public void setCooperationRatio(double cooperationRatio) {
		this.cooperationRatio = cooperationRatio;
	}

	public double getCategoryCongruency() {
		return categoryCongruency;
	}

	public void setCategoryCongruency(double categoryCongruency) {
		this.categoryCongruency = categoryCongruency;
	}

	public int getAmountAbusesUser1() {
		return amountAbusesUser1;
	}

	public void setAmountAbusesUser1(int amountAbusesUser1) {
		this.amountAbusesUser1 = amountAbusesUser1;
	}

	public int getAmountAbusesUser2() {
		return amountAbusesUser2;
	}

	public void setAmountAbusesUser2(int amountAbusesUser2) {
		this.amountAbusesUser2 = amountAbusesUser2;
	}

	public UserInfo getUserInfo1() {
		return userInfo1;
	}

	public void setUserInfo1(UserInfo userInfo1) {
		this.userInfo1 = userInfo1;
	}

	public UserInfo getUserInfo2() {
		return userInfo2;
	}

	public void setUserInfo2(UserInfo userInfo2) {
		this.userInfo2 = userInfo2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
