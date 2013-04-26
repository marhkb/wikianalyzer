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
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;

import de.behrfried.wikianalyzer.wawebapp.client.Messages;

public class DefaultArticleView extends ArticleView {	
	
	
	private final Presenter presenter;
	
	/**
	 * the parent element of the {@link DefaultArticleView} where the widgets
	 * has to be put in.
	 */
	
	private Label waLabel;
	private ComboBoxItem searchBox;
	private DynamicForm searchBoxContainer;
	private HTMLPane wikiPrevPanel;
	private HLayout searchLayout, generalWikiArticleLayout;
	private Button searchButton;
	private IMenuButton timeMenuButton;
	private Menu timeSpanMenu;
	private MenuItem randomSpan, daySpan, weekSpan, monthSpan, yearSpan, chooseSpan;
	private SectionStack sectionPanel;
	private SectionStackSection generalArticleSection, articleAnalyzationSection;
	private VLayout lay;
	
	private final Messages messages;

	@Inject
	public DefaultArticleView(Presenter presenter, Messages messages) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		this.presenter = presenter;
		this.messages = messages;
		
		this.init();
	}
	
	private void init() {
		this.waLabel = new Label("WIKIAnalyzer");
		this.waLabel.setHeight100();
		this.waLabel.setMargin(10);
		this.searchBox = new ComboBoxItem();
		this.searchBox.setWidth(200);
		this.searchBox.setShowTitle(false);
		this.searchBoxContainer = new DynamicForm();
		this.searchBoxContainer.setBackgroundColor("white");
		this.searchBoxContainer.setItems(this.searchBox);		
		this.searchButton = new Button(this.messages.searchButton());		
		this.searchLayout = new HLayout();
		this.searchLayout.setMembersMargin(3);
		this.searchLayout.setHeight(30);
		this.searchLayout.setBackgroundColor("red");
		this.searchLayout.addMember(waLabel);
		this.searchLayout.addMember(searchBoxContainer);
		this.searchLayout.addMember(searchButton);
		
		this.timeSpanMenu = new Menu();
		this.randomSpan = new MenuItem("random time");
		this.randomSpan.setChecked(true);
		this.timeSpanMenu.addItem(randomSpan);
		this.daySpan = new MenuItem("last day");
		this.timeSpanMenu.addItem(daySpan);
		this.weekSpan = new MenuItem("last week");
		this.timeSpanMenu.addItem(weekSpan);
		this.monthSpan = new MenuItem("last month");
		this.timeSpanMenu.addItem(monthSpan);
		this.yearSpan = new MenuItem("last year");
		this.timeSpanMenu.addItem(yearSpan);
		this.chooseSpan = new MenuItem("choose timespan");
		this.timeSpanMenu.addItem(chooseSpan);
		this.timeMenuButton = new IMenuButton(randomSpan.getTitle(), timeSpanMenu);
		
		
		this.generalArticleSection = new SectionStackSection();
		this.sectionPanel = new SectionStack();
		//TODO weitermachen: WIkivorschau, allgemeine wikiinfos...
		this.sectionPanel.setVisibilityMode(VisibilityMode.MULTIPLE);
		
		this.lay = new VLayout();
		
		this.lay.addMember(searchLayout);
		this.lay.addMember(timeMenuButton);
		
		this.addChild(this.lay);
	}


	public String getName() {
		return "Article";
	}
}
