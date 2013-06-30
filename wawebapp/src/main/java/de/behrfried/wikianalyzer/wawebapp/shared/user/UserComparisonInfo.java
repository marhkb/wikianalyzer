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
	private double simUserName, simRegDate, simCommits, simRep, simBlock, simAbuse, simClaComTot, simClaComDa, simClaRev, simClaCommeCom,
	        simClaDisOth, simClaDisSelf, simComPDay;

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
		this.simComPDay = calcSimComPDay();
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
		long signInU1 = userInfo1.getSignInDate().getTime();
		long signInU2 = userInfo2.getSignInDate().getTime();
		if(signInU1 != 0 && signInU2 != 0) {
			long dayDifU1 = (System.currentTimeMillis() - userInfo1.getSignInDate().getTime()) / 86400000;
			long dayDifU2 = (System.currentTimeMillis() - userInfo2.getSignInDate().getTime()) / 86400000;
			if(dayDifU1 < dayDifU2) {
				return ((double)dayDifU1 / dayDifU2);
			} else if(dayDifU1 > dayDifU2) {
				return ((double)dayDifU2 / dayDifU1);
			}
			return 100;
		}
		if(signInU1 == 0 && signInU2 == 0) {
			return 100;
		}
		return 0;
	}

	private double calcSimCommits() {
		final double simCU1 = userInfo1.getTotalCommits();
		final double simCU2 = userInfo2.getTotalCommits();
		if(simCU1 != 0 && simCU2 != 0) {
			if(simCU1 < simCU2) {
				return ((double)simCU1 / simCU2);
			} else if(simCU1 > simCU2) {
				return ((double)simCU2 / simCU1);
			}
			return 100;
		}
		if(simCU1 == 0 && simCU2 == 0) {
			return 100;
		}
		return 0;
	}
	
	private double calcSimComPDay() {
		final double simComPerDayCU1 = userInfo1.getCommitsPerDay();
		final double simComPerDayCU2 = userInfo2.getCommitsPerDay();
		if(simComPerDayCU1 != 0 && simComPerDayCU2 != 0) {
			if(simComPerDayCU1 < simComPerDayCU2) {
				return ((double)simComPerDayCU1 / simComPerDayCU2);
			} else if(simComPerDayCU1 > simComPerDayCU2) {
				return ((double)simComPerDayCU2 / simComPerDayCU1);
			}
			return 100;
		}
		if(simComPerDayCU1 == 0 && simComPerDayCU2 == 0) {
			return 100;
		}
		return 0;
	}

	private double calcSimRep() {
		final double simRepU1 = userInfo1.getReputation()*100;
		final double simRepU2 = userInfo2.getReputation()*100;
		if(simRepU1 != 0 && simRepU2 != 0) {
			if(simRepU1 < simRepU2) {
				return ((double)simRepU1 / simRepU2);
			} else if(simRepU1 > simRepU2) {
				return ((double)simRepU2 / simRepU1);
			}
			return 100;
		}
		if(simRepU1 == 0 && simRepU2 == 0) {
			return 100;
		}
		return 0;
	}

	private double calcSimAbuse() {
		final double simAbuU1 = userInfo1.getAbusesRatio();
		final double simAbuU2 = userInfo2.getAbusesRatio();
		if(simAbuU1 != 0 && simAbuU2 != 0) {
			if(simAbuU1 < simAbuU2) {
				return ((double)simAbuU1 / simAbuU2);
			} else if(simAbuU1 > simAbuU2) {
				return ((double)simAbuU2 / simAbuU1);
			}
			return 100;
		}
		if(simAbuU1 == 0 && simAbuU2 == 0) {
			return 100;
		}
		return 0;
	}

	private double calcSimBlock() {
		final boolean simAbuU1 = userInfo1.isBlocked();
		final boolean simAbuU2 = userInfo2.isBlocked();
		if(simAbuU1 == simAbuU2) {
			return 100;
		}
		return 0;
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
		        + this.simClaComDa + this.simClaRev + this.simComPDay+ this.simClaCommeCom + this.simClaDisOth + this.simClaDisSelf) / 13);
	}

    public double getCategoryCooperationRation() {
    	return categoryCooperationRation;
    }

	
    public void setCategoryCooperationRation(double categoryCooperationRation) {
    	this.categoryCooperationRation = categoryCooperationRation;
    }

	
    public double getSimUserName() {
    	return simUserName;
    }

	
    public void setSimUserName(double simUserName) {
    	this.simUserName = simUserName;
    }

	
    public double getSimRegDate() {
    	return simRegDate;
    }

	
    public void setSimRegDate(double simRegDate) {
    	this.simRegDate = simRegDate;
    }

	
    public double getSimCommits() {
    	return simCommits;
    }

	
    public void setSimCommits(double simCommits) {
    	this.simCommits = simCommits;
    }

	
    public double getSimRep() {
    	return simRep;
    }

	
    public void setSimRep(double simRep) {
    	this.simRep = simRep;
    }

	
    public double getSimAbuse() {
    	return simAbuse;
    }

	
    public void setSimAbuse(double simAbuse) {
    	this.simAbuse = simAbuse;
    }

	
    public double getSimBlock() {
    	return simBlock;
    }

	
    public void setSimBlock(double simBlock) {
    	this.simBlock = simBlock;
    }

	
    public double getSimClaComTot() {
    	return simClaComTot;
    }

	
    public void setSimClaComTot(double simClaComTot) {
    	this.simClaComTot = simClaComTot;
    }

	
    public double getSimClaComDa() {
    	return simClaComDa;
    }

	
    public void setSimClaComDa(double simClaComDa) {
    	this.simClaComDa = simClaComDa;
    }

	
    public double getSimClaRev() {
    	return simClaRev;
    }

	
    public void setSimClaRev(double simClaRev) {
    	this.simClaRev = simClaRev;
    }

	
    public double getSimClaCommeCom() {
    	return simClaCommeCom;
    }

	
    public void setSimClaCommeCom(double simClaCommeCom) {
    	this.simClaCommeCom = simClaCommeCom;
    }

	
    public double getSimClaDisOth() {
    	return simClaDisOth;
    }

	
    public void setSimClaDisOth(double simClaDisOth) {
    	this.simClaDisOth = simClaDisOth;
    }

	
    public double getSimClaDisSelf() {
    	return simClaDisSelf;
    }

	
    public void setSimClaDisSelf(double simClaDisSelf) {
    	this.simClaDisSelf = simClaDisSelf;
    }
	
    public void setArticleCooperationRatio(double articleCooperationRatio) {
    	this.articleCooperationRatio = articleCooperationRatio;
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
