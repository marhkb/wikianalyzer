package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;
import java.util.List;


public class UserComparisonInfo implements Serializable {
 
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String criteria, resultUserOne, resultUserTwo;
    
    private List<UserComparisonInfo> userComparisonInfos;
    
	public UserComparisonInfo() {}
	
	public UserComparisonInfo(String criteria, String resultUserOne, String resultUserTwo) {
		this.criteria = criteria;
		this.resultUserOne = resultUserOne;
		this.resultUserTwo = resultUserTwo;
	}

	
    public String getCriteria() {
    	return criteria;
    }

	
    public void setCriteria(String criteria) {
    	this.criteria = criteria;
    }

	
    public String getResultUserOne() {
    	return resultUserOne;
    }

	
    public void setResultUserOne(String resultUserOne) {
    	this.resultUserOne = resultUserOne;
    }

	
    public String getResultUserTwo() {
    	return resultUserTwo;
    }

	
    public void setResultUserTwo(String resultUserTwo) {
    	this.resultUserTwo = resultUserTwo;
    }

	
    public static long getSerialversionuid() {
    	return serialVersionUID;
    }

	
    public List<UserComparisonInfo> getUserComparisonInfos() {
    	return userComparisonInfos;
    }

	
    public void setUserComparisonInfos(List<UserComparisonInfo> userComparisonInfos) {
    	this.userComparisonInfos = userComparisonInfos;
    }
}
