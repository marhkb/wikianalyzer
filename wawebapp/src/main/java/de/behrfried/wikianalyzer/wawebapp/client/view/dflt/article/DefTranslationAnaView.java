package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.inject.Inject;
import com.smartgwt.client.widgets.Label;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.TranslationAnaView;


public class DefTranslationAnaView extends TranslationAnaView{
	private final Presenter presenter;
	private final Messages messages;
	
	private Label test2;
	
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

		this.test2 = new Label("test2");
		this.addChild(test2);
	}
}
