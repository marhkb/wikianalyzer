package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.inject.Inject;
import com.smartgwt.client.widgets.Label;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.TranslationAnaView;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;


public class DefTranslationAnaView extends TranslationAnaView{
	private final Presenter presenter;
	private final Messages messages;
	
	private Label todo;
	
	@Inject
	public DefTranslationAnaView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
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
