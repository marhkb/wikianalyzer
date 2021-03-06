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

import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.exception.CriterionNotFoundException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.UserForComparisonNotExistException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.UserNotExistException;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.CriterionInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.TitleOrCategory;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserComparisonInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.List;

public class XmlWikiAccess implements WikiAccess {

	private final Logger logger = LoggerFactory.getLogger(XmlWikiAccess.class);

	private final WikiApi requester;

	private final DocumentBuilder builder;
	private final XPathFactory xPathfactory;

	@Inject
	public XmlWikiAccess(final WikiApi requester) {
		this.requester = requester;
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch(final ParserConfigurationException e) {
			this.logger.error(e.getMessage(), e);
		}
		this.builder = builder;
		this.xPathfactory = XPathFactory.newInstance();
	}

	public int getPageId(final String title) {
		final String convertedTitle = title.replaceAll(" ", "%20");
		this.logger.debug("Request 'pageid' for title '" + title + "' (converted to '" + convertedTitle + "')");
		final String response = this.requester.getResult("http://de.wikipedia.org/w/api.php?action=query&format=xml&indexpageids&titles="
		        + convertedTitle);
		this.logger.debug("Response: " + response);
		try {
			final InputSource is = new InputSource(new StringReader(response));

			final Document doc = this.builder.parse(is);

			final XPath xpath = this.xPathfactory.newXPath();
			final XPathExpression expr = xpath.compile("//page[1]");

			final Node result = (Node)expr.evaluate(doc, XPathConstants.NODE);

			return Integer.parseInt(result.getAttributes().getNamedItem("pageid").getNodeValue());
		} catch(final Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return -1;
	}
	
	
	@Override
	public ArticleInfo getArticleInfo(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public UserInfo getUserInfo(String userName) throws UserNotExistException {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public UserComparisonInfo getUserComparisonInfo(String userName1, String userName2) throws UserForComparisonNotExistException {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
	public CriterionInfo getCriterionInfo(List<TitleOrCategory> titlesOrCategories) throws
			CriterionNotFoundException {
		return null;
	}
}
