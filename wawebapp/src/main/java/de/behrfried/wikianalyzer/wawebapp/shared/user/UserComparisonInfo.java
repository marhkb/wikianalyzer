package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;

public class UserComparisonInfo implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private double articleCooperationRatio, categoryCooperationRation, similarityRatio;
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

	public UserComparisonInfo(UserInfo userInfo1, UserInfo userInfo2, double articleCooperationRatio, double categoryCooperationRatio, String congruentArticles, String congruentCategories) {
		this.userInfo1 = userInfo1;
		this.userInfo2 = userInfo2;
		this.articleCooperationRatio = articleCooperationRatio;
		this.categoryCooperationRation = categoryCooperationRatio;
		this.congruentArticles = congruentArticles;
		this.congruentCategories = congruentCategories;
		this.similarityRatio = similarityRatio;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public double getArticleCooperationRatio() {
		return articleCooperationRatio;
	}

	public void setCooperationRatio(double cooperationRatio) {
		this.articleCooperationRatio = cooperationRatio;
	}

	public double getCategoryCooperationRatio() {
		return categoryCooperationRation;
	}

	public void setCategoryCooperationRatioy(double categoryCongruency) {
		this.categoryCooperationRation = categoryCongruency;
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
}
