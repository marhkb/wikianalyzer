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

package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import de.behrfried.wikianalyzer.util.command.Command;
import de.behrfried.wikianalyzer.util.event.Event;
import de.behrfried.wikianalyzer.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.Messages;
import de.behrfried.wikianalyzer.wawebapp.client.engine.CommandManager;
import de.behrfried.wikianalyzer.wawebapp.client.engine.DefaultCommandManager;
import de.behrfried.wikianalyzer.wawebapp.client.engine.UICommand;
import de.behrfried.wikianalyzer.wawebapp.client.event.GenericEventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainServiceAsync;
import de.behrfried.wikianalyzer.wawebapp.client.view.UserView;

/**
 * Default implementation of {@link UserView.Presenter}.
 * 
 * @author marcus
 * 
 */
public class DefaultUserPresenter implements UserView.Presenter {

	private final CommandManager commandManager;
	
	private final MainServiceAsync mainService;
	private final Messages messages;

	private final Object initContext = new Object();

	@Inject
	public DefaultUserPresenter(final CommandManager commandManager, final MainServiceAsync mainService,
			final Messages messages) throws IllegalArgumentException {
		if (mainService == null) {
			throw new IllegalArgumentException("mainService == null");
		}
		if (messages == null) {
			throw new IllegalArgumentException("messages == null");
		}
		this.commandManager = commandManager;
		this.mainService = mainService;
		this.messages = messages;
	}

	private String nameToServer = "";

	public String getNameToServer() {
		return this.nameToServer;
	}

	public void setNameToServer(final String nameToServer) {
		if (!this.nameToServer.equals(nameToServer)) {
			this.nameToServer = nameToServer;
			this.checkNameToServer();
			this.checkCanSendNameToServer();
			this.getNameToServerChanged()
				.fire(this.initContext, DefaultUserPresenter.this, new GenericEventArgs<String>(getNameToServer()));
			this.commandManager.invalidateRequerySuggested();
		}
	}

	private void checkNameToServer() {
		final String old = this.nameToServerErrorMessage;
		if (this.getNameToServer() == null || this.getNameToServer().length() == 0) {
			this.nameToServerErrorMessage = "empty string";
		} else {
			this.nameToServerErrorMessage = "";
		}
		
		if(!old.equals(this.nameToServerErrorMessage)) {
			this.getErrorNameToServerChanged().fire(
					this.initContext, 
					this, 
					new GenericEventArgs<String>(this.getNameToServerErrorMessage()));
		}
	}

	private Event<GenericEventArgs<String>> nameToServerChanged = new Event<GenericEventArgs<String>>(this.initContext);
	public Event<GenericEventArgs<String>> getNameToServerChanged() {
		return this.nameToServerChanged;
	}

	private String nameToServerErrorMessage = "empty string";
	public String getNameToServerErrorMessage() {
		return this.nameToServerErrorMessage;
	}

	private Event<GenericEventArgs<String>> errorNameToServerChanged = new Event<GenericEventArgs<String>>(
			this.initContext);

	public Event<GenericEventArgs<String>> getErrorNameToServerChanged() {
		return this.errorNameToServerChanged;
	}

	/**
	 * 
	 */
	public void onSendNameToServer() {
		if (this.getCanSendNameToServer()) {
			this.mainService.getArticle(this.nameToServer,
					new AsyncCallback<String>() {
						public void onSuccess(String result) {
							setNameToServer(result);
							getNameToServerChanged()
								.fire(initContext, DefaultUserPresenter.this, new GenericEventArgs<String>(getNameToServer()));
						}

						public void onFailure(Throwable caught) {
						}
					});
		}
	}

	private boolean canSendNameToServer = this.getNameToServerErrorMessage().length() == 0;

	public boolean getCanSendNameToServer() {
		return this.canSendNameToServer;
	}
	
	private void checkCanSendNameToServer() {
		final boolean old = this.canSendNameToServer;
		this.canSendNameToServer = this.getNameToServerErrorMessage().length() == 0;
		if(old != this.canSendNameToServer) {
			this.canSendNameToServerChanged().fire(initContext, this, new GenericEventArgs<Boolean>(this.canSendNameToServer));
		}
	}

	private final Event<GenericEventArgs<Boolean>> canSendNameToServerChanged = new Event<GenericEventArgs<Boolean>>(this.initContext);
	public Event<GenericEventArgs<Boolean>> canSendNameToServerChanged() {
		return this.canSendNameToServerChanged;
	}

	private final Command sendCommand = new UICommand() {
		public void execute(Object param) {
			DefaultUserPresenter.this.onSendNameToServer();
			this.raiseCanExecuteChanged(EventArgs.EMPTY);
		}
		
		public boolean canExecute(Object Param) {
			return DefaultUserPresenter.this.nameToServer.length() != 0;
		}
	};
	
	public Command getSendCommand() {
		return this.sendCommand;
	}
}
