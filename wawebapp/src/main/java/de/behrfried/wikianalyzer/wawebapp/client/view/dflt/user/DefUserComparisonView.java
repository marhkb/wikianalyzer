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

package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.user;

import com.google.inject.Inject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserComparisonView;

/**
 * Default implementation for {@link UserComparisonView}.
 * 
 * @author marcus
 * 
 */
public class DefUserComparisonView extends UserComparisonView {

	private Label waLabel, userOneLabel, userTwoLabel;
	private ComboBoxItem searchBox1, searchBox2;

	private DynamicForm searchBoxOneContainer, searchBoxTwoContainer;

	private HLayout searchLayout;
	private VLayout siteLayoutContainer;
	private Button searchButton;
	private DefUserComparisonAnaView userComparisonAnaView;
	/**
	 * parent view of this {@link DefUserComparisonView}
	 */
	private final Presenter presenter;
	/**
	 * {@link DefUserView}'s parent element
	 */
	private final Messages messages;

	/**
	 * Creates an instance of {@link DefUserComparisonView}. All arguments are
	 * injected by Gin
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
		this.bind();
	}

	private void init() {
		this.waLabel = new Label("WIKIAnalyzer");
		this.waLabel.setHeight100();
		this.waLabel.setMargin(10);

		this.userOneLabel = new Label("Nutzer 1:");
		this.userOneLabel.setWidth(100);
		this.userOneLabel.setAlign(Alignment.RIGHT);
		this.searchBox1 = new ComboBoxItem();
		this.searchBox1.setWidth(150);
		this.searchBox1.setShowTitle(false);
		this.searchBoxOneContainer = new DynamicForm();
		this.searchBoxOneContainer.setBackgroundColor("white");
		this.searchBoxOneContainer.setItems(this.searchBox1);

		this.userTwoLabel = new Label(" vergleichen mit 2. Nutzer: ");
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

		this.searchLayout.addMembers(this.waLabel, this.userOneLabel, this.searchBoxOneContainer, this.userTwoLabel, this.searchBoxTwoContainer,
		        this.searchButton);
		this.searchLayout.setMembersMargin(10);

		this.siteLayoutContainer = new VLayout();
		this.userComparisonAnaView = new DefUserComparisonAnaView(this.presenter, this.messages);
		this.userComparisonAnaView.setAlign(Alignment.CENTER);
		this.userComparisonAnaView.setWidth("80%");
		this.siteLayoutContainer.addMember(this.searchLayout);
		this.siteLayoutContainer.addMember(this.userComparisonAnaView);
		this.siteLayoutContainer.setWidth100();
		this.siteLayoutContainer.setHeight100();
		this.addChild(this.siteLayoutContainer);
	}
	
	private void bind() {
		this.bindSearchButton();
		this.bindSearchBox1();
		this.bindSearchBox2();
	}

	private void bindSearchBox1() {
		this.searchBox1.setValue(this.presenter.getUserName1());
		this.searchBox1.addChangedHandler(new ChangedHandler() {

			public void onChanged(final ChangedEvent event) {
				presenter.setUserName1(searchBox1.getValueAsString());
			}
		});
		this.presenter.userName1Changed().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				if(!DefUserComparisonView.this.searchBox1.equals(DefUserComparisonView.this.presenter.getUserName1())) {
					searchBox1.setValue(presenter.getUserName1());
				}
			}
		});
		this.searchBox1.addKeyUpHandler(new KeyUpHandler() {

			public void onKeyUp(final KeyUpEvent event) {
				if(event.getKeyName() != null && event.getKeyName().equals("Enter")) {
					if(DefUserComparisonView.this.presenter.getSendCommand().canExecute(null)) {
						DefUserComparisonView.this.presenter.getSendCommand().execute(false);
					}
				}
			}
		});

		this.searchBox1.setValueMap(this.presenter.getUser1Suggestions());
		this.presenter.user1SuggestionsChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				DefUserComparisonView.this.searchBox1.setValueMap(DefUserComparisonView.this.presenter.getUser1Suggestions());
				DefUserComparisonView.this.searchBox1.showPicker();

			}
		});
	}
	
	private void bindSearchBox2() {
		this.searchBox2.setValue(this.presenter.getUserName2());
		this.searchBox2.addChangedHandler(new ChangedHandler() {

			public void onChanged(final ChangedEvent event) {
				presenter.setUserName2(searchBox2.getValueAsString());
			}
		});
		this.presenter.userName2Changed().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				if(!DefUserComparisonView.this.searchBox2.equals(DefUserComparisonView.this.presenter.getUserName2())) {
					searchBox2.setValue(presenter.getUserName2());
				}
			}
		});
		this.searchBox2.addKeyUpHandler(new KeyUpHandler() {

			public void onKeyUp(final KeyUpEvent event) {
				if(event.getKeyName() != null && event.getKeyName().equals("Enter")) {
					if(DefUserComparisonView.this.presenter.getSendCommand().canExecute(null)) {
						DefUserComparisonView.this.presenter.getSendCommand().execute(false);
					}
				}
			}
		});

		this.searchBox2.setValueMap(this.presenter.getUser2Suggestions());
		this.presenter.user2SuggestionsChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				DefUserComparisonView.this.searchBox2.setValueMap(DefUserComparisonView.this.presenter.getUser2Suggestions());
				DefUserComparisonView.this.searchBox2.showPicker();

			}
		});
	}

	private void bindSearchButton() {
		this.searchButton.
		setDisabled(!this.presenter.
				getSendCommand().canExecute(null));
		this.presenter.getSendCommand().canExecuteChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				searchButton.setDisabled(!presenter.getSendCommand().canExecute(null));
			}
		});
		this.searchButton.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				presenter.getSendCommand().execute(null);
			}
		});
	}

	@Override
	public String getName() {
		return "User Comparison";
	}
}
