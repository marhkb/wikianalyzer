package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainServiceAsync;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.Command;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.CommandManager;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.UICommand;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Event;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserView;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserInfo;

import java.util.LinkedHashMap;

public class DefUserPresenter implements UserView.Presenter {
	private final static Object INIT_CONTEXT = new Object();
	private final Event<EventArgs> userNameChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> userChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> suggestionsChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> searchStatusChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final LinkedHashMap<String, String> suggestions = new LinkedHashMap<String, String>();
	private final MainServiceAsync mainService;
	private UserInfo userInfo;
	private String userName = "";
	private Command sendCommand;
	private boolean searched = true;

	@Inject
	public DefUserPresenter(final MainServiceAsync mainService) throws IllegalArgumentException {
		if(mainService == null) {
			throw new IllegalArgumentException("mainService == null");
		}
		this.mainService = mainService;
	}

	@Override
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	@Override
	public Event<EventArgs> userInfoChanged() {
		return this.userChanged;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public Event<EventArgs> userNameChanged() {
		return this.userNameChanged;
	}

	@Override
	public Command getSendCommand() {
		if(this.sendCommand == null) {
			this.sendCommand = new UICommand() {

				public void execute(final Object param) {
					setSearchStatus(false);
					DefUserPresenter.this.mainService.sendUserName(DefUserPresenter.this.getUserName(), new AsyncCallback<UserInfo>() {

						public void onSuccess(final UserInfo result) {
							setSearchStatus(true);
							setUserInfo(result);
						}

						public void onFailure(final Throwable caught) {
							Window.alert(caught.getMessage());
						}
					});
				}

				public boolean canExecute(final Object param) {
					return DefUserPresenter.this.getUserName().length() > 0;
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(new Handler<EventArgs>() {

				public void invoke(final Object sender, final EventArgs e) {
					DefUserPresenter.this.getSendCommand().raiseCanExecuteChanged();
				}
			});
		}
		return this.sendCommand;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
		this.userInfoChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
	}

	

	@Override
	public boolean getSearchStatus() {
		return this.searched;
	}

	@Override
	public Event<EventArgs> searchStatusChanged() {
		return this.searchStatusChanged;
	}

	@Override
	public LinkedHashMap<String, String> getUserSuggestions() {
		return this.suggestions;
	}

	@Override
	public Event<EventArgs> userSuggestionsChanged() {
		return this.suggestionsChanged;
	}

	private void setSearchStatus(boolean searched) {
		if(this.searched != searched) {
			this.searched = searched;
			this.searchStatusChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
		}
	}
	
	private void fireSuggestionsChanged() {
		this.suggestionsChanged.fire(INIT_CONTEXT, this, EventArgs.EMPTY);
	}

	private final void clearSuggestions() {
		this.suggestions.clear();
	}

	private final void addToSuggestions(final String name) {
		this.suggestions.put(name, name);
	}

	@Override
	public void setUserName(final String userName) {
		if(!userName.equals(this.userName)) {
			this.userName = userName;
            if(this.userName.length() > 0 && Character.isLetter(this.userName.charAt(0))) {
                this.userName = Character.toUpperCase(this.userName.charAt(0)) + this.userName.substring(1);
            }
			this.userNameChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
			this.jGetUserName(this.userName);
			CommandManager.get().invalidateRequerySuggested();
		}
	}

	public final native void jGetUserName(String word) /*-{
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefUserPresenter::clearSuggestions()();
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefUserPresenter::fireSuggestionsChanged()();
		var inst = this;

		$wnd.$
				.getJSON(
						"http://de.wikipedia.org/w/api.php?action=query&format=json&list=allusers&callback=?",
						{
							aufrom : word
						},
						function(data) {
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefUserPresenter::clearSuggestions()();
							$wnd.$.each(
											data["query"]["allusers"],
											function(i, val) {
												inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefUserPresenter::addToSuggestions(Ljava/lang/String;)(val.name);
											});
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefUserPresenter::fireSuggestionsChanged()();
						});
	}-*/;
}
