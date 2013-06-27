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
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserInfo.ArticleEdited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
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
        try {
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
			final String response1 = this.requester.getResult(this.convertRequest("action=query&format=json&prop=revisions&rvprop=user|ids"
                    + "|timestamp|comment|size&rvlimit=500&rvdir=newer&rvexcludeuser=127.0.0.1&pageids=" + pageid + "&rvstartid=" + lastRev
                    + "&continue="));

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
		}
		for(final ArticleInfo.Revision revision : revisions) {
			final Date key = new Date(revision.getTimestamp().getYear(), revision.getTimestamp().getMonth(), revision.getTimestamp().getDate());
			long time = key.getTime();
			try {
				if(!revsPerDatesTmp.containsKey(time)) {
					time -= 3600000;
				}
                if(!revsPerDatesTmp.containsKey(time)) {
                    time += 7200000;
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
                final StringBuilder usersStrBldr = new StringBuilder();
                final Map<String, Integer> usersMap = new HashMap<String, Integer>();
                for(int j = startI; j <= i; j++) {
                    final String author = revertedRevs.get(j).getAuthor();
                    if(!usersMap.containsKey(author)) {
                        usersMap.put(author, 0);
                    }
                    usersMap.put(author, usersMap.get(author) + 1);

                }
                for(final Map.Entry<String, Integer> entry : usersMap.entrySet()) {
                    usersStrBldr.append(entry.getKey());
                    usersStrBldr.append(" (");
                    usersStrBldr.append(entry.getValue());
                    usersStrBldr.append("); ");
                }
				editWars.add(new ArticleInfo.EditWar(
                        revertedRevs.get(startI).getTimestamp(),
                        revertedRevs.get(i).getTimestamp(),
                        usersStrBldr.toString().substring(0, usersStrBldr.length() - 2)));
			}
		}

		/* get similiar articles */
		// http://de.wikipedia.org/w/api.php?action=query&format=xml&list=search&srsearch=Maus&srlimit=500
		final String similar = this.requester.getResult(this.convertRequest("action=query&format=json&list=search&srlimit=500&srsearch="
                + title));
		final JsonArray search = this.parser.parse(similar).getAsJsonObject().getAsJsonObject("query").getAsJsonArray("search");

		final List<ArticleInfo.SimilarArticle> similarArticles = new ArrayList<ArticleInfo.SimilarArticle>(search.size());

		for(final JsonElement obj : search) {
		final JsonObject jsonObj = obj.getAsJsonObject();
		final String simTitle =
		jsonObj.getAsJsonPrimitive("title").getAsString();
		final int simPageid = this.getPageId(simTitle);

		if(simPageid == pageid) {
		continue;
		}

		/* get categories */
		final String categories = this.getCategories(simPageid);

		/* get creation date */
		Date simCreationDate = null;
		final String creationDateStr = this.requester.getResult(
		API +
		"action=query&format=json&prop=revisions&rvprop=timestamp&rvlimit=1&rvdir=newer&pageids="
		+ simPageid
		);
		try {
		simCreationDate = this.formatter.parse(
		this.parser
		.parse(creationDateStr)
		.getAsJsonObject()
		.getAsJsonObject("query")
		.getAsJsonObject("pages")
		.getAsJsonObject(simPageid + "")
		.getAsJsonArray
		("revisions")
		.get(0)
		.getAsJsonObject()
		.getAsJsonPrimitive("timestamp")
		.getAsString().replace('T', '_')
		);
		} catch(ParseException e) {
		this.logger.error(e.getMessage(), e);
		}


		similarArticles.add(
		new ArticleInfo.SimilarArticle(
		simTitle,
		categories,
		simCreationDate
		)
		);

		}

		/* get number of images */

		final String imageStr = this.requester.getResult(API + "action=query&format=json&prop=images&pageids=" + pageid);
		int numOfImages = this.parser.parse(imageStr).getAsJsonObject().getAsJsonObject("query").getAsJsonObject("pages")
		        .getAsJsonObject(pageid + "").getAsJsonArray("images").size();

        /*
         * get categories
         */
        final String categoriesStr = this.requester.getResult(
                API + "action=query&format=json&prop=categories&clprop=timestamp&cllimit=500&pageids=" + pageid
        );
        final JsonArray categoriesJson = this.parser.parse(categoriesStr)
                .getAsJsonObject()
                .getAsJsonObject("query")
                .getAsJsonObject("pages")
                .getAsJsonObject(pageid + "")
                .getAsJsonArray("categories");
        final List<ArticleInfo.Category> categoryList = new ArrayList<ArticleInfo.Category>(categoriesJson.size());
        for(final JsonElement catElem : categoriesJson) {
            final JsonObject catJson = catElem.getAsJsonObject();
            try {

                categoryList.add(new ArticleInfo.Category(
                        catJson.getAsJsonPrimitive("title").getAsString()
                                .replaceAll("Kategorie:", "")
                                .replaceAll("Wikipedia:", ""),
                        this.formatter.parse(
                            catJson.getAsJsonPrimitive("timestamp").getAsString().replace('T', '_'))
                        )
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


		return new ArticleInfo(pageid, title, initialAuthor, creationDate, "http://de.wikipedia.org/wiki/" + title.replaceAll(" ", "_"),
		        "http://de.wikipedia.org/wiki/Benutzer:" + initialAuthor, numOfImages, this.getCategories(pageid), revisions
		                .get(revisions.size() - 1).getBytes(), authorsAndCommits, revisions, revsPerDates, editWars, similarArticles, categoryList);

        } catch (Exception e) {
           this.logger.error(e.getMessage(), e);
        }
        return null;
    }

	public int getPageId(final String title) {
		final String response = this.requester.getResult(this.convertRequest("action=query&format=json&indexpageids&titles=" + title));
		this.logger.debug("Response: " + response);
		return this.parser.parse(response).getAsJsonObject().getAsJsonObject("query").getAsJsonArray("pageids").get(0).getAsInt();
	}

    private String convertRequest(String request) {
        try {
            return new URI("http",
                    "de.wikipedia.org",
                    "/w/api.php",
                    request,
                    null).toASCIIString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

	private String getCategories(int pageid) {
		/* get categories */
		final String categories = this.requester.getResult(API + "action=query&format=json&prop=categories&pageids=" + pageid);
		final JsonArray cats = this.parser.parse(categories).getAsJsonObject().getAsJsonObject("query").getAsJsonObject("pages")
		        .getAsJsonObject(pageid + "").getAsJsonArray("categories");
		final StringBuilder stringBuilder = new StringBuilder();
		for(JsonElement inner : cats) {
			stringBuilder.append(inner.getAsJsonObject().getAsJsonPrimitive("title").getAsString());
			stringBuilder.append("; ");
		}
		return stringBuilder.toString().replaceAll("Kategorie:", "")
                .replaceAll("Wikipedia:", "");
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

		final List<UserInfo.ArticleEdited> categoryEdited = new ArrayList<UserInfo.ArticleEdited>();
		final String restrictions = null;
		int totalUserCommits = 0;
		final String articleCommits = null;
		final String reputation = null;
		Date signInDate = null;
		
		String editTypeDesc, editTypeQuantity;
		int editTypeNumOfCommits;
		
		String article, category, quantity;
		int numOfCommits;

		final String response1 = this.requester.getResult(this.convertRequest("action=query&format=json&list=users&ususers=" + userName
                + "&usprop=editcount|registration"));
		final JsonObject root = this.parser.parse(response1).getAsJsonObject();
		final JsonObject user = root.getAsJsonObject("query").getAsJsonArray("users").get(0).getAsJsonObject();
		

		try {
			signInDate = this.formatter.parse(user.getAsJsonPrimitive("registration").getAsString().replace('T', '_'));
			totalUserCommits = user.getAsJsonPrimitive("editcount").getAsInt();
		} catch(ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		//TODO für abuses
		final String response2 = this.requester.getResult(API + "action=query&format=json&list=abuselog&afllimit=500&afluser=" + userName);
		// public UserInfo(String userID, String username, String restrictions,
		// String commits, String categoryCommits, Date signInDate, String
		// reputation, List<CategoryEdited> editedCategories, List<EditType>
		// editTypes) {
		
		//TODO anzahl der artikel ... man bekommt nur 500 deswegen wieder schleife
		
		
//		String tmpArticle = "";
//		final String articleCategory = this.requester.getResult(API + "action=query&prop=categories&cllimit=500&titles="+tmpArticle);
		
		String tmpDate = "";
		Map<String, Map<String, String>> comparableRevisions = new HashMap();
		final List<ArticleEdited> articleEditeds = new ArrayList<UserInfo.ArticleEdited>();
		do {
			String userArticles = this.requester.getResult(API + "action=query&format=json&list=usercontribs&ucdir=newer&ucuser=" + userName +"&uclimit=500&continue=" + tmpDate);
			final JsonObject articles = this.parser.parse(userArticles).getAsJsonObject();
			
			final JsonArray articlesArticle = root.getAsJsonObject("query").getAsJsonArray("usercontribs");
			for(JsonElement elm : articlesArticle) {
				JsonObject tmpObj = elm.getAsJsonObject();
				String pageId = tmpObj.getAsJsonPrimitive("pageid").getAsString();
				String curRev = tmpObj.getAsJsonPrimitive("revid").getAsString();
				String parRev = tmpObj.getAsJsonPrimitive("parentid").getAsString();
				
				if(comparableRevisions.containsKey(pageId) && !comparableRevisions.get(pageId).containsKey(curRev)) {
						comparableRevisions.get(pageId).put(curRev, parRev);
				} else {
					Map<String,String> tmpMap = new HashMap<String,String>();
					tmpMap.put(curRev, parRev);
					comparableRevisions.put(pageId, tmpMap);
				}
			}
			tmpDate = root.getAsJsonObject("query").getAsJsonObject("continue").getAsJsonPrimitive("ucstart").getAsString();
		}while(!tmpDate.isEmpty());
		
		return new UserInfo(userid, userName, restrictions, totalUserCommits, articleCommits, signInDate,
		        reputation, categoryEdited);
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
		final String response =
                this.requester.getResult(this.convertRequest("action=query&format=json&list=allusers&aufrom=" + userName));
		this.logger.debug("Response: " + response);
		return this.parser.parse(response).getAsJsonObject().getAsJsonObject("query").getAsJsonArray("allusers").get(0).getAsJsonObject()
		        .getAsJsonPrimitive("userid").getAsInt();
	}
}
