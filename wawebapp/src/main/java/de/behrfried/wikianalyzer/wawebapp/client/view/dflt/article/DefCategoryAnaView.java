package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.CategoryAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;


public class DefCategoryAnaView extends CategoryAnaView{
	private final Presenter presenter;
	private final Messages messages;
	@Inject
	public DefCategoryAnaView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
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
