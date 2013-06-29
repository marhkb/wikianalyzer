package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;
import java.util.List;


public class CriterionInfo implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	private List<User> users;

    public CriterionInfo() {}

	public CriterionInfo(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "CriterionInfo{" +
			   "users=" + users +
			   '}';
	}

	public final static class User implements Serializable {


		@Override
		public String toString() {
			return "User{" +
				   "userName='" + userName + '\'' +
				   ", match=" + match +
				   '}';
		}

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
