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

import com.google.gson.JsonParser;
import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ShortArticleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonWikiAccess implements WikiAccess {

	private final Logger logger = LoggerFactory.getLogger(JsonWikiAccess.class);

	private final WikiApi requester;

	private final JsonParser parser = new JsonParser();

	@Inject
	public JsonWikiAccess(final WikiApi requester) {
		this.requester = requester;
	}
	
	@Override
	public ShortArticleInfo getShortArticleInfo(String title) {
		final String convertedTitle = this.convertTitle(title);
		return null;
	}

	public int getPageId(final String title) {
		final String convertedTitle = this.convertTitle(title);
		final String response = this.requester.getResult("http://de.wikipedia.org/w/api.php?action=query&format=json&indexpageids&titles="
		        + convertedTitle);
		this.logger.debug("Response: " + response);
		return this.parser.parse(response).getAsJsonObject().getAsJsonObject("query").getAsJsonArray("pageids").get(0).getAsInt();
	}
	
	private String convertTitle(String title) {
		final String convertedTitle = title.replaceAll(" ", "%20");
		this.logger.debug("Request 'pageid' for title '" + title + "' (converted to '" + convertedTitle + "')");
		return convertedTitle;
	}
}
