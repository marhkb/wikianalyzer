package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.AuthorAnaView;


public class DefAuthorAnaView extends AuthorAnaView{
	private final Presenter presenter;
	private final Messages messages;
	@Inject
	public DefAuthorAnaView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		this.presenter = presenter;
		this.messages = messages;

		this.init();
	}

	private void init() {
		
	}
}
