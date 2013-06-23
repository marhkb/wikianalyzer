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
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;
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
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


	@Inject
	public JsonWikiAccess(final WikiApi requester) {
		this.requester = requester;
	}

	@Override
	public ArticleInfo getArticleInfo(String title) {
		final int pageid = this.getPageId(title);
		final List<ArticleInfo.AuthorAndCommits> authorsAndCommits = new ArrayList<ArticleInfo.AuthorAndCommits>();
		final List<ArticleInfo.Revision> revisions = new ArrayList<ArticleInfo.Revision>();

		int lastRev = 0;

		Date creationDate = null;
		String initialAuthor = null;

		/* get revisions of an article (max 500 are allowed) */
		// http://de.wikipedia.org/w/api.php?action=query&format=xml&prop=revisions&pageids=88112&rvprop=user|ids|timestamp|sha1&rvlimit=10000&rvdiffto=next&rvdir=older
		final Map<String, Integer> tmp = new HashMap<String, Integer>();
		while(true) {
			final String response1 =
					this.requester.getResult(
							API + "action=query&format=json&prop=revisions&rvprop=user|ids" +
							"|timestamp|comment|size&rvlimit=500&rvdir=newer&rvexcludeuser=127.0.0.1&pageids=" +
							pageid + "&rvstartid=" + lastRev
					);

			final JsonObject page = this.parser.parse(response1)
											   .getAsJsonObject()
											   .getAsJsonObject("query")
											   .getAsJsonObject("pages")
											   .getAsJsonObject(pageid + "");

			if(!page.has("revisions")) {
				break;
			}

			final JsonArray w = page.getAsJsonArray("revisions");
			for(JsonElement obj : w) {

				//{"revid":69797531,"parentid":69352711,"user":"80.143.242.119","anon":"","timestamp":"2010-01-25T18:38:06Z","diff":{"notcached":""}}
				final JsonObject jsonObj = obj.getAsJsonObject();
				final String author = jsonObj.getAsJsonPrimitive("user").getAsString();
				if(!tmp.containsKey(author)) {
					tmp.put(author, 1);
				} else {
					tmp.put(author, tmp.get(author) + 1);
				}

				try {
					revisions.add(
							new ArticleInfo.Revision(
									jsonObj.getAsJsonPrimitive("revid").getAsInt(),
									jsonObj.getAsJsonPrimitive("parentid").getAsInt(),
									this.formatter.parse(
											jsonObj.getAsJsonPrimitive("timestamp").getAsString()
									),
									author,
									jsonObj.getAsJsonPrimitive("comment").getAsString(),
									jsonObj.getAsJsonPrimitive("size").getAsInt(),
									0
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
					);
				} catch(ParseException e) {
					this.logger.error(e.getMessage(), e);
				}
				initialAuthor = w.get(0).getAsJsonObject().getAsJsonPrimitive("user").getAsString();
			}
			lastRev = w.get(w.size() - 1).getAsJsonObject().getAsJsonPrimitive("revid").getAsInt() + 1;
		}


		for(Map.Entry<String, Integer> entry : tmp.entrySet()) {
			authorsAndCommits.add(new ArticleInfo.AuthorAndCommits(entry.getKey(), entry.getValue()));
		}

		/* set diffs in revisions */
		for(int i = 1; i < revisions.size(); i++) {
			revisions.get(i).setDiff(revisions.get(i).getBytes() - revisions.get(i - 1).getBytes());
		}

		/* get similiar articles */
		// http://de.wikipedia.org/w/api.php?action=query&format=xml&list=search&srsearch=Maus&srlimit=500
		final String similar = this.requester.getResult(
				API +
				"action=query&format=json&list=search&srlimit=500&srsearch=" + title
		);
		final JsonArray search = this.parser.parse(similar)
											.getAsJsonObject()
											.getAsJsonObject("query")
											.getAsJsonArray("search");

		final List<ArticleInfo.SimilarArticle> similarArticles =
				new ArrayList<ArticleInfo.SimilarArticle>(search.size());

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
			final String creationDateStr = this.requester.getResult(
					API + "action=query&format=json&prop=revisions&rvprop=timestamp&rvlimit=1&rvdir=newer&pageids="
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
								.getAsString()
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
		final String imageStr = this.requester.getResult(
				API + "action=query&format=json&prop=images&pageids=" + pageid
		);
		int numOfImages = this.parser.parse(imageStr)
				.getAsJsonObject()
				.getAsJsonObject("query")
				.getAsJsonObject("pages")
				.getAsJsonObject(pageid + "")
				.getAsJsonArray("images")
				.size();

		return new ArticleInfo(
				pageid,
				creationDate,
				initialAuthor,
				"http://de.wikipedia.org/wiki/" + title.replaceAll(" ", "_"),
				numOfImages,
				this.getCategories(pageid),
				revisions.get(0).getBytes(),
				authorsAndCommits,
				revisions,
				similarArticles
		);
	}

	public int getPageId(final String title) {
		final String convertedTitle = this.convertTitle(title);
		final String response = this.requester.getResult(
				API + "action=query&format=json&indexpageids&titles="
				+ convertedTitle
		);
		this.logger.debug("Response: " + response);
		return this.parser
				.parse(response)
				.getAsJsonObject()
				.getAsJsonObject("query")
				.getAsJsonArray("pageids")
				.get(0)
				.getAsInt();
	}

	private String convertTitle(String title) {
		final String convertedTitle = title.replaceAll(" ", "%20").replaceAll("&", "%26");
		this.logger.debug("Request 'pageid' for title '" + title + "' (converted to '" + convertedTitle + "')");
		return convertedTitle;
	}

	private String getCategories(int pageid) {
		/* get categories */
		final String categories = this.requester.getResult(
				API + "action=query&format=json&prop=categories&pageids=" + pageid
		);
		final JsonArray cats = this.parser.parse(categories)
										  .getAsJsonObject()
										  .getAsJsonObject("query")
										  .getAsJsonObject("pages")
										  .getAsJsonObject(pageid + "")
										  .getAsJsonArray("categories");
		final StringBuilder stringBuilder = new StringBuilder();
		for(JsonElement inner : cats) {
			stringBuilder.append(inner.getAsJsonObject().getAsJsonPrimitive("title").getAsString());
			stringBuilder.append("; ");
		}
		return stringBuilder.toString();
	}
}
