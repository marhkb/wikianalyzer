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

/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.behrfried.wikianalyzer.wawebapp.client.view.dflt;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import de.behrfried.wikianalyzer.wawebapp.client.view.ArticleView;
import de.behrfried.wikianalyzer.wawebapp.client.view.ShellView;
import de.behrfried.wikianalyzer.wawebapp.client.view.UserView;
import de.behrfried.wikianalyzer.wawebapp.client.view.ShellView.Presenter;

/**
 * Default implementation of {@link ShellView}
 * 
 * @author marcus
 * 
 */
public class DefaultShellView extends ShellView {

	private final Presenter presenter;

	private final ArticleView articleView;
	private final UserView userView;

	private final TabSet tabSet;

	@Inject
	public DefaultShellView(final Presenter presenter, final ArticleView articleView, final UserView userView) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		this.presenter = presenter;
		this.articleView = articleView;
		this.userView = userView;

		/* init other tabs */
		final Tab tabArticle = new Tab(this.articleView.getName());
		tabArticle.setID(this.articleView.getName());
		tabArticle.setPane(this.articleView);

		final Tab tabUser = new Tab(this.userView.getName());
		tabUser.setID(this.userView.getName());
		tabUser.setPane(this.userView);

		this.tabSet = new TabSet();

		this.tabSet.addTab(tabArticle);
		this.tabSet.addTab(tabUser);

		this.tabSet.setWidth100();
		this.tabSet.setHeight100();

		this.setWidth100();
		this.setHeight100();

		this.addChild(this.tabSet);

	}

	@Override
	public String getName() {
		return "TabContainer";
	}

	@Override
	public void postConstruct() {

		final String initToken = History.getToken();
		if(initToken.length() == 0) {
			History.newItem(this.tabSet.getTab(0).getID());
		}

		this.tabSet.addTabSelectedHandler(new TabSelectedHandler() {

			public void onTabSelected(final TabSelectedEvent event) {
				History.newItem(event.getID());
			}
		});

		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			public void onValueChange(final ValueChangeEvent<String> event) {
				final String historyToken = event.getValue();
				if(historyToken.isEmpty()) {
					History.back();
				}
				DefaultShellView.this.tabSet.selectTab(event.getValue());
			}
		});
		History.fireCurrentHistoryState();
	}
}
