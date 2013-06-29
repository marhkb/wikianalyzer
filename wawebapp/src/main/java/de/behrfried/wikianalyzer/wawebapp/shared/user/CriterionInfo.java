package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;


public class CriterionInfo implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CriterionInfo() {}

	public final static class User implements Serializable {

		private String userName;

		private double match;

		public User() {
		}

		public User(String userName, double match) {
			this.userName = userName;
			this.match = match;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public double getMatch() {
			return match;
		}

		public void setMatch(double match) {
			this.match = match;
		}
	}
    
}
