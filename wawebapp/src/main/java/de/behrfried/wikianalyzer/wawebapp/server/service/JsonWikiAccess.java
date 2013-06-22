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

import java.util.*;

public class JsonWikiAccess implements WikiAccess {

	private final static String API = "http://de.wikipedia.org/w/api.php?";
	private final Logger logger = LoggerFactory.getLogger(JsonWikiAccess.class);
	private final WikiApi requester;
	private final JsonParser parser = new JsonParser();

	@Inject
	public JsonWikiAccess(final WikiApi requester) {
		this.requester = requester;
	}

	@Override
	public ArticleInfo getArticleInfo(String title) {
		final int pageid = this.getPageId(title);

		/* get revisions of an article (max 500 are allowed?) */
		// http://de.wikipedia.org/w/api.php?action=query&format=xml&prop=revisions&pageids=88112&rvprop=user|ids|timestamp|sha1&rvlimit=10000&rvdiffto=next&rvdir=older
		final String response1 =
				this.requester.getResult(
						API + "action=query&format=json&prop=revisions&rvprop=user|ids" +
						"|timestamp&rvlimit=10000&rvdiffto=next&pageids=" + pageid
				);


		/* get similiar articles */
		// http://de.wikipedia.org/w/api.php?action=query&format=xml&list=search&srsearch=Maus&srlimit=500
		final String similiar = this.requester.getResult(
				API +
				"action=query&format=json&list=search&srlimit=500&srsearch="
				+ title
		);

		JsonArray w = this.parser.parse(response1)
								 .getAsJsonObject()
								 .getAsJsonObject("query")
								 .getAsJsonObject("pages")
								 .getAsJsonObject(pageid + "")
								 .getAsJsonArray("revisions");


		final List<ArticleInfo.AuthorAndCommits> authorsAndCommits = new ArrayList<ArticleInfo.AuthorAndCommits>();
		final List<ArticleInfo.Revision> revisions = new ArrayList<ArticleInfo.Revision>();

		final Map<String, Integer> tmp = new HashMap<String, Integer>();
		for(JsonElement obj : w) {

			//{"revid":69797531,"parentid":69352711,"user":"80.143.242.119","anon":"","timestamp":"2010-01-25T18:38:06Z","diff":{"notcached":""}}
			final JsonObject jsonObj = obj.getAsJsonObject();
			final String author = jsonObj.getAsJsonPrimitive("user").getAsString();
			if(!tmp.containsKey(author)) {
				tmp.put(author, 1);
			} else {
				tmp.put(author, tmp.get(author) + 1);
			}

			revisions.add(new ArticleInfo.Revision(
					jsonObj.getAsJsonPrimitive("revid").getAsInt(),
					jsonObj.getAsJsonPrimitive("parentid").getAsInt(),
					null, author,
					0,
					""
			));

			this.logger.info(author);
		}

		for(Map.Entry<String, Integer> entry : tmp.entrySet()) {
			authorsAndCommits.add(new ArticleInfo.AuthorAndCommits(entry.getKey(), entry.getValue()));
		}

		final Date creationDate;
		final String initialAuthor = "";

		ArticleInfo result = new ArticleInfo();
		result.setAuthorsAndCommits(authorsAndCommits);
		result.setRevisions(revisions);
		result.setInitialAuthor("root");
		return result;//new ArticleInfo(null, pageid, "", authorsAndCommits, null, null, null);
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
		final String convertedTitle = title.replaceAll(" ", "%20");
		this.logger.debug("Request 'pageid' for title '" + title + "' (converted to '" + convertedTitle + "')");
		return convertedTitle;
	}
}
