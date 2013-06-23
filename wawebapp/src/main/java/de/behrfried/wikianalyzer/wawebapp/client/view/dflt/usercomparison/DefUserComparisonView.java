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

package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.usercomparison;

import com.google.inject.Inject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user.DefUserView;
import de.behrfried.wikianalyzer.wawebapp.client.view.usercomparison.UserComparisonView;

/**
 * Default implementation for {@link UserComparisonView}.
 * 
 * @author marcus
 * 
 */
public class DefUserComparisonView extends UserComparisonView {
	private Label waLabel, genUsrInfLabel, usrAnaLabel, userOneLabel, userTwoLabel;
	private ComboBoxItem searchBox1, searchBox2;
	private DynamicForm searchBoxOneContainer, searchBoxTwoContainer;
	private ListGrid generalUsrInfoGrid;
	private ListGridField usrAttributeColumn, usrValueColumn;
	private HLayout searchLayout, usrInfoAnalyzationLayout, searchInfoLayout;
	private VLayout siteLayoutContainer, genUsrInfLayout, usrAnaLayout;
	private Button searchButton;
	private IMenuButton timeMenuButton;
	private Menu timeSpanMenu;
	private MenuItem randomSpan, hourSpan, daySpan, weekSpan, monthSpan, yearSpan, chooseSpan;
	/**
	 * parent view of this {@link DefUserComparisonView}
	 */
	private final Presenter presenter;/**
	 * {@link DefUserView}'s parent element
	 */
	private final Messages messages;

	/**
	 * Creates an instance of {@link DefUserComparisonView}. All arguments
	 * are injected by Gin
	 * 
	 * @param parentView
	 * @throws IllegalArgumentException
	 */
	@Inject
	public DefUserComparisonView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		if(messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;
		this.init();
	}
	
	private void init() {
		this.waLabel = new Label("WIKIAnalyzer");
		this.waLabel.setHeight100();
		this.waLabel.setMargin(10);
		
		this.userOneLabel = new Label("1. Usernamen:");
		this.userOneLabel.setWidth(100);
		this.userOneLabel.setAlign(Alignment.RIGHT);
		this.searchBox1 = new ComboBoxItem();
		this.searchBox1.setWidth(150);
		this.searchBox1.setShowTitle(false);
		this.searchBoxOneContainer = new DynamicForm();
		this.searchBoxOneContainer.setBackgroundColor("white");
		this.searchBoxOneContainer.setItems(this.searchBox1);

		this.userTwoLabel = new Label(" vergleichen mit 2. Usernamen: ");
		this.userTwoLabel.setWidth(150);
		this.searchBox2 = new ComboBoxItem();
		this.searchBox2.setWidth(150);
		this.searchBox2.setShowTitle(false);
		this.searchBoxTwoContainer = new DynamicForm();
		this.searchBoxTwoContainer.setBackgroundColor("white");
		this.searchBoxTwoContainer.setItems(this.searchBox2);
		
		this.searchButton = new Button(this.messages.searchButton());
		this.searchLayout = new HLayout();
		this.searchLayout.setMembersMargin(3);
		this.searchLayout.setHeight(30);

		this.searchLayout.addMembers(this.waLabel, this.userOneLabel, this.searchBoxOneContainer, this.userTwoLabel, this.searchBoxTwoContainer, this.searchButton);
		this.searchLayout.setMembersMargin(10);

		this.timeSpanMenu = new Menu();
		this.randomSpan = new MenuItem("random time");
		this.randomSpan.setChecked(true);
		this.timeSpanMenu.addItem(this.randomSpan);
		this.hourSpan = new MenuItem("last hour");
		this.timeSpanMenu.addItem(this.hourSpan);
		this.daySpan = new MenuItem("last day");
		this.timeSpanMenu.addItem(this.daySpan);
		this.weekSpan = new MenuItem("last week");
		this.timeSpanMenu.addItem(this.weekSpan);
		this.monthSpan = new MenuItem("last month");
		this.timeSpanMenu.addItem(this.monthSpan);
		this.yearSpan = new MenuItem("last year");
		this.timeSpanMenu.addItem(this.yearSpan);
		this.chooseSpan = new MenuItem("choose timespan");
		this.timeSpanMenu.addItem(this.chooseSpan);
		this.timeMenuButton = new IMenuButton(this.randomSpan.getTitle(), this.timeSpanMenu);
		
		
		
		this.siteLayoutContainer = new VLayout();
		this.siteLayoutContainer.addMembers(this.searchLayout, this.timeMenuButton);
		this.addChild(this.siteLayoutContainer);
	}

	@Override
	public String getName() {
		return "User Comparison";
	}
}
