package de.behrfried.wikianalyzer.wawebapp.client.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.behrfried.wikianalyzer.wawebapp.shared.article.ShortArticleInfo;

@RemoteServiceRelativePath("articleInfo")
public interface ArticleInfoService {
	
	ShortArticleInfo getArticleInfo(final String title);
}
