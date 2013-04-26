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
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.event.GenericEventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.event.Handler;

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
	private TextItem textItem;
	private Button button;

	/**
	 * Creates an instance of {@link DefaultUserView}. All arguments are
	 * injected by Gin.
	 * 
	 * @param parentView
	 */
	@Inject
	public DefaultUserView(final Presenter presenter, final Messages messages) throws IllegalArgumentException {
		if(messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.presenter = presenter;
		this.messages = messages;
		
		this.textItem = new TextItem();
		this.button = new Button("Send");
		this.vLayout = new VLayout();
		
		DynamicForm dForm = new DynamicForm();
		dForm.setFields(textItem);
		
		this.vLayout.addChild(dForm);
		this.vLayout.addChild(this.button);
		
		this.addChild(this.vLayout);
		
		this.bind();
	}
	
	private void bind() {
		
		this.textItem.addChangedHandler(new ChangedHandler() {		
			public void onChanged(ChangedEvent event) {
				presenter.setNameToServer(textItem.getValueAsString());
			}
		});
		this.textItem.setValue("");
		
		this.presenter.getNameToServerChanged().addHandler(new Handler<GenericEventArgs<String>>() {		
			public void invoke(Object sender, GenericEventArgs<String> e) {
				textItem.setValue(e.getValue());
			}
		});
		
		this.presenter.getErrorNameToServerChanged().addHandler(new Handler<GenericEventArgs<String>>() {
			public void invoke(Object sender, GenericEventArgs<String> e) {
				//TODO
			}
		});
		
		this.presenter.canSendNameToServerChanged().addHandler(new Handler<GenericEventArgs<Boolean>>() {
			public void invoke(Object sender, GenericEventArgs<Boolean> e) {
				button.setDisabled(!e.getValue());
			}
		});
		
		this.button.addClickHandler(new ClickHandler() {	
			public void onClick(ClickEvent event) {
				presenter.onSendNameToServer();
			}
		});
	}

	public String getName() {
		return "User";
	}
}
