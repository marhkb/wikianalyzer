package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.inject.Inject;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class DefaultArticleView implements ArticleView {	
	/**
	 * the parent element of the {@link DefaultArticleView} where the widgets
	 * has to be put in.
	 */
	private final StartPageView parentView;
	
	private Label waLabel;
	private DynamicForm formContainer;
	private ComboBoxItem searchBox;
	private HTMLPane wikiPrevPanel;
	private VLayout layoutContainer;
	private HLayout searchLayout;
	private Button searchButton;

	public void init() {
		this.waLabel = new Label();
		
		this.searchBox = new ComboBoxItem();
		this.searchButton = new Button("Search");
		
		this.formContainer = new DynamicForm();
		this.formContainer.setWidth(100);
		this.formContainer.setItems(searchBox);
		
		this.searchLayout = new HLayout();
		this.searchLayout.addChild(waLabel);
		this.searchLayout.addChild(formContainer);
		this.searchLayout.addChild(searchButton);
				
		this.parentView.getMainTabContainer().getTab(0).setPane(this.searchLayout);
	}

	@Inject
	public DefaultArticleView(StartPageView parentView) {
		this.parentView = parentView;
	}

	/**
	 * @see View
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	

}
