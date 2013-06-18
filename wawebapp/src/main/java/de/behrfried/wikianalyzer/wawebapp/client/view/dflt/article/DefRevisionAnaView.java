package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.inject.Inject;
import com.smartgwt.client.widgets.Label;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.RevisionAnaView;


public class DefRevisionAnaView extends RevisionAnaView{
	private final Presenter presenter;
	private final Messages messages;
	private Label test3;
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
		this.test3 = new Label("test3");
		this.addChild(test3);
		
	}
}
