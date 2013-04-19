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
