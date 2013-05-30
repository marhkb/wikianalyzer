package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.RevisionAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;


public class DefRevisionAnaView extends RevisionAnaView{
	private final Presenter presenter;
	private final Messages messages;
	@Inject
	public DefRevisionAnaView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
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
