package de.behrfried.wikianalyzer.wawebapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.behrfried.wikianalyzer.wawebapp.shared.FieldVerifier;

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

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button(this.messages.sendButton());
		final TextBox nameField = new TextBox();
		nameField.setText(this.messages.nameField());

		final HorizontalPanel hp = new HorizontalPanel();
		hp.setBorderWidth(2);
		hp.setVisible(false);

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("contentContainer").add(hp);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

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
				// First, we validate the input.
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					return;
				}

				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								if(hp.getWidgetCount() > 0) {
									hp.remove(0);
								}
								sendButton.setEnabled(true);
							}

							public void onSuccess(String result) {
								if(hp.getWidgetCount() > 0) {
									hp.remove(0);
								}
								final HTMLPanel html = new HTMLPanel(result);
								hp.add(html);
								hp.setVisible(true);
								sendButton.setEnabled(true);
										
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}
}
