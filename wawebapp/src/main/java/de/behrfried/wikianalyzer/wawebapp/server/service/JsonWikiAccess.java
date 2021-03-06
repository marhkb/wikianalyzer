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
import de.behrfried.wikianalyzer.wawebapp.client.util.data.Tuple;
import de.behrfried.wikianalyzer.wawebapp.client.util.data.Tuple2;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.CriterionInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.TitleOrCategory;
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
				final String response1 = this.requester.getResult(
						this.convertRequest(
								"action=query&format=json&prop=revisions&rvprop=user|ids"
								+ "|timestamp|comment|size&rvlimit=500&rvdir=newer&rvexcludeuser=127.0.0.1&pageids=" + pageid + "&rvstartid=" + lastRev
								+ "&continue="
						)
				);

				final JsonObject root = this.parser.parse(response1).getAsJsonObject();
				final JsonObject page = root.getAsJsonObject("query")
											.getAsJsonObject("pages")
											.getAsJsonObject(pageid + "");

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
						revisions.add(
								new ArticleInfo.Revision(
										jsonObj.getAsJsonPrimitive("revid").getAsInt(),
										jsonObj.getAsJsonPrimitive("parentid")
											   .getAsInt(),
										this.formatter
												.parse(
														jsonObj.getAsJsonPrimitive("timestamp")
															   .getAsString()
															   .replace('T', '_')
												),
										author,
										jsonObj.getAsJsonPrimitive("comment").getAsString(),
										jsonObj.getAsJsonPrimitive("size").getAsInt(),
										0,
										false
								)
						);
					} catch(ParseException e) {
						this.logger.error(e.getMessage(), e);
					}
				}
				if(lastRev == 0) {
					try {
						creationDate = this.formatter.parse(
								w.get(0).getAsJsonObject().getAsJsonPrimitive("timestamp").getAsString()
								 .replace('T', '_')
						);
					} catch(ParseException e) {
						this.logger.error(e.getMessage(), e);
					}
					initialAuthor = w.get(0).getAsJsonObject().getAsJsonPrimitive("user").getAsString();
				}
				// lastRev = w.get(w.size() -
				// 1).getAsJsonObject().getAsJsonPrimitive("revid").getAsInt() +
				// 1;
				lastRev = root.has("continue") ? root.getAsJsonObject("continue")
													 .getAsJsonPrimitive("rvcontinue")
													 .getAsInt()

						:

						-1;
			}

			for(Map.Entry<String, Integer> entry : authorsAndCommitsTmp.entrySet()) {
				authorsAndCommits.add(new ArticleInfo.AuthorAndCommits(entry.getKey(), entry.getValue()));
			}
			Collections.sort(
					authorsAndCommits, new Comparator<ArticleInfo.AuthorAndCommits>() {

				@Override
				public int compare(ArticleInfo.AuthorAndCommits authorAndCommits,
								   ArticleInfo.AuthorAndCommits authorAndCommits2) {
					return authorAndCommits2.getNumOfCommits() - authorAndCommits.getNumOfCommits();
				}
			}
			);
 
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
				final Date key = new Date(
						revision.getTimestamp().getYear(),
						revision.getTimestamp().getMonth(),
						revision.getTimestamp().getDate()
				);
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
			Collections.sort(
					revsPerDates, new Comparator<ArticleInfo.RevsPerDate>() {

				@Override
				public int compare(ArticleInfo.RevsPerDate revsPerDate, ArticleInfo.RevsPerDate revsPerDate2) {
					return revsPerDate.getDate().compareTo(revsPerDate2.getDate());
				}
			}
			);
 
                        /*
                         * find edit wars
                         */
			final List<ArticleInfo.EditWar> editWars = new ArrayList<ArticleInfo.EditWar>();
			final List<ArticleInfo.Revision> revertedRevs = this.getRevertedRevisions(revisions);
			for(int i = 0; i < revertedRevs.size() - 4; i++) {
				final ArticleInfo.Revision revision = revertedRevs.get(i);
				int startI = i;
				while(i < revertedRevs.size() - 4
					  && revertedRevs.get(i + 4).getTimestamp().getTime() - revertedRevs.get(i)
																						.getTimestamp()
																						.getTime() < 100000000) {
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
					editWars.add(
							new ArticleInfo.EditWar(
									revertedRevs.get(startI).getTimestamp(),
									revertedRevs.get(i).getTimestamp(),
									usersStrBldr
											.toString().substring(0, usersStrBldr.length() - 2)
							)
					);
				}
			}
 
                        /* get similiar articles */
			// http://de.wikipedia.org/w/api.php?action=query&format=xml&list=search&srsearch=Maus&srlimit=500
			final String similar = this.requester
					.getResult(this.convertRequest("action=query&format=json&list=search&srlimit=500&srsearch=" + title));
			final JsonArray search = this.parser
					.parse(similar)
					.getAsJsonObject()
					.getAsJsonObject("query")
					.getAsJsonArray("search");

			final List<ArticleInfo.SimilarArticle> similarArticles = new ArrayList<ArticleInfo.SimilarArticle>(search.size());

			for(final JsonElement obj : search) {
				final JsonObject jsonObj = obj.getAsJsonObject();
				final String simTitle = jsonObj.getAsJsonPrimitive("title").getAsString();
				final int simPageid = this.getPageId(simTitle);

				if(simPageid == pageid) {
					continue;
				}
 
                                /* get categories */
				final String categories = this.getCategories(simPageid);
 
                                /* get creation date */
				Date simCreationDate = null;
				final String creationDateStr = this.requester
						.getResult(
								this.convertRequest(
										"action=query&format=json&prop=revisions&rvprop=timestamp&rvlimit=1&rvdir=newer&pageids=" + simPageid
								)
						);
				try {
					simCreationDate = this.formatter.parse(
							this.parser
									.parse(creationDateStr)
									.getAsJsonObject()
									.getAsJsonObject("query")
									.getAsJsonObject("pages")
									.getAsJsonObject(simPageid + "")
									.getAsJsonArray("revisions")
									.get(0)
									.getAsJsonObject()
									.getAsJsonPrimitive("timestamp")
									.getAsString()
									.replace('T', '_')
					);
				} catch(ParseException e) {
					this.logger.error(e.getMessage(), e);
				}

				similarArticles.add(new ArticleInfo.SimilarArticle(simTitle, categories, simCreationDate));

			}
 
                        /* get number of images */
			final String imageStr = this.requester
					.getResult(this.convertRequest("action=query&format=json&prop=images&pageids=" + pageid));
			final JsonObject images = this.parser
					.parse(imageStr)
					.getAsJsonObject()
					.getAsJsonObject("query")
					.getAsJsonObject("pages")
					.getAsJsonObject(pageid + "");
			int numOfImages = 0;
			if(images.has("images")) {
				numOfImages = images.getAsJsonArray("images")
									.size();
			}
 
                        /*
                         * get categories
                         */
			final String categoriesStr = this.requester
					.getResult(
							this.convertRequest(
									"action=query&format=json&prop=categories&clprop=timestamp&cllimit=500&pageids=" + pageid
							)
					);
			final JsonObject categoriesJsonObj = this.parser
					.parse(categoriesStr)
					.getAsJsonObject()
					.getAsJsonObject("query")
					.getAsJsonObject("pages")
					.getAsJsonObject(pageid + "");

			final List<ArticleInfo.Category> categoryList = new ArrayList<ArticleInfo.Category>();
			if(categoriesJsonObj.has("categories")) {
				final JsonArray categoriesJsonArr = categoriesJsonObj
						.getAsJsonArray("categories");

				for(final JsonElement catElem : categoriesJsonArr) {
					final JsonObject catJson = catElem.getAsJsonObject();
					try {

						categoryList.add(
								new ArticleInfo.Category(
										catJson.getAsJsonPrimitive("title").getAsString().replaceAll("Kategorie:", "")
											   .replaceAll("Wikipedia:", ""), this.formatter.parse(
										catJson.getAsJsonPrimitive("timestamp").getAsString()
											   .replace('T', '_')
								)
								)
						);
					} catch(ParseException e) {
						e.printStackTrace();
					}
				}
			}

			return new ArticleInfo(
					pageid,
					title,
					initialAuthor,
					creationDate,
					"http://de.wikipedia.org/wiki/" + title.replaceAll(" ", "_"),
					"http://de.wikipedia.org/wiki/Benutzer:" + initialAuthor,
					numOfImages,
					this.getCategories(pageid),
					revisions.get(
							revisions.size() - 1
					).getBytes(),
					authorsAndCommits,
					revisions,
					revsPerDates,
					editWars,
					similarArticles,
					categoryList
			);

		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return null;
	}

	public int getPageId(final String title) {
		final String response = this.requester
				.getResult(this.convertRequest("action=query&format=json&indexpageids&titles=" + title));
		this.logger.debug("Response: " + response);
		return this.parser
				.parse(response)
				.getAsJsonObject()
				.getAsJsonObject("query")
				.getAsJsonArray("pageids")
				.get(0)
				.getAsInt();
	}

	private String convertRequest(String request) {
		try {
			final String result = new URI("http", "de.wikipedia.org", "/w/api.php", request, null).toASCIIString();
			this.logger.info("Converted Request: " + result);
			return result;
		} catch(URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private String getCategories(int pageid) {
                /* get categories */
		final String categories = this.requester
				.getResult(this.convertRequest("action=query&format=json&prop=categories&pageids=" + pageid));
		final JsonObject jsonObj = this.parser
				.parse(categories)
				.getAsJsonObject()
				.getAsJsonObject("query")
				.getAsJsonObject("pages")
				.getAsJsonObject(pageid + "");
		if(!jsonObj.has("categories")) {
			return "\"nicht kategorisiert\"";
		}
		final JsonArray cats = jsonObj.getAsJsonArray("categories");
		final StringBuilder stringBuilder = new StringBuilder();
		this.logger.info("Pageid: " + pageid);
		for(JsonElement inner : cats) {
			stringBuilder.append(inner.getAsJsonObject().getAsJsonPrimitive("title").getAsString());
			stringBuilder.append("; ");
		}
		return stringBuilder.toString().substring(0, stringBuilder.length() - 2).replaceAll(
				"Kategorie:",
				""
		).replaceAll("Wikipedia:", "");
	}

	private List<ArticleInfo.Revision> getRevertedRevisions(List<ArticleInfo.Revision> revisions) {
		List<ArticleInfo.Revision> result = new ArrayList<ArticleInfo.Revision>();

		for(final ArticleInfo.Revision revision : revisions) {
			if(isRevert(revision.getComment())) {
				result.add(revision);
			}
		}
		return result;
	}

	private boolean isRevert(String comment) {
		final String lowerComment = comment.toLowerCase();
		for(final String pattern : this.patterns) {
			if(lowerComment.contains(pattern)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public UserInfo getUserInfo(String userName) throws UserNotExistException {
		try {
			final int userid = this.getUserID(userName);

			if(userid == -1) {
                        /* article does not exist */
				throw new UserNotExistException("Nutzer \"" + userid + "\" existiert nicht!");
			}

			final List<UserInfo.ArticleEdited> categoryEdited = new ArrayList<UserInfo.ArticleEdited>();

			boolean isBot = false;
			Date signInDate = null;
			int lastUserCommits = 0;

			final String response1 = this.requester.getResult(
					this.convertRequest(
							"action=query&format=json&list=users&ususers=" + userName
							+ "&usprop=editcount|registration|blockinfo|groups"
					)
			);


			final JsonObject root = this.parser.parse(response1).getAsJsonObject();
			final JsonObject user = root.getAsJsonObject("query").getAsJsonArray("users").get(0).getAsJsonObject();


			if(user.has("groups")) {
				final JsonArray groupsJsonArr = user
						.getAsJsonArray("groups");
				for(JsonElement jsonElement : groupsJsonArr) {
					if("bot".equals(jsonElement.getAsJsonPrimitive().getAsString())) {
						isBot = true;
						break;
					}
				}
			}

			final int editcount = user.getAsJsonPrimitive("editcount").getAsInt();

			final boolean blocked = user.has("blockid");

			Map<Tuple2<Integer, String>, Tuple2<Integer, Integer>> comparableRevisions =
					new HashMap<Tuple2<Integer, String>, Tuple2<Integer, Integer>>();
			final Map<String, Integer> commitsPerCategory = new HashMap<String, Integer>();
			final List<ArticleEdited> articleEditeds = new ArrayList<UserInfo.ArticleEdited>();
			int numOfComments = 0;
			int numOfReverts = 0;
			int numOfUserDiscussion = 0;
			int numofSelfDiscussion = 0;

			final String userArticles = this.requester.getResult(
					this.convertRequest(
							"action=query&format=json&list=usercontribs&ucdir=older&ucuser=" + userName
							+ "&uclimit=500&ucprop=ids|sizediff|title|flags|comment|timestamp"
					)
			);
			final JsonObject rootObj = this.parser.parse(userArticles).getAsJsonObject();

			final JsonArray articlesArticle = rootObj.getAsJsonObject("query").getAsJsonArray("usercontribs");
			lastUserCommits += articlesArticle.size();


			for(JsonElement elm : articlesArticle) {
				final JsonObject tmpObj = elm.getAsJsonObject();

				final Tuple2<Integer, String> key = Tuple.create(
						tmpObj.getAsJsonPrimitive("pageid").getAsInt(),
						tmpObj.getAsJsonPrimitive("title").getAsString()
				);

				if(key.getItem2().startsWith("Benutzer Diskussion:")) {
					if(key.getItem2().endsWith(userName)) {
						numofSelfDiscussion++;
					} else {
						numOfUserDiscussion++;
					}
				}

				final int sizediff = tmpObj.has("sizediff") ? tmpObj.getAsJsonPrimitive("sizediff").getAsInt() : 0;
				final String comment = tmpObj.has("comment") ? tmpObj.getAsJsonPrimitive("comment").getAsString()
						: "";
				if(!comment.isEmpty()) {
					numOfComments++;
					if(this.isRevert(comment)) {
						numOfReverts++;
					}
				}

				if(!comparableRevisions.containsKey(key)) {
					comparableRevisions.put(key, Tuple.create(0, 0));
				}
				comparableRevisions.put(
						key, Tuple.create(
						comparableRevisions.get(key).getItem1() + 1,
						comparableRevisions.get(key).getItem2() + sizediff
				)
				);
			}

			final Set<String> categorySet = new HashSet<String>();
			for(final Map.Entry<Tuple2<Integer, String>, Tuple2<Integer, Integer>> entry : comparableRevisions
					.entrySet()) {

				final String categories = this.getCategories(entry.getKey().getItem1());
				categoryEdited.add(
						new ArticleEdited(
								entry.getKey().getItem2(),
								entry.getValue().getItem1(),
								entry.getValue().getItem2(),
								categories
						)
				);

				final String[] catArr = categories.split(";");
				for(final String cat : catArr) {
					categorySet.add(cat.trim());

					if(!commitsPerCategory.containsKey(cat.trim())) {
						commitsPerCategory.put(cat.trim(), entry.getValue().getItem1());
					} else {
						commitsPerCategory.put(
								cat.trim(),
								(commitsPerCategory.get(cat.trim()) + entry.getValue().getItem1())
						);
					}
				}
			}

			try {
				signInDate = this.formatter
						.parse(user.getAsJsonPrimitive("registration").getAsString().replace('T', '_'));

			} catch(Exception e) {
				this.logger.warn(userName + " has no sign in date!");
				final String signInRsp = this.requester.getResult(
						this.convertRequest(
								"action=query&format=json&list=usercontribs&ucdir=newer&ucuser=" + userName
								+ "&uclimit=1&ucprop=timestamp"
						)
				);
				final JsonObject aa = this.parser.parse(userArticles).getAsJsonObject();
				final JsonArray aaa = aa.getAsJsonObject("query").getAsJsonArray("usercontribs");
				if(aaa.size() > 0) {
					// get the date of the first user contrib
					signInDate = this.formatter.parse(
							aaa.get(aaa.size() - 1).getAsJsonObject().getAsJsonPrimitive
									("timestamp")
							   .getAsString()
							   .replace('T', '_')
					);
				}
			}

			final StringBuilder categoriesStrBuilder = new StringBuilder();
			categoriesStrBuilder.append("(");
			categoriesStrBuilder.append(categorySet.size());
			categoriesStrBuilder.append(") = ");
			for(final String cat : categorySet) {
				categoriesStrBuilder.append(cat);
				categoriesStrBuilder.append("; ");
			}
 
                        /*
                         * get categories
                         */
			final String categoryCommits = categoriesStrBuilder.toString().substring(
					0,
					categoriesStrBuilder.length() -
					2
			);
 
                        /*
                         * get reputation
                         */
			final Object[] reps = this.calcReputation(userName, lastUserCommits, blocked);
			final double reputation = (Double)reps[0];
			final String abuses = (String)reps[1];
			final int abuseCount = (Integer)reps[2];
 
                        /*
                         * user classes
                         */
			String userclassNumOfCommits = "Gott";
			if(lastUserCommits < 100) {
				userclassNumOfCommits = "Rookie";
			} else if(lastUserCommits < 1000) {
				userclassNumOfCommits = "Fortgeschrittener";
			} else if(lastUserCommits < 10000) {
				userclassNumOfCommits = "Profi";
			} else if(lastUserCommits < 100000) {
				userclassNumOfCommits = "Master";
			}

			String userclassAvgCommits = "\"nicht bewertbar\"";
			double avgCommits = 0;
			if(signInDate != null) {
				avgCommits = editcount / ((System.currentTimeMillis() - signInDate.getTime()) / 86400000);
				if(avgCommits == 0.0) {
					userclassAvgCommits = "Schlafmütze";
				} else if(avgCommits < 1.0d) {
					userclassAvgCommits = "Gelegenheitssurfer";
				} else if(avgCommits < 5.0d) {
					userclassAvgCommits = "Durchschnittsuser";
				} else if(avgCommits < 15.0d) {
					userclassAvgCommits = "Vielschreiber";
				} else {
					userclassAvgCommits = "Dauergast";
				}
			}

			String userclassRevert = "\"nicht bewertbar\"";
			String userclassComment = "\"nicht bewertbar\"";

			if(lastUserCommits > 0) {
				final double revertCommitRelation = (double)numOfReverts / lastUserCommits;
				userclassRevert = "Spielverderber";
				if(revertCommitRelation < 0.01) {
					userclassRevert = "Ein-Auge-Zudrücker";
				} else if(revertCommitRelation < 0.1) {
					userclassRevert = "Gelegenheitsspießer";
				} else if(revertCommitRelation < 0.2) {
					userclassRevert = "Sturrkopf";
				} else if(revertCommitRelation < 0.5) {
					userclassRevert = "Kontrolleur";
				}

				final double commentCommitRelation = (double)numOfComments / lastUserCommits;
				userclassComment = "Saubermann";
				if(commentCommitRelation < 0.1) {
					userclassComment = "Dokuhasser";
				} else if(commentCommitRelation < 0.5) {
					userclassComment = "Ab und zu vergesslich";
				} else if(commentCommitRelation < 0.8) {
					userclassComment = "Ordnungshüter";
				}
			}


			String userDiscussion = "Sehr oft";
			if(numOfUserDiscussion == 0) {
				userDiscussion = "Nie";
			} else if(numOfUserDiscussion < 3) {
				userDiscussion = "Selten";
			} else if(numOfUserDiscussion < 10) {
				userDiscussion = "Gelegentlich";
			} else if(numOfUserDiscussion < 20) {
				userDiscussion = "Oft";
			}

			String selfDiscussion = "Sehr oft";
			if(numofSelfDiscussion == 0) {
				selfDiscussion = "Nie";
			} else if(numofSelfDiscussion < 10) {
				selfDiscussion = "Selten";
			} else if(numofSelfDiscussion < 30) {
				selfDiscussion = "Gelegentlich";
			} else if(numofSelfDiscussion < 100) {
				selfDiscussion = "Oft";
			}

			return new UserInfo(
					userid,
					userName,
					editcount,
					categoryCommits,
					avgCommits,
					signInDate,
					reputation,
					categoryEdited,
					blocked,
					userclassNumOfCommits,
					userclassAvgCommits,
					userclassRevert,
					userclassComment,
					userDiscussion,
					selfDiscussion,
					commitsPerCategory,
					abuses,
					abuseCount,
					numOfReverts,
					numOfComments,
					numOfUserDiscussion,
					numofSelfDiscussion,
					isBot
			);
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return null;
	}

	private Object[] calcReputation(String userName, int lastUserCommits, boolean blocked) {
		int abuseCnt = 0;
		String abuses = "";
		int abuseCount = 0;
		double abuseCntFactor = 1.0d;
		String tmpDate = "";

		do {
			final String abuselogStr = this.requester.getResult(
					this.convertRequest(
							"action=query&format=json&list=abuselog&afllimit=500&afluser=" + userName +
							"&aflstart=" + tmpDate
					)
			);

			final JsonObject abuseRoot = this.parser.parse(abuselogStr)
													.getAsJsonObject();
			final JsonArray abuseJsnonArr = abuseRoot
					.getAsJsonObject("query")
					.getAsJsonArray("abuselog");
			int warnCount = 0, disallowCount = 0, blockCount = 0;
			for(final JsonElement jElem : abuseJsnonArr) {
				final JsonObject jObj = jElem.getAsJsonObject();
				final String abuseResult = jObj.getAsJsonPrimitive("result").getAsString();

				abuseCnt += 1;
				if(abuseResult.contains("warn")) {
					warnCount += 5;
					abuseCntFactor = (abuseCntFactor + 5.0d) / abuseCnt;
				}
				if(abuseResult.contains("disallow")) {
					disallowCount += 10;
					;
					abuseCntFactor = (abuseCntFactor + 10.0d) / abuseCnt;
				}
				if(abuseResult.contains("block")) {
					blockCount += 15;
					abuseCntFactor = (abuseCntFactor + 15.0d) / abuseCnt;
				}
			}
			abuseCount = warnCount + disallowCount + blockCount;
			abuses = "(" + abuseCount + "): " + "warn(" + warnCount + "); disallow(" + disallowCount + "); block(" + blockCount + ");";
			tmpDate = "";
			if(abuseRoot.has("query-continue")) {
				tmpDate = abuseRoot.getAsJsonObject("query-continue")
								   .getAsJsonObject("abuselog")
								   .getAsJsonPrimitive("aflstart")
								   .getAsString();
			}

		} while(!tmpDate.isEmpty());

		this.logger.info("AbuseCntfactor: " + abuseCntFactor);
		double reputation = 1.0d - (1.0d / ((1.0d + lastUserCommits) / (1.0d + abuseCnt))) / (1 / abuseCntFactor);
		if(blocked) {
			reputation = 0;
		}
		if(reputation > 1) {
			reputation = 1;
		} else if(reputation < 0) {
			reputation = 0;
		}
		return new Object[]{new Double(reputation), abuses, abuseCnt};
	}

	@Override
	public UserComparisonInfo getUserComparisonInfo(String userName1, String userName2) throws
			UserForComparisonNotExistException {
		UserInfo user1 = null;
		UserInfo user2 = null;
		double articleCooperationRatio = 0, categoryCooperationRatio = 0;
		String congruentArticles = "", congruentCategories = "";
		try {
			user1 = this.getUserInfo(userName1);
			user2 = this.getUserInfo(userName2);
			int user1TotalArt = user1.getEditedCategories().size();
			int user1SimArtCommits = 0;
			int user1TotalCat = user1.getCommitsPerCategory().size();
			int user1SimCatCommits = 0;
			int user2TotalArt = user2.getEditedCategories().size();
			int user2SimArtCommits = 0;
			int user2TotalCat = user2.getCommitsPerCategory().size();
			int user2SimCatCommits = 0;
			int sameCat = 0;
			int sameArt = 0;
			/**
			 * Vergleich der Artikel
			 */
			final StringBuilder articlesStrBuilder = new StringBuilder();
			if(user1TotalArt < user2TotalArt) {
				for(ArticleEdited articleUser2 : user2.getEditedCategories()) {
					for(ArticleEdited articleUser1 : user1.getEditedCategories()) {
						if(articleUser2.getArticle().equals(articleUser1)) {
							sameArt++;
							user1SimArtCommits += articleUser1.getNumOfCommits();
							user2SimArtCommits += articleUser2.getNumOfCommits();
							articlesStrBuilder.append(articleUser2.getArticle());
							articlesStrBuilder.append("; ");
						}
					}
				}

			} else {
				for(ArticleEdited articleUser1 : user1.getEditedCategories()) {
					for(ArticleEdited articleUser2 : user2.getEditedCategories()) {
						if(articleUser1.getArticle().equals(articleUser2)) {
							sameArt++;
							user1SimArtCommits += articleUser1.getNumOfCommits();
							user2SimArtCommits += articleUser2.getNumOfCommits();
							articlesStrBuilder.append(articleUser2.getArticle());
							articlesStrBuilder.append("; ");
						}
					}
				}
			}
			articlesStrBuilder.insert(0, ") = ");
			articlesStrBuilder.insert(0, sameArt);
			articlesStrBuilder.insert(0, "(");
			congruentArticles = articlesStrBuilder.toString().substring(
					0,
					articlesStrBuilder.length() -
					2
			);
			if(sameArt != 0) {
				articleCooperationRatio = ((sameArt * user1SimArtCommits * user1.getReputation()) / (user1TotalArt * user1
						.getTotalCommits()) +
										   (sameArt * user2SimArtCommits * user2.getReputation()) / (user2TotalArt * user2
												   .getTotalCommits())) / 2;
			}
			/**
			 * Vergleich der Kategorien
			 */
			final StringBuilder categoryStrBuilder = new StringBuilder();
			if(user1TotalCat < user2TotalCat) {
				for(String categoryUser2 : user2.getCommitsPerCategory().keySet()) {
					for(String categoryUser1 : user1.getCommitsPerCategory().keySet()) {
						if(categoryUser2.equals(categoryUser1)) {
							sameCat++;
							user1SimCatCommits += user1.getCommitsPerCategory().get(categoryUser1);
							user2SimCatCommits += user2.getCommitsPerCategory().get(categoryUser2);
							categoryStrBuilder.append(categoryUser1);
							categoryStrBuilder.append("; ");
						}
					}
				}
			} else {
				for(String categoryUser1 : user1.getCommitsPerCategory().keySet()) {
					for(String categoryUser2 : user2.getCommitsPerCategory().keySet()) {
						if(categoryUser1.equals(categoryUser2)) {
							sameCat++;
							user1SimCatCommits += user1.getCommitsPerCategory().get(categoryUser1);
							user2SimCatCommits += user2.getCommitsPerCategory().get(categoryUser2);
							categoryStrBuilder.append(categoryUser1);
							categoryStrBuilder.append("; ");
						}
					}
				}
			}
			categoryStrBuilder.insert(0, ") = ");
			categoryStrBuilder.insert(0, sameCat);
			categoryStrBuilder.insert(0, "(");
			congruentCategories = categoryStrBuilder.toString().substring(
					0,
					categoryStrBuilder.length() -
					2
			);
			if(sameCat != 0) {
				final int tc1 = user1.getTotalCommits() > 500 ? 500 : user1.getTotalCommits();
				final int tc2 = user2.getTotalCommits() > 500 ? 500 : user2.getTotalCommits();
				categoryCooperationRatio = ((sameCat * user1SimCatCommits * user1.getReputation()) / (user1TotalCat *
																									  tc1) +
											(sameCat * user2SimCatCommits * user2.getReputation()) / (user2TotalCat *
																									  tc2)) / 2;
				categoryCooperationRatio *= 100.0d;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new UserComparisonInfo(
				user1,
				user2,
				articleCooperationRatio,
				categoryCooperationRatio,
				congruentArticles,
				congruentCategories
		);
	}

	@Override
	public CriterionInfo getCriterionInfo(List<TitleOrCategory> titlesOrCategories) throws
			CriterionNotFoundException {

		final Map<String, Map<String, Integer>> users = new HashMap<String, Map<String, Integer>>();
		final Map<String, List<Integer>> pages = new HashMap<String, List<Integer>>();
		for(final TitleOrCategory toc : titlesOrCategories) {
			if(toc.isCategory()) {
				String nextpage = "";
				do {
					String resp = this.requester.getResult(
							this.convertRequest(
									"action=query&format=json&list=categorymembers&cmlimit=500&cmtitle=Kategorie:"
									+ toc.getTitle() + "&cmstartsortkey=" + nextpage
							)
					);

					final JsonObject root = this.parser.parse(resp)
													   .getAsJsonObject();

					final JsonArray jsonPagesArr = root
							.getAsJsonObject("query")
							.getAsJsonArray("categorymembers");

					final List<Integer> pageids = new ArrayList<Integer>(jsonPagesArr.size());
					for(final JsonElement jsonElem : jsonPagesArr) {
						pageids.add(jsonElem.getAsJsonObject().getAsJsonPrimitive("pageid").getAsInt());
					}
					pages.put(toc.getTitle(), pageids);

					nextpage = "";
					if(root.has("query-continue")) {
						nextpage = root.getAsJsonObject("query-continue")
									   .getAsJsonObject("categorymembers")
									   .getAsJsonPrimitive("cmcontinue")
									   .getAsString();
					}


				} while(!nextpage.isEmpty());
			} else {
				final List<Integer> pageids = new ArrayList<Integer>(1);
				pageids.add(this.getPageId(toc.getTitle()));
				pages.put(toc.getTitle(), pageids);
				this.getPageId(toc.getTitle());
			}
		}
 
                /*
                 * iterate all criterions
                 */
		// User, Title, Commits
		for(final Map.Entry<String, List<Integer>> entry : pages.entrySet()) {

			for(final int pageid : entry.getValue()) {
                                        /*
                                 * iterate all revisions
                                 */
				int lastRev = 0;
				while(lastRev != -1) {
					final String response1 = this.requester.getResult(
							this.convertRequest(
									"action=query&format=json&prop=revisions&rvprop=user" +
									"&rvlimit=500&rvdir=newer&rvexcludeuser=127.0.0.1&pageids=" + pageid +
									"&rvstartid=" + lastRev + "&continue="
							)
					);


					final JsonObject root = this.parser.parse(response1).getAsJsonObject();
					final JsonObject page = root.getAsJsonObject("query")
												.getAsJsonObject("pages")
												.getAsJsonObject(pageid + "");

					if(!page.has("revisions")) {
						break;
					}

					final JsonArray jsonRevArr = page.getAsJsonArray("revisions");
					for(final JsonElement jsonElem : jsonRevArr) {
						final String user = jsonElem.getAsJsonObject().getAsJsonPrimitive("user").getAsString();
						if(!users.containsKey(user)) {
							final Map<String, Integer> title = new HashMap<String, Integer>();
							users.put(user, title);
						}
						final Map<String, Integer> title = users.get(user);
						if(!title.containsKey(entry.getKey())) {
							title.put(entry.getKey(), 0);
						}
						title.put(entry.getKey(), title.get(entry.getKey()) + 1);

					}

					lastRev = root.has("continue") ? root.getAsJsonObject("continue")
														 .getAsJsonPrimitive("rvcontinue")
														 .getAsInt()

							:

							-1;
				}
			}

		}

		final List<CriterionInfo.User> userList = new ArrayList<CriterionInfo.User>(users.size());
		outer:
		for(final Map.Entry<String, Map<String, Integer>> entry : users.entrySet()) {

			final CriterionInfo.User user = new CriterionInfo.User(entry.getKey(), 1);
			for(final TitleOrCategory toc : titlesOrCategories) {
				if(!entry.getValue().containsKey(toc.getTitle())) {
					continue outer;
				}
				user.setMatch(user.getMatch() * entry.getValue().get(toc.getTitle()));
			}
			userList.add(user);
		}
		outer:
		for(int i = 0; i < userList.size(); i++) {
			final CriterionInfo.User us = userList.get(i);
			final String userBotRsp = this.requester.getResult(
					this.convertRequest(
							"action=query&format=json&list=users&usprop=groups|blockinfo&ususers=" + us.getUserName()
					)
			);
			final JsonObject usersJsonObj = this.parser.parse(userBotRsp).getAsJsonObject()
													   .getAsJsonObject("query")
													   .getAsJsonArray("users")
													   .get(0).getAsJsonObject();
			if(usersJsonObj.has("blockid")) {
				userList.remove(i);
				continue;
			}
			if(!usersJsonObj.has("groups")) {
				userList.remove(i);
				continue;
			}
			final JsonArray groupsJsonArr = usersJsonObj
					.getAsJsonArray("groups");
			for(JsonElement jsonElement : groupsJsonArr) {
				if("bot".equals(jsonElement.getAsJsonPrimitive().getAsString())) {
					userList.remove(i);
					continue outer;
				}
			}
			us.setMatch(
					us.getMatch() * (Double)this.calcReputation(
							us.getUserName(), users.get(us.getUserName()).size(),
							false
					)[0]
			);
		}
		Collections.sort(
				userList, new Comparator<CriterionInfo.User>() {
			@Override
			public int compare(CriterionInfo.User user, CriterionInfo.User user2) {
				return (int)Math.signum(user2.getMatch() - user.getMatch());
			}
		}
		);

		return new CriterionInfo(userList);
	}

	private int getUserID(final String userName) {
		final String response = this.requester
				.getResult(this.convertRequest("action=query&format=json&list=users&ususers=" + userName));
		this.logger.debug("Response: " + response);
		JsonObject obj = this.parser
				.parse(response)
				.getAsJsonObject()
				.getAsJsonObject("query")
				.getAsJsonArray("users")
				.get(0)
				.getAsJsonObject();
		if(obj.has("userid")) {
			return obj.getAsJsonPrimitive("userid").getAsInt();
		}
		return -1;
	}
}