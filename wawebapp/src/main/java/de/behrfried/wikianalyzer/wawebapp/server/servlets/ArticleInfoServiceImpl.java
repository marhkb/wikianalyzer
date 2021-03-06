package de.behrfried.wikianalyzer.wawebapp.server.servlets;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.behrfried.wikianalyzer.wawebapp.client.service.ArticleInfoService;
import de.behrfried.wikianalyzer.wawebapp.server.service.WikiAccess;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	@Inject
	public ArticleInfoServiceImpl(final WikiAccess wikiAccess) {
		this.wikiAccess = wikiAccess;
	}

	@Override
	public ArticleInfo getArticleInfo(String title) {
		return new ArticleInfo();
	}
}