/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package de.behrfried.wikianalyzer.wawebapp.server.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.exception.ArticleNotExistException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.CriterionNotFoundException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.UserForComparisonNotExistException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.UserNotExistException;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.CriterionInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserComparisonInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonWikiAccess implements WikiAccess {

	private final static String API = "http://de.wikipedia.org/w/api.php?";
	private final Logger logger = LoggerFactory.getLogger(JsonWikiAccess.class);
	private final WikiApi requester;
	private final JsonParser parser = new JsonParser();
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
	private List<String> patterns = new ArrayList<String>();

	@Inject
	public JsonWikiAccess(final WikiApi requester) {
		this.requester = requester;

		// 1. Änderung 114793679 von
		// [[Special:Contributions/79.197.160.242|79.197.160.242]] rückgängig
		// gemacht;
		// 2. Revert: [[Wikipedia:Vandalismus|Vandalismus]]
		// 3. Änderungen von [[Special:Beiträge/62.143.131.72|62.143.131.72]]
		// ([[Benutzer Diskussion:62.143.131.72|Diskussion]]) wurden auf die
		// letzte Version von [[Benutzer:CactusBot|CactusBot]]
		// 4. Die letzte Textänderung von
		// [[Spezial:Beiträge/87.179.226.206|87.179.226.206]] wurde verworfen
		// und die Version 114742746 von Der.Traeumer wiederhergestellt

		patterns.add("änderung");
		patterns.add("revert");
		patterns.add("zurückgesetzt");
		patterns.add("rückgängig gemacht");
	}

	@Override
	public ArticleInfo getArticleInfo(String title) throws ArticleNotExistException {
		final int pageid = this.getPageId(title);

		if(pageid == -1) {
			/* article does not exist */
			throw new ArticleNotExistException("Artikel \"" + title + "\" existiert nicht!");
		}

		final List<ArticleInfo.AuthorAndCommits> authorsAndCommits = new ArrayList<ArticleInfo.AuthorAndCommits>();
		final List<ArticleInfo.Revision> revisions = new ArrayList<ArticleInfo.Revision>();
		final List<ArticleInfo.RevsPerDate> revsPerDates = new ArrayList<ArticleInfo.RevsPerDate>();

		int lastRev = 0;

		Date creationDate = null;
		String initialAuthor = null;

		/* get revisions of an article (max 500 are allowed) */
		// http://de.wikipedia.org/w/api.php?action=query&format=xml&prop=revisions&pageids=88112&rvprop=user|ids|timestamp|sha1&rvlimit=10000&rvdiffto=next&rvdir=older
		final Map<String, Integer> authorsAndCommitsTmp = new HashMap<String, Integer>();
		final Map<Long, Integer> revsPerDatesTmp = new HashMap<Long, Integer>();
		while(lastRev != -1) {
			final String response1 = this.requester.getResult(API + "action=query&format=json&prop=revisions&rvprop=user|ids"
			        + "|timestamp|comment|size&rvlimit=500&rvdir=newer&rvexcludeuser=127.0.0.1&pageids=" + pageid + "&rvstartid=" + lastRev
			        + "&continue=");

			final JsonObject root = this.parser.parse(response1).getAsJsonObject();
			final JsonObject page = root.getAsJsonObject("query").getAsJsonObject("pages").getAsJsonObject(pageid + "");

			if(!page.has("revisions")) {
				break;
			}

			final JsonArray w = page.getAsJsonArray("revisions");

			/*
			 * iterate the revisions
			 */
			for(JsonElement obj : w) {

				final JsonObject jsonObj = obj.getAsJsonObject();
				final String author = jsonObj.getAsJsonPrimitive("user").getAsString();
				if(!authorsAndCommitsTmp.containsKey(author)) {
					authorsAndCommitsTmp.put(author, 1);
				} else {
					authorsAndCommitsTmp.put(author, authorsAndCommitsTmp.get(author) + 1);
				}

				try {
					revisions.add(new ArticleInfo.Revision(jsonObj.getAsJsonPrimitive("revid").getAsInt(), jsonObj.getAsJsonPrimitive("parentid")
					        .getAsInt(), this.formatter.parse(jsonObj.getAsJsonPrimitive("timestamp").getAsString().replace('T', '_')), author,
					        jsonObj.getAsJsonPrimitive("comment").getAsString(), jsonObj.getAsJsonPrimitive("size").getAsInt(), 0, false));
				} catch(ParseException e) {
					this.logger.error(e.getMessage(), e);
				}
			}
			if(lastRev == 0) {
				try {
					creationDate = this.formatter.parse(w.get(0).getAsJsonObject().getAsJsonPrimitive("timestamp").getAsString().replace('T', '_'));
				} catch(ParseException e) {
					this.logger.error(e.getMessage(), e);
				}
				initialAuthor = w.get(0).getAsJsonObject().getAsJsonPrimitive("user").getAsString();
			}
			// lastRev = w.get(w.size() -
			// 1).getAsJsonObject().getAsJsonPrimitive("revid").getAsInt() + 1;
			lastRev = root.has("continue") ? root.getAsJsonObject("continue").getAsJsonPrimitive("rvcontinue").getAsInt()

			:

			-1;
		}

		for(Map.Entry<String, Integer> entry : authorsAndCommitsTmp.entrySet()) {
			authorsAndCommits.add(new ArticleInfo.AuthorAndCommits(entry.getKey(), entry.getValue()));
		}
		Collections.sort(authorsAndCommits, new Comparator<ArticleInfo.AuthorAndCommits>() {

			@Override
			public int compare(ArticleInfo.AuthorAndCommits authorAndCommits, ArticleInfo.AuthorAndCommits authorAndCommits2) {
				return authorAndCommits2.getNumOfCommits() - authorAndCommits.getNumOfCommits();
			}
		});

		/* set diffs in revisions and look for edit wars */
		for(int i = 1; i < revisions.size(); i++) {
			revisions.get(i).setDiff(revisions.get(i).getBytes() - revisions.get(i - 1).getBytes());
		}

		Date currentDate = new Date(creationDate.getYear(), creationDate.getMonth(), creationDate.getDate());
		while(currentDate.before(revisions.get(revisions.size() - 1).getTimestamp())) {
			revsPerDatesTmp.put(currentDate.getTime(), 0);
			currentDate = new Date(currentDate.getTime() + 86400000);
			this.logger.info(currentDate.toString());
		}
		for(final ArticleInfo.Revision revision : revisions) {
			final Date key = new Date(revision.getTimestamp().getYear(), revision.getTimestamp().getMonth(), revision.getTimestamp().getDate());
			this.logger.info(key.toString());
			long time = key.getTime();
			try {
				if(!revsPerDatesTmp.containsKey(time)) {
					time -= 3600000;
				}
				revsPerDatesTmp.put(time, revsPerDatesTmp.get(time) + 1);
			} catch(Exception e) {
				this.logger.error(e.getMessage());
			}

		}
		for(Map.Entry<Long, Integer> entry : revsPerDatesTmp.entrySet()) {
			revsPerDates.add(new ArticleInfo.RevsPerDate(new Date(entry.getKey()), entry.getValue()));
		}
		Collections.sort(revsPerDates, new Comparator<ArticleInfo.RevsPerDate>() {

			@Override
			public int compare(ArticleInfo.RevsPerDate revsPerDate, ArticleInfo.RevsPerDate revsPerDate2) {
				return revsPerDate.getDate().compareTo(revsPerDate2.getDate());
			}
		});

		/*
		 * find edit wars
		 */
		final List<ArticleInfo.EditWar> editWars = new ArrayList<ArticleInfo.EditWar>();
		final List<ArticleInfo.Revision> revertedRevs = this.getRevertedRevisions(revisions);
		for(int i = 0; i < revertedRevs.size() - 4; i++) {
			final ArticleInfo.Revision revision = revertedRevs.get(i);
			int startI = i;
			while(i < revertedRevs.size() - 4
			        && revertedRevs.get(i + 4).getTimestamp().getTime() - revertedRevs.get(i).getTimestamp().getTime() < 100000000) {
				i += 4;
			}
			if(i != startI) {
				editWars.add(new ArticleInfo.EditWar(
                        revertedRevs.get(startI).getTimestamp(),
                        revertedRevs.get(i).getTimestamp(),
                        null));
			}
		}

		/* get similiar articles */
		// http://de.wikipedia.org/w/api.php?action=query&format=xml&list=search&srsearch=Maus&srlimit=500
		final String similar = this.requester.getResult(API + "action=query&format=json&list=search&srlimit=500&srsearch="
		        + this.convertRequest(title));
		final JsonArray search = this.parser.parse(similar).getAsJsonObject().getAsJsonObject("query").getAsJsonArray("search");

		final List<ArticleInfo.SimilarArticle> similarArticles = new ArrayList<ArticleInfo.SimilarArticle>(search.size());

		// for(final JsonElement obj : search) {
		// final JsonObject jsonObj = obj.getAsJsonObject();
		// final String simTitle =
		// jsonObj.getAsJsonPrimitive("title").getAsString();
		// final int simPageid = this.getPageId(simTitle);
		//
		// if(simPageid == pageid) {
		// continue;
		// }
		//
		// /* get categories */
		// final String categories = this.getCategories(simPageid);
		//
		// /* get creation date */
		// Date simCreationDate = null;
		// final String creationDateStr = this.requester.getResult(
		// API +
		// "action=query&format=json&prop=revisions&rvprop=timestamp&rvlimit=1&rvdir=newer&pageids="
		// + simPageid
		// );
		// try {
		// simCreationDate = this.formatter.parse(
		// this.parser
		// .parse(creationDateStr)
		// .getAsJsonObject()
		// .getAsJsonObject("query")
		// .getAsJsonObject("pages")
		// .getAsJsonObject(simPageid + "")
		// .getAsJsonArray
		// ("revisions")
		// .get(0)
		// .getAsJsonObject()
		// .getAsJsonPrimitive("timestamp")
		// .getAsString()
		// );
		// } catch(ParseException e) {
		// this.logger.error(e.getMessage(), e);
		// }
		//
		//
		// similarArticles.add(
		// new ArticleInfo.SimilarArticle(
		// simTitle,
		// categories,
		// simCreationDate
		// )
		// );
		//
		// }

		/* get number of images */

		final String imageStr = this.requester.getResult(API + "action=query&format=json&prop=images&pageids=" + pageid);
		int numOfImages = this.parser.parse(imageStr).getAsJsonObject().getAsJsonObject("query").getAsJsonObject("pages")
		        .getAsJsonObject(pageid + "").getAsJsonArray("images").size();

		return new ArticleInfo(pageid, title, initialAuthor, creationDate, "http://de.wikipedia.org/wiki/" + title.replaceAll(" ", "_"),
		        "http://de.wikipedia.org/wiki/Benutzer:" + initialAuthor, numOfImages, this.getCategories(pageid), revisions
		                .get(revisions.size() - 1).getBytes(), authorsAndCommits, revisions, revsPerDates, editWars, similarArticles);
	}

	public int getPageId(final String title) {
		final String convertedTitle = this.convertRequest(title);
		final String response = this.requester.getResult(API + "action=query&format=json&indexpageids&titles=" + convertedTitle);
		this.logger.debug("Response: " + response);
		return this.parser.parse(response).getAsJsonObject().getAsJsonObject("query").getAsJsonArray("pageids").get(0).getAsInt();
	}

	private String convertRequest(String title) {
		final String convertedTitle = title.replaceAll(" ", "%20").replaceAll("&", "%26");
		this.logger.debug("Request 'pageid' for title '" + title + "' (converted to '" + convertedTitle + "')");
		return convertedTitle;
	}

	private String getCategories(int pageid) {
		/* get categories */
		final String categories = this.requester.getResult(API + "action=query&format=json&prop=categories&pageids=" + pageid);
		final JsonArray cats = this.parser.parse(categories).getAsJsonObject().getAsJsonObject("query").getAsJsonObject("pages")
		        .getAsJsonObject(pageid + "").getAsJsonArray("categories");
		final StringBuilder stringBuilder = new StringBuilder();
		for(JsonElement inner : cats) {
			stringBuilder.append(inner.getAsJsonObject().getAsJsonPrimitive("title").getAsString());
			stringBuilder.append("\n");
		}
		final String result = stringBuilder.toString().replaceAll("Kategorie:", "");
		if(result.length() == 0) {
			return result;
		}
		return result.substring(0, result.length() - 1);
	}

	private List<ArticleInfo.Revision> getRevertedRevisions(List<ArticleInfo.Revision> revisions) {
		List<ArticleInfo.Revision> result = new ArrayList<ArticleInfo.Revision>();

		for(final ArticleInfo.Revision revision : revisions) {
			final String lowerComment = revision.getComment().toLowerCase();
			boolean matches = false;
			for(final String pattern : this.patterns) {
				if(lowerComment.contains(pattern)) {
					matches = true;
					break;
				}
			}
			if(matches) {
				this.logger.info(lowerComment);
				result.add(revision);
			}
		}
		return result;
	}

	@Override
	public UserInfo getUserInfo(String userName) throws UserNotExistException {
		final String userid = this.getUserID(userName) + "";

		if(userid.isEmpty()) {
			/* article does not exist */
			throw new UserNotExistException("Nutzer \"" + userid + "\" existiert nicht!");
		}

		final List<UserInfo.CategoryEdited> categoryEdited = new ArrayList<UserInfo.CategoryEdited>();
		final List<UserInfo.EditType> editType = new ArrayList<UserInfo.EditType>();
		final String restrictions = null;
		final int totalUserCommits = 0;
		final String categoryCommits = null;
		final String reputation = null;
		Date signInDate = null;

		final String response1 = this.requester.getResult(API + "action=query&format=json&list=users&ususers=" + convertRequest(userName)
		        + "&usprop=editcount|gender|registration|blockinfo");
		final JsonObject root = this.parser.parse(response1).getAsJsonObject();
		final JsonObject user = root.getAsJsonObject("query").getAsJsonArray("users").get(0).getAsJsonObject();

		try {
			signInDate = this.formatter.parse(user.getAsJsonPrimitive("registration").getAsString().replace('T', '_'));
		} catch(ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		// public UserInfo(String userID, String username, String restrictions,
		// String commits, String categoryCommits, Date signInDate, String
		// reputation, List<CategoryEdited> editedCategories, List<EditType>
		// editTypes) {
		return new UserInfo(userid, "http://de.wikipedia.org/wiki/Benutzer:" + userName, restrictions, totalUserCommits, categoryCommits, signInDate,
		        reputation, categoryEdited, editType);
	}

	@Override
	public UserComparisonInfo getUserComparisonInfo(String userName) throws UserForComparisonNotExistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CriterionInfo getCriterionInfo() throws CriterionNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	private int getUserID(final String userName) {
		final String convertedUserName = this.convertRequest(userName);
		final String response = this.requester.getResult(API + "action=query&format=json&list=allusers&aufrom=" + convertedUserName);
		this.logger.debug("Response: " + response);
		return this.parser.parse(response).getAsJsonObject().getAsJsonObject("query").getAsJsonArray("allusers").get(0).getAsJsonObject()
		        .getAsJsonPrimitive("userid").getAsInt();
	}
}
