package de.behrfried.wikianalyzer.wawebapp.client.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;

@RemoteServiceRelativePath("articleInfo")
public interface ArticleInfoService {
	
	ArticleInfo getArticleInfo(final String title);
}
