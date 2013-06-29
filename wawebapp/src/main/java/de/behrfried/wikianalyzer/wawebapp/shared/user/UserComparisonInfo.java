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

	private double simUserName, simRegDate, simCommits, simRep, simAbuse, simBlock, simClaComTot, simClaComDa, simClaRev, simClaCommeCom,
	        simClaDisOth, simClaDisSelf;

	public UserComparisonInfo() {}

	public UserComparisonInfo(UserInfo userInfo1, UserInfo userInfo2, double articleCooperationRatio, double categoryCooperationRatio,
	        String congruentArticles, String congruentCategories) {
		this.userInfo1 = userInfo1;
		this.userInfo2 = userInfo2;
		this.articleCooperationRatio = articleCooperationRatio;
		this.categoryCooperationRation = categoryCooperationRatio;
		this.congruentArticles = congruentArticles;
		this.congruentCategories = congruentCategories;
		this.simUserName = calcSimUserName();
		this.simRegDate = calcSimRegDate();
		this.simCommits = calcSimCommits();
		this.simRep = calcSimRep();
		this.simAbuse = calcSimAbuse();
		this.simBlock = calcSimBlock();
		this.simClaComTot = calcSimClaComTot();
		this.simClaComDa = calcSimClaComDa();
		this.simClaRev = calcSimClaRev();
		this.simClaCommeCom = calcSimClaCommeCom();
		this.simClaDisOth = calcSimClaDisOth();
		this.simClaDisSelf = calcSimClaDisSelf();
		this.similarityRatio = calcSimilarityRatio();
	}

	private double calcSimUserName() {
		double simUN = 0;
		return simUN;
	}

	private double calcSimRegDate() {
		double simRD = 0;
		return simRD;
	}

	private double calcSimCommits() {
		double simC = 0;
		return simC;
	}

	private double calcSimRep() {
		double simRep = 0;
		return simRep;
	}

	private double calcSimAbuse() {
		double simA = 0;
		return simA;
	}

	private double calcSimBlock() {
		double simB = 0;
		return simB;
	}

	private double calcSimClaComTot() {
		double simCCT = 0;
		return simCCT;
	}

	private double calcSimClaComDa() {
		double simCCD = 0;
		return simCCD;
	}

	private double calcSimClaRev() {
		double simCR = 0;
		return simCR;
	}

	private double calcSimClaCommeCom() {
		double simCCC = 0;
		return simCCC;
	}

	private double calcSimClaDisOth() {
		double simCDO = 0;
		return simCDO;
	}

	private double calcSimClaDisSelf() {
		double simCCS = 0;
		return simCCS;
	}

	private double calcSimilarityRatio() {
		return ((this.simUserName + this.simRegDate + this.simCommits + this.simRep + this.simAbuse + this.simBlock + this.simClaComTot
		        + this.simClaComDa + this.simClaRev + this.simClaCommeCom + this.simClaDisOth + this.simClaDisSelf) / 12);
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
