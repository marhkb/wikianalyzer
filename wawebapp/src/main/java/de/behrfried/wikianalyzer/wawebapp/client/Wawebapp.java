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
 
package de.behrfried.wikianalyzer.wawebapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel.Direction;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Wawebapp implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private final Messages messages = GWT.create(Messages.class);

	private VerticalPanel vp;
	private HorizontalPanel hp;
	
	private TextBox nameField;
	private Button sendButton;
	private HTMLPanel html;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		this.vp = new VerticalPanel();
		this.vp.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		this.hp = new HorizontalPanel();

		this.nameField = new TextBox();
		this.nameField.setText("Hamster");
		this.nameField.setFocus(true);

		this.sendButton = new Button(this.messages.sendButton());
		
		this.html = new HTMLPanel("");
		this.html.setVisible(false);

		this.hp.add(this.nameField);
		this.hp.add(this.sendButton);

		this.vp.add(this.hp);
		RootPanel.get().add(this.vp);

		// History
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> event) {
				final String historyToken = event.getValue();
				sendNameToServer(historyToken);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {

			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				sendButton.setEnabled(false);
				History.newItem(nameField.getText());
			}
		}

		// Add a handler to send the name to the server
		final MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);

		if (History.getToken().length() != 0) {
			this.sendNameToServer(History.getToken());
		}
	}

	public final void sendNameToServer(final String name) {
		this.nameField.setText(name);
		greetingService.greetServer(name, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				if (html.isVisible()) {
					html.removeFromParent();
				}
				sendButton.setEnabled(true);
			}

			public void onSuccess(String result) {
				if (html.isVisible()) {
					html.removeFromParent();
				}
				html = new HTMLPanel(result);
				vp.add(html);
				sendButton.setEnabled(true);
			}
		});
	}
}
