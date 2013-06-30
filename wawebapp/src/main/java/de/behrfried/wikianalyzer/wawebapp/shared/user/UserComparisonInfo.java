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
	private double simUserName, simRegDate, simCommits, simRep, simBlock, simAbuse, simRevCom, simCommeCom, simDisOth, simDisSelf, simComPDay, simCatRatio, simArtRatio;

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
		this.simRevCom = calcSimRevCom();
		this.simCommeCom = calcSimCommeCom();
		this.simDisOth = calcSimDisOth();
		this.simDisSelf = calcSimDisSelf();
		this.simCatRatio = calcSimCat();
		this.simArtRatio = calcSimArt();
		this.similarityRatio = calcSimilarityRatio();
	}

	private double calcSimUserName() {
		double simUN = 0;
		return simUN;
	}

	private double calcSimRegDate() {
		long signInU1 = 0;
		long signInU2 = 0;
		if(userInfo1.getSignInDate() != null && userInfo2.getSignInDate() != null) {
			signInU1 = userInfo1.getSignInDate().getTime();
			signInU2 = userInfo2.getSignInDate().getTime();
		}
		if(signInU1 != 0 && signInU2 != 0) {
			long dayDifU1 = (System.currentTimeMillis() - userInfo1.getSignInDate().getTime()) / 86400000;
			long dayDifU2 = (System.currentTimeMillis() - userInfo2.getSignInDate().getTime()) / 86400000;
			if(dayDifU1 < dayDifU2) {
				return (((double)dayDifU1 / dayDifU2) * 100);
			} else if(dayDifU1 > dayDifU2) {
				return (((double)dayDifU2 / dayDifU1) * 100);
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
				return (((double)simCU1 / simCU2) * 100);
			} else if(simCU1 > simCU2) {
				return (((double)simCU2 / simCU1) * 100);
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
				return ((double)simComPerDayCU1 / simComPerDayCU2) * 100;
			} else if(simComPerDayCU1 > simComPerDayCU2) {
				return ((double)simComPerDayCU2 / simComPerDayCU1) * 100;
			}
			return 100;
		}
		if(simComPerDayCU1 == 0 && simComPerDayCU2 == 0) {
			return 100;
		}
		return 0;
	}

	private double calcSimRep() {
		final double simRepU1 = userInfo1.getReputation() * 100;
		final double simRepU2 = userInfo2.getReputation() * 100;
		if(simRepU1 != 0 && simRepU2 != 0) {
			if(simRepU1 < simRepU2) {
				return ((double)simRepU1 / simRepU2) * 100;
			} else if(simRepU1 > simRepU2) {
				return ((double)simRepU2 / simRepU1) * 100;
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

	private double calcSimCommeCom() {
		final double simComComU1 = userInfo1.getCommentsPerCommitRatio() * 100;
		final double simComComU2 = userInfo2.getCommentsPerCommitRatio() * 100;
		if(simComComU1 != 0 && simComComU2 != 0) {
			if(simComComU1 < simComComU2) {
				return (((double)simComComU1 / simComComU2) * 100);
			} else if(simComComU1 > simComComU2) {
				return (((double)simComComU2 / simComComU1) * 100);
			}
			return 100;
		}
		if(simComComU1 == 0 && simComComU2 == 0) {
			return 100;
		}
		return 0;
	}

	private double calcSimDisOth() {
		final double simDisOthU1 = userInfo1.getNumOfUserDiscussion();
		final double simDisOthU2 = userInfo2.getNumOfUserDiscussion();
		if(simDisOthU1 != 0 && simDisOthU2 != 0) {
			if(simDisOthU1 < simDisOthU2) {
				return (((double)simDisOthU1 / simDisOthU2) * 100);
			} else if(simDisOthU1 > simDisOthU2) {
				return (((double)simDisOthU2 / simDisOthU1) * 100);
			}
			return 100;
		}
		if(simDisOthU1 == 0 && simDisOthU2 == 0) {
			return 100;
		}
		return 0;
	}

	private double calcSimDisSelf() {
		final double simDisSelfU1 = userInfo1.getNumOfSelfDiscussion();
		final double simDisSelfU2 = userInfo2.getNumOfSelfDiscussion();
		if(simDisSelfU1 != 0 && simDisSelfU2 != 0) {
			if(simDisSelfU1 < simDisSelfU2) {
				return (((double)simDisSelfU1 / simDisSelfU2) * 100);
			} else if(simDisSelfU1 > simDisSelfU2) {
				return (((double)simDisSelfU2 / simDisSelfU1) * 100);
			}
			return 100;
		}
		if(simDisSelfU1 == 0 && simDisSelfU2 == 0) {
			return 100;
		}
		return 0;
	}
	
	private double calcSimRevCom() {
		final double simRevComU1 = userInfo1.getRevertCommitsRatio();
		final double simRevComU2 = userInfo2.getRevertCommitsRatio();
		if(simRevComU1 != 0 && simRevComU2 != 0) {
			if(simRevComU1 < simRevComU2) {
				return (((double)simRevComU1 / simRevComU2) * 100);
			} else if(simRevComU1 > simRevComU2) {
				return (((double)simRevComU2 / simRevComU1) * 100);
			}
			return 100;
		}
		if(simRevComU1 == 0 && simRevComU2 == 0) {
			return 100;
		}
		return 0;
	}
	
	public double calcSimCat() {
		final double simCatSizeU1 = userInfo1.getCommitsPerCategory().size();
		final double simCatSizeU2 = userInfo2.getCommitsPerCategory().size();
		if(simCatSizeU1 != 0 && simCatSizeU2 != 0) {
			if(simCatSizeU1 < simCatSizeU2) {
				return (((double)simCatSizeU1 / simCatSizeU2) * 100);
			} else if(simCatSizeU1 > simCatSizeU2) {
				return (((double)simCatSizeU2 / simCatSizeU1) * 100);
			}
			return 100;
		}
		if(simCatSizeU1 == 0 && simCatSizeU2 == 0) {
			return 100;
		}
		return 0;
	}


	public double calcSimArt() {
		final double simCatSizeU1 = userInfo1.getEditedCategories().size();
		final double simCatSizeU2 = userInfo2.getEditedCategories().size();
		if(simCatSizeU1 != 0 && simCatSizeU2 != 0) {
			if(simCatSizeU1 < simCatSizeU2) {
				return (((double)simCatSizeU1 / simCatSizeU2) * 100);
			} else if(simCatSizeU1 > simCatSizeU2) {
				return (((double)simCatSizeU2 / simCatSizeU1) * 100);
			}
			return 100;
		}
		if(simCatSizeU1 == 0 && simCatSizeU2 == 0) {
			return 100;
		}
		return 0;
	}
	
	private double calcSimilarityRatio() {
		return ((this.simUserName + this.simRegDate + this.simCommits + this.simRep + this.simAbuse + this.simBlock + this.simRevCom
		        + this.simComPDay + this.simCommeCom + this.simDisOth + this.simDisSelf) / 11);
	}
	
	

	
    public double getSimCatRatio() {
    	return simCatRatio;
    }

	
    public void setSimCatRatio(double simCatRatio) {
    	this.simCatRatio = simCatRatio;
    }

	
    public double getSimArtRatio() {
    	return simArtRatio;
    }

	
    public void setSimArtRatio(double simArtRatio) {
    	this.simArtRatio = simArtRatio;
    }

	
    public void setSimRevCom(double simRevCom) {
    	this.simRevCom = simRevCom;
    }

	public double getSimComPDay() {
		return simComPDay;
	}

	public void setSimComPDay(double simComPDay) {
		this.simComPDay = simComPDay;
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

	public double getSimRevCom() {
		return simRevCom;
	}

	public void setSimClaRev(double simClaRev) {
		this.simRevCom = simClaRev;
	}

	public double getSimCommeCom() {
		return simCommeCom;
	}

	public void setSimCommeCom(double simClaCommeCom) {
		this.simCommeCom = simClaCommeCom;
	}

	public double getSimDisOth() {
		return simDisOth;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getSimRev() {
		return simRevCom;
	}

	public void setSimDisOth(double simClaDisOth) {
		this.simDisOth = simClaDisOth;
	}

	public double getSimDisSelf() {
		return simDisSelf;
	}

	public void setSimDisSelf(double simClaDisSelf) {
		this.simDisSelf = simClaDisSelf;
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
