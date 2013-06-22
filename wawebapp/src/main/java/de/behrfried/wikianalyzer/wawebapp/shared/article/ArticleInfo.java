package de.behrfried.wikianalyzer.wawebapp.shared.article;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ArticleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private int pageid;

	/**
	 *
	 */
	private Date creationDate;

	/**
	 *
	 */
	private String initialAuthor;

	/**
	 *
	 */
	private List<AuthorAndCommits> authorsAndCommits;

	/**
	 *
	 */
	private List<Revision> revisions;

	/**
	 *
	 */
	private List<SimilarArticle> similarArticles;

	/**
	 *
	 */
	private List<Categories> categories;


	public ArticleInfo() { }

	public ArticleInfo(Date creationDate,
					   int pageid,
					   String initialAuthor,
					   List<AuthorAndCommits> authorsAndCommits,
					   List<Revision> revisions,
					   List<SimilarArticle> similarArticles,
					   List<Categories> categories) {

		this.creationDate = creationDate;
		this.pageid = pageid;
		this.initialAuthor = initialAuthor;
		this.authorsAndCommits = authorsAndCommits;
		this.revisions = revisions;
		this.similarArticles = similarArticles;
		this.categories = categories;
	}

	public String getUser() {
		return initialAuthor;
	}

	public void setInitialAuthor(String initialAuthor) {
		this.initialAuthor = initialAuthor;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getPageid() {
		return pageid;
	}

	public void setPageid(int pageid) {
		this.pageid = pageid;
	}

	public String getInitialAuthor() {
		return initialAuthor;
	}

	public List<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}

	public List<AuthorAndCommits> getAuthorsAndCommits() {
		return authorsAndCommits;
	}

	public void setAuthorsAndCommits(List<AuthorAndCommits> authorsAndCommits) {
		this.authorsAndCommits = authorsAndCommits;
	}

	public List<SimilarArticle> getSimilarArticles() {
		return similarArticles;
	}

	public void setSimilarArticles(List<SimilarArticle> similarArticles) {
		this.similarArticles = similarArticles;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	/**
	 *
	 */
	public final static class AuthorAndCommits implements Serializable {

		private String author;

		private int numOfCommits;

		public AuthorAndCommits() { }

		public AuthorAndCommits(String author, int numOfCommits) {
			this.author = author;
			this.numOfCommits = numOfCommits;
		}

		public int getNumOfCommits() {
			return numOfCommits;
		}

		public void setNumOfCommits(int numOfCommits) {
			this.numOfCommits = numOfCommits;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
	}

	/**
	 *
	 */
	public final static class Revision implements Serializable {

		private int revid;

		private int parentid;

		private Date timestamp;

		private String author;

		private int diff;

		private String changeType;

		public Revision() { }

		public Revision(int revid, int parentid, Date timestamp, String author, int diff, String changeType) {
			this.revid = revid;
			this.parentid = parentid;
			this.timestamp = timestamp;
			this.author = author;
			this.diff = diff;
			this.changeType = changeType;
		}

		public int getRevid() {
			return revid;
		}

		public void setRevid(int revid) {
			this.revid = revid;
		}

		public int getParentid() {
			return parentid;
		}

		public void setParentid(int parentid) {
			this.parentid = parentid;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public int getDiff() {
			return diff;
		}

		public void setDiff(int diff) {
			this.diff = diff;
		}

		public String getChangeType() {
			return changeType;
		}

		public void setChangeType(String changeType) {
			this.changeType = changeType;
		}
	}

	/**
	 *
	 */
	public final static class SimilarArticle implements Serializable {

		public SimilarArticle() { }
	}

	/**
	 *
	 */
	public final static class Categories implements Serializable {

		public  Categories() { }
	}
}
