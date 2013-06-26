package de.behrfried.wikianalyzer.wawebapp.shared.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class UserInfo implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    
    /**
	 *
	 */
	private int pageid;

	private String title;
	
	private String userName, restrictions, commits, categoryCommits;
	
	private Date lastOnlineDate, signInDate;
	
	public UserInfo() {}
	
	public UserInfo(String username, String restrictions, String commits, String categoryCommits,Date lastOnlineDate,Date signInDate) {
		this.userName = username;
		this.restrictions = restrictions;
		this.commits = commits;
		this.categoryCommits = categoryCommits;
		this.lastOnlineDate = lastOnlineDate;
		this.signInDate = signInDate;
	}
	
	private List<CategoryEdited> editedCategories;
	
	public final static class CategoryEdited implements Serializable {
		/**
         * 
         */
        private static final long serialVersionUID = 1L;
        
		String article, category, commits, quantity;
		
		public CategoryEdited() {}
		
		public CategoryEdited(String article, String category, String commits, String quantity) {
			this.article = article;
			this.category = category;
			this.commits = commits;
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

		
        public String getCommits() {
        	return commits;
        }

		
        public void setCommits(String commits) {
        	this.commits = commits;
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
	
	private List<EditType> editTypes;
	
	public final static class EditType implements Serializable {

		/**
         * 
         */
        private static final long serialVersionUID = 1L;
		
        String editType, commits, quantity;
        
        public EditType() {}
        
        public EditType(String editType, String commits, String quantity) {
        	this.editType = editType;
        	this.commits = commits;
        	this.quantity = quantity;
        }

		
        public String getEditType() {
        	return editType;
        }

		
        public void setEditType(String editType) {
        	this.editType = editType;
        }

		
        public String getCommits() {
        	return commits;
        }

		
        public void setCommits(String commits) {
        	this.commits = commits;
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

	
    public int getPageid() {
    	return pageid;
    }

	
    public void setPageid(int pageid) {
    	this.pageid = pageid;
    }

	
    public String getTitle() {
    	return title;
    }

	
    public void setTitle(String title) {
    	this.title = title;
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

	
    public String getCommits() {
    	return commits;
    }

	
    public void setCommits(String commits) {
    	this.commits = commits;
    }

	
    public String getCategoryCommits() {
    	return categoryCommits;
    }

	
    public void setCategoryCommits(String categoryCommits) {
    	this.categoryCommits = categoryCommits;
    }

	
    public Date getLastOnlineDate() {
    	return lastOnlineDate;
    }

	
    public void setLastOnlineDate(Date lastOnlineDate) {
    	this.lastOnlineDate = lastOnlineDate;
    }

	
    public Date getSignInDate() {
    	return signInDate;
    }

	
    public void setSignInDate(Date signInDate) {
    	this.signInDate = signInDate;
    }

	
    public List<CategoryEdited> getEditedCategories() {
    	return editedCategories;
    }

	
    public void setEditedCategories(List<CategoryEdited> editedCategories) {
    	this.editedCategories = editedCategories;
    }

	
    public List<EditType> getEditTypes() {
    	return editTypes;
    }

	
    public void setEditTypes(List<EditType> editTypes) {
    	this.editTypes = editTypes;
    }

	
    public static long getSerialversionuid() {
    	return serialVersionUID;
    }
	
	
}
