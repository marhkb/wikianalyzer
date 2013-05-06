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

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import com.google.inject.Inject;

public class XmlWikiAccess implements WikiAccess {

	private final Logger logger = LoggerFactory.getLogger(XmlWikiAccess.class);

	private final WikiApi requester;
	
	private final DocumentBuilder builder;
	private final XPathFactory xPathfactory;
	
	@Inject
	public XmlWikiAccess(WikiApi requester) {
		this.requester = requester;
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = null;
	    try {
	        builder = factory.newDocumentBuilder();
        } catch(ParserConfigurationException e) {
        	this.logger.error(e.getMessage(), e);
        }
	    this.builder = builder;
		this.xPathfactory = XPathFactory.newInstance();
	}

	
	public int getPageId(String title) {
		final String convertedTitle = title.replaceAll(" ", "%20");
		this.logger.debug("Request 'pageid' for title '" + title + "' (converted to '" + convertedTitle + "')");
		final String response =
				this.requester.getResult("http://de.wikipedia.org/w/api.php?action=query&format=xml&indexpageids&titles=" + convertedTitle);
		this.logger.debug("Response: " + response);
		try {
			InputSource is = new InputSource(new StringReader(response));
			
			Document doc = builder.parse(is);
			
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("//page[1]");
			
			Node result = (Node)expr.evaluate(doc, XPathConstants.NODE);
				
			return Integer.parseInt(result.getAttributes().getNamedItem("pageid").getNodeValue());
		} catch(Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return -1;
	}

}
