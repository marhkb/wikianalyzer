package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.article;

import com.google.inject.Inject;
import com.smartgwt.client.widgets.Label;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.ArticleView.Presenter;
import de.behrfried.wikianalyzer.wawebapp.client.view.article.AuthorAnaView;


public class DefAuthorAnaView extends AuthorAnaView{
	private final Presenter presenter;
	private final Messages messages;
	private Label test1;
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
		this.test1 = new Label("test1");
		this.addChild(test1);
	}
}
