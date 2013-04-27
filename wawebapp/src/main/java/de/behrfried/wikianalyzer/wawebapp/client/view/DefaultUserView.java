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
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

import de.behrfried.wikianalyzer.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.engine.CommandManager;
import de.behrfried.wikianalyzer.wawebapp.client.engine.CommandManager.GenericGetter;
import de.behrfried.wikianalyzer.wawebapp.client.event.GenericEventArgs;

/**
 * Default implementation of {@link UserView}.
 * 
 * @author marcus
 * 
 */
public class DefaultUserView extends UserView {

	private final Presenter presenter;

	/**
	 * {@link DefaultUserView}'s parent element
	 */
	private final Messages messages;

	private VLayout vLayout;
	private TextAreaItem textItem;
	private Button button;

	/**
	 * Creates an instance of {@link DefaultUserView}. All arguments are
	 * injected by Gin.
	 * 
	 * @param parentView
	 */
	@Inject
	public DefaultUserView(final Presenter presenter, final Messages messages)
			throws IllegalArgumentException {
		if (messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;

		this.textItem = new TextAreaItem();
		this.textItem.setWidth("*");
		this.textItem.setHeight("*");
		this.textItem.setShowTitle(false);
		this.button = new Button("Send");
		this.vLayout = new VLayout(5);

		DynamicForm form = new DynamicForm();
		form.setGroupTitle("Text");
		form.setWidth(500);
		form.setHeight(180);
		form.setFields(textItem);

		// this.vLayout.addChild(dForm);
		this.vLayout.addMember(form);
		this.vLayout.addMember(button);

		this.vLayout.setWidth100();
		this.vLayout.setHeight100();

		this.addChild(this.vLayout);

		this.setWidth100();
		this.setHeight100();

		this.bind();
	}

	private void bind() {
		
		this.textItem.setValue(this.presenter.getNameToServer());
		this.textItem.addChangedHandler(new ChangedHandler() {		
			public void onChanged(ChangedEvent event) {
				presenter.setNameToServer(textItem.getValueAsString());
			}
		});
		
		this.presenter.getNameToServerChanged().addHandler(new Handler<GenericEventArgs<String>>() {		
			public void invoke(Object sender, GenericEventArgs<String> e) {
				textItem.setValue(e.getValue());
			}
		});
		
		this.presenter.getErrorNameToServerChanged().addHandler(new Handler<GenericEventArgs<String>>() {
			public void invoke(Object sender, GenericEventArgs<String> e) {
				if(e.getValue().length() == 0) {
					//textItem.
				}
			}
		});
		
		CommandManager.getInstance().setCommand(this.button, this.presenter.getSendCommand(), new GenericGetter<Object>() {
			public Object get() {
				return null;
			}
		});
	}

	public String getName() {
		return "User";
	}
}
