package de.behrfried.wikianalyzer.wawebapp.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonParser;
import com.google.inject.Inject;

public class JsonWikiAccess implements WikiAccess {

	private final Logger logger = LoggerFactory.getLogger(JsonWikiAccess.class);

	private final WikiApi requester;

	private final JsonParser parser = new JsonParser();

	@Inject
	public JsonWikiAccess(WikiApi requester) {
		this.requester = requester;
	}

	public int getPageId(final String title) {
		final String convertedTitle = title.replaceAll(" ", "%20");
		this.logger.debug("Request 'pageid' for title '" + title + "' (converted to '" + convertedTitle + "')");
		final String response = this.requester.getResult("http://de.wikipedia.org/w/api.php?action=query&format=json&indexpageids&titles=" + convertedTitle);
		this.logger.debug("Response: " + response);
		return this.parser.parse(response)
		        .getAsJsonObject().getAsJsonObject("query").getAsJsonArray("pageids").get(0).getAsInt();
	}
}
