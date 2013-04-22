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
 
package de.behrfried.wikianalyzer.wawebapp.server;

import java.net.MalformedURLException;

import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.behrfried.wikianalyzer.wawebapp.client.GreetingService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	private static String ADDRESS = "http://de.wikipedia.org/w/";
	private static String USER = "Behrfried";
	private static String PSSWD = "!alien123";
	private static MediaWikiBot BOT;
	
	static {
		try {
			BOT = new MediaWikiBot(ADDRESS);
		} catch (MalformedURLException e) {
			System.exit(-1);
		}
	}
	
	public String greetServer(String input) throws IllegalArgumentException {
		MediaWikiBot b = null;
		try {
			if(!BOT.isLoggedIn()) {
				BOT.login(USER, PSSWD);
			}
			return Jsoup.parse(BOT.getPage(ADDRESS + "index.php?title=" + input))
					.getElementsByTag("body").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
