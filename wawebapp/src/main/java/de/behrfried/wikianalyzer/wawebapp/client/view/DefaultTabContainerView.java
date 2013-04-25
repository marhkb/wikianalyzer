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

package de.behrfried.wikianalyzer.wawebapp.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.ShellPresenter;

/**
 * Default implementation of {@link ShellView}
 * 
 * @author marcus
 * 
 */
public class DefaultTabContainerView extends ShellView {
	
	private ShellPresenter presenter;
	
	private final ArticleView articleView;
	private final UserView userView;
	
	private TabSet tabSet;
	
	@Inject
	public DefaultTabContainerView(ShellPresenter presenter, ArticleView articleView, UserView userView) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		this.presenter = presenter;
		this.articleView = articleView;
		this.userView = userView;
		
		/* init other tabs */
		
		final Tab tabArticle = new Tab(this.articleView.getName());
		tabArticle.setPane(this.articleView);
		
		final Tab tabUser = new Tab(this.userView.getName());
		tabUser.setPane(this.userView);
		
		this.tabSet = new TabSet();
		
		this.tabSet.addTab(tabArticle);
		this.tabSet.addTab(tabUser);
		
		this.tabSet.setWidth("99%");
		this.tabSet.setHeight("97.8%");
		
		this.setWidth("99%");
		this.setHeight("97.8%");
		
		this.addChild(this.tabSet);
	}

	public String getName() {
		return "TabContainer";
	}
}
