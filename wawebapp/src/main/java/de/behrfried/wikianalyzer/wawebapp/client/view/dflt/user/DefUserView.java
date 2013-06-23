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

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.util.data.Tuple2;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.util.list.ListChangedEventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.list.ListChangedEventArgs.ListChangedAction;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserView;

/**
 * Default implementation of {@link UserView}.
 * 
 * @author marcus
 * 
 */
public class DefUserView extends UserView {

	private final Presenter presenter;

	/**
	 * {@link DefUserView}'s parent element
	 */
	private final Messages messages;

	private Label waLabel, genUsrInfLabel, usrAnaLabel;
	private ComboBoxItem searchBox;
	private DynamicForm searchBoxContainer;
	private ListGrid generalUsrInfoGrid;
	private ListGridField usrAttributeColumn, usrValueColumn;
	private HLayout searchLayout, usrInfoAnalyzationLayout, searchInfoLayout;
	private VLayout siteLayoutContainer, genUsrInfLayout, usrAnaLayout;
	private Button searchButton;

	/**
	 * Creates an instance of {@link DefUserView}. All arguments are
	 * injected by Gin.
	 * 
	 * @param parentView
	 */
	@Inject
	public DefUserView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
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
		this.searchLayout.addMember(this.waLabel);
		this.searchLayout.addMember(this.searchBoxContainer);
		this.searchLayout.addMember(this.searchButton);

		this.usrAttributeColumn = new ListGridField("Attribute");
		this.usrAttributeColumn.setCanEdit(false);
		this.usrValueColumn = new ListGridField("Value");
		this.usrValueColumn.setCanEdit(false);
		this.generalUsrInfoGrid = new ListGrid();
		this.generalUsrInfoGrid.setFields(this.usrAttributeColumn, this.usrValueColumn);

		
		this.genUsrInfLabel = new Label("Allgemeine User Infos");
		this.genUsrInfLabel.setHeight(10);
		this.genUsrInfLabel.setWidth100();
		this.genUsrInfLayout = new VLayout();
		this.genUsrInfLayout.setWidth("25%");
		this.genUsrInfLayout.setHeight100();
		this.genUsrInfLayout.addMembers(this.genUsrInfLabel, this.generalUsrInfoGrid);

		this.usrAnaLabel = new Label("User Analysen");
		this.usrAnaLabel.setHeight(10);
		this.usrAnaLabel.setWidth100();
		this.usrAnaLayout = new VLayout();
		this.usrAnaLayout.setWidth("75%");
		this.usrAnaLayout.addMembers(this.usrAnaLabel, new DefUserAnaView(presenter, messages));

		this.usrInfoAnalyzationLayout = new HLayout();
		this.usrInfoAnalyzationLayout.addMembers(this.genUsrInfLayout, this.usrAnaLayout);

		this.siteLayoutContainer = new VLayout();
		this.siteLayoutContainer.setWidth100();
		this.siteLayoutContainer.setHeight100();
		this.siteLayoutContainer.addMembers(this.searchLayout, this.usrInfoAnalyzationLayout);

		this.addChild(this.siteLayoutContainer);

		this.bind();
	}

	private void bind() {
		// this.bindGeneralInfoGrid();
		// this.bindSearchBox();
		// this.bindSearchButton();

		/*
		 * final DataContainer<Boolean> textItemNameToServerSelfChanged = new
		 * DataContainer<Boolean>(false);
		 * this.textItem.setValue(this.presenter.getNameToServer());
		 * this.textItem.addChangedHandler(new ChangedHandler() {
		 * 
		 * public void onChanged(final ChangedEvent event) {
		 * textItemNameToServerSelfChanged.setValue(true);
		 * DefaultUserView.this.presenter
		 * .setNameToServer(DefaultUserView.this.textItem.getValueAsString());
		 * textItemNameToServerSelfChanged.setValue(false); } });
		 * 
		 * this.presenter.getNameToServerChanged().addHandler(new
		 * Handler<GenericEventArgs<String>>() {
		 * 
		 * public void invoke(final Object sender, final
		 * GenericEventArgs<String> e) {
		 * DefaultUserView.this.textItem.setValue(e.getValue()); } });
		 * 
		 * final DataContainer<Boolean> textItem2NameToServerSelfChanged = new
		 * DataContainer<Boolean>(false);
		 * this.textItem2.setValue(this.presenter.getNameToServer());
		 * this.textItem2.addChangedHandler(new ChangedHandler() {
		 * 
		 * public void onChanged(final ChangedEvent event) {
		 * textItem2NameToServerSelfChanged.setValue(true);
		 * DefaultUserView.this.
		 * presenter.setNameToServer(DefaultUserView.this.textItem2
		 * .getValueAsString());
		 * textItem2NameToServerSelfChanged.setValue(false); } });
		 * 
		 * this.presenter.getNameToServerChanged().addHandler(new
		 * Handler<GenericEventArgs<String>>() {
		 * 
		 * public void invoke(final Object sender, final
		 * GenericEventArgs<String> e) {
		 * DefaultUserView.this.textItem2.setValue(e.getValue()); } });
		 * 
		 * this.presenter.getErrorNameToServerChanged().addHandler(new
		 * Handler<GenericEventArgs<String>>() {
		 * 
		 * public void invoke(final Object sender, final
		 * GenericEventArgs<String> e) { if(e.getValue().length() == 0) { //
		 * textItem. } } });
		 * 
		 * this.button.setDisabled(!this.presenter.getSendCommand().canExecute(
		 * null));
		 * this.presenter.getSendCommand().canExecuteChanged().addHandler(new
		 * Handler<EventArgs>() {
		 * 
		 * public void invoke(final Object sender, final EventArgs e) {
		 * DefaultUserView
		 * .this.button.setDisabled(!DefaultUserView.this.presenter
		 * .getSendCommand().canExecute(null)); } });
		 * this.button.addClickHandler(new ClickHandler() {
		 * 
		 * public void onClick(final ClickEvent event) {
		 * DefaultUserView.this.presenter.getSendCommand().execute(null); } });
		 */
	}

	private void bindSearchBox() {
		this.searchBox.setValue(this.presenter.getUsrName());
		this.searchBox.addChangedHandler(new ChangedHandler() {

			public void onChanged(final ChangedEvent event) {
				DefUserView.this.presenter.setUserName(DefUserView.this.searchBox.getValueAsString());
			}
		});
		this.presenter.usrNameChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				if(!DefUserView.this.searchBox.equals(DefUserView.this.presenter.getUsrName())) {
					DefUserView.this.searchBox.setValue(DefUserView.this.presenter.getUsrName());
				}
			}
		});
		this.searchBox.addKeyUpHandler(new KeyUpHandler() {

			public void onKeyUp(final KeyUpEvent event) {
				if(event.getKeyName().equals("Enter")) {
					if(DefUserView.this.presenter.getSendCommand().canExecute(null)) {
						DefUserView.this.presenter.getSendCommand().execute(null);
					}
				}
			}
		});
	}

	private void bindSearchButton() {
		this.searchButton.setDisabled(!this.presenter.getSendCommand().canExecute(null));
		this.presenter.getSendCommand().canExecuteChanged().addHandler(new Handler<EventArgs>() {

			public void invoke(final Object sender, final EventArgs e) {
				DefUserView.this.searchButton.setDisabled(!DefUserView.this.presenter.getSendCommand().canExecute(null));
			}
		});
		this.searchButton.addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {
				DefUserView.this.presenter.getSendCommand().execute(null);
			}
		});
	}

	private void bindGeneralInfoGrid() {
		final Map<Tuple2<String, String>, Record> recordsO = new HashMap<Tuple2<String, String>, Record>();
		for(final Tuple2<String, String> t : this.presenter.getUsrInfos()) {
			final ListGridRecord lsg = new ListGridRecord();
			lsg.setAttribute("Attribute", t.getItem1());
			lsg.setAttribute("Value", t.getItem2());
			this.generalUsrInfoGrid.addData(lsg);
			recordsO.put(t, lsg);
		}
		this.presenter.getUsrInfos().listChanged().addHandler(new Handler<ListChangedEventArgs<Tuple2<String, String>>>() {

			public void invoke(final Object sender, final ListChangedEventArgs<Tuple2<String, String>> e) {
				if(e.getListChangedAction() == ListChangedAction.ADD_REMOVE) {
					if(e.getOldItems() != null) {
						for(final Tuple2<String, String> t : e.getOldItems()) {
							DefUserView.this.generalUsrInfoGrid.removeData(recordsO.remove(t));
						}
					}

					if(e.getNewItems() != null) {
						for(final Tuple2<String, String> t : e.getNewItems()) {
							final ListGridRecord lsg = new ListGridRecord();
							lsg.setAttribute("Attribute", t.getItem1());
							lsg.setAttribute("Value", t.getItem2());
							DefUserView.this.generalUsrInfoGrid.addData(lsg);
							recordsO.put(t, lsg);
						}
					}
				} else {
					DefUserView.this.generalUsrInfoGrid.clear();
					recordsO.clear();
				}
			}
		});
	}

	@Override
	public String getName() {
		return "User";
	}
}
