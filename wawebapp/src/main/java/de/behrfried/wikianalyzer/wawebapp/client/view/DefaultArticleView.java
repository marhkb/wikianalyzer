/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
 
package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.inject.Inject;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

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
	private Tab articleTab;

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
		
		this.articleTab = new Tab();
		this.articleTab.setTitle("Article");
		this.articleTab.setPane(this.searchLayout);
				
		this.parentView.getMainTabContainer().addTab(this.articleTab);
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
