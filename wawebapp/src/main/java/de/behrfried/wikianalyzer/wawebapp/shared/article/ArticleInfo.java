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

		public Revision() { }
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
