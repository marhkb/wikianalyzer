package de.behrfried.wikianalyzer.wawebapp.server.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

import de.behrfried.wikianalyzer.wawebapp.client.service.ArticleInfoService;
import de.behrfried.wikianalyzer.wawebapp.server.service.WikiAccess;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ShortArticleInfo;

@Singleton
public class ArticleInfoServiceImpl  extends RemoteServiceServlet implements ArticleInfoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(ArticleInfoServiceImpl.class);
	
	/**
	 * 
	 */
	private final WikiAccess wikiAccess;
	
	/**
	 * 
	 * @param wikiAccess
	 */
	public ArticleInfoServiceImpl(final WikiAccess wikiAccess) {
		this.wikiAccess = wikiAccess;
	}

	@Override
	public ShortArticleInfo getArticleInfo(String title) {
		this.logger.debug("");
		return null;
	}

}
