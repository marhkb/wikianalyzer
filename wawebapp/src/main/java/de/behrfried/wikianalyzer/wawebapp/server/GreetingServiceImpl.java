package de.behrfried.wikianalyzer.wawebapp.server;

import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import de.behrfried.wikianalyzer.wawebapp.client.GreetingService;
import de.behrfried.wikianalyzer.wawebapp.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.behrfried.wikianalyzer.simplemath.*;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		MediaWikiBot b = null;
		try {
		b = new MediaWikiBot("http://de.wikipedia.org/w/");
		b.login("Behrfried", "!alien123");
		String result = b.readData(input).getText();
		if(result.length() > 500) {
			result = result.substring(0, 500);
		}
		return result;
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
