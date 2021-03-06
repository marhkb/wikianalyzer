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

	private String title;

	private String initialAuthor;


	/**
	 *
	 */
	private Date creationDate;

	private String articleLink;

	/**
	 *
	 */
	private String initialAuthorLink;

	private int images;

	private String categories;

	private int bytes;

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
	private List<RevsPerDate> revsPerDates;

	/**
	 *
	 */
	private List<EditWar> editWars;

	/**
	 *
	 */
	private List<SimilarArticle> similarArticles;

	/**
	 *
	 */
	private List<Category> categoryList;


	public ArticleInfo() { }

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getInitialAuthor() {
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

	public String getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}

	public String getInitialAuthorLink() {
		return initialAuthorLink;
	}

	public void setInitialAuthorLink(String initialAuthorLink) {
		this.initialAuthorLink = initialAuthorLink;
	}

	public int getImages() {
		return images;
	}

	public void setImages(int images) {
		this.images = images;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public List<AuthorAndCommits> getAuthorsAndCommits() {
		return authorsAndCommits;
	}

	public void setAuthorsAndCommits(List<AuthorAndCommits> authorsAndCommits) {
		this.authorsAndCommits = authorsAndCommits;
	}

	public List<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}

	public List<SimilarArticle> getSimilarArticles() {
		return similarArticles;
	}

	public void setSimilarArticles(List<SimilarArticle> similarArticles) {
		this.similarArticles = similarArticles;
	}

	public List<RevsPerDate> getRevsPerDates() {
		return revsPerDates;
	}

	public List<EditWar> getEditWars() {
		return editWars;
	}

	public void setEditWars(List<EditWar> editWars) {
		this.editWars = editWars;
	}

	public void setRevsPerDates(List<RevsPerDate> revsPerDates) {
		this.revsPerDates = revsPerDates;
	}

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public ArticleInfo(int pageid,
					   String title,
					   String initialAuthor,
					   Date creationDate,
					   String articleLink,
					   String initialAuthorLink,
					   int images,
					   String categories,
					   int bytes,
					   List<AuthorAndCommits> authorsAndCommits,
					   List<Revision> revisions,
					   List<RevsPerDate> revsPerDates,
					   List<EditWar> editWars,
					   List<SimilarArticle> similarArticles,
                       List<Category> categoryList) {
		this.pageid = pageid;
		this.title = title;
		this.initialAuthor = initialAuthor;
		this.creationDate = creationDate;
		this.articleLink = articleLink;
		this.initialAuthorLink = initialAuthorLink;
		this.images = images;
		this.categories = categories;
		this.bytes = bytes;
		this.authorsAndCommits = authorsAndCommits;
		this.revisions = revisions;
		this.revsPerDates = revsPerDates;
		this.editWars = editWars;
		this.similarArticles = similarArticles;
        this.categoryList = categoryList;
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

		private String comment;

		private int bytes;

		private int diff;

		private boolean isPartOfEditWar;

		public Revision() { }

		public Revision(int revid,
						int parentid,
						Date timestamp,
						String author,
						String comment,
						int bytes,
						int diff,
						boolean partOfEditWar) {
			this.revid = revid;
			this.parentid = parentid;
			this.timestamp = timestamp;
			this.author = author;
			this.comment = comment;
			this.bytes = bytes;
			this.diff = diff;
			isPartOfEditWar = partOfEditWar;
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

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public int getBytes() {
			return bytes;
		}

		public void setBytes(int bytes) {
			this.bytes = bytes;
		}

		public int getDiff() {
			return diff;
		}

		public void setDiff(int diff) {
			this.diff = diff;
		}

		public boolean isPartOfEditWar() {
			return isPartOfEditWar;
		}

		public void setPartOfEditWar(boolean partOfEditWar) {
			isPartOfEditWar = partOfEditWar;
		}
	}


	public final static class RevsPerDate implements Serializable {

		private Date date;

		private int numOfRevs;

		public RevsPerDate() { }

		public RevsPerDate(Date date, int numOfRevs) {
			this.date = date;
			this.numOfRevs = numOfRevs;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getNumOfRevs() {
			return numOfRevs;
		}

		public void setNumOfRevs(int numOfRevs) {
			this.numOfRevs = numOfRevs;
		}
	}

	public final static class EditWar implements Serializable {

		private Date from;

		private Date to;

		private String users;

		public EditWar() {
		}

		public EditWar(Date from, Date to, String users) {
			this.from = from;
			this.to = to;
			this.users = users;
		}

		public Date getFrom() {
			return from;
		}

		public void setFrom(Date from) {
			this.from = from;
		}

		public Date getTo() {
			return to;
		}

		public void setTo(Date to) {
			this.to = to;
		}

		public String getUsers() {
			return users;
		}

		public void setUsers(String users) {
			this.users = users;
		}
	}

	/**
	 *
	 */
	public final static class SimilarArticle implements Serializable {

		private String title;

		private String categories;

		private Date timestamp;

		public SimilarArticle() { }

		public SimilarArticle(String title, String categories, Date timestamp) {
			this.title = title;
			this.categories = categories;
			this.timestamp = timestamp;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getCategories() {
			return categories;
		}

		public void setCategories(String categories) {
			this.categories = categories;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}
	}

	/**
	 *
	 */
	public final static class Category implements Serializable {

        private String name;

        private Date timestamp;

		public Category() { }

        public Category(String name, Date timestamp) {
            this.name = name;
            this.timestamp = timestamp;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }
    }
}
