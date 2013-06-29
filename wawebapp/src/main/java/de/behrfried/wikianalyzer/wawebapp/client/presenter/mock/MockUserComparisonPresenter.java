package de.behrfried.wikianalyzer.wawebapp.client.presenter.mock;

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
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserComparisonView;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserComparisonInfo;

import java.util.LinkedHashMap;

public class MockUserComparisonPresenter implements UserComparisonView.Presenter {

	private final static Object INIT_CONTEXT = new Object();
	private final Event<EventArgs> userName1Changed = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> userName2Changed = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> user1Changed = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> user2Changed = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> user1SuggestionsChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> user2SuggestionsChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> searchStatusChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final LinkedHashMap<String, String> user1Suggestions = new LinkedHashMap<String, String>();
	private final LinkedHashMap<String, String> user2Suggestions = new LinkedHashMap<String, String>();
	private final MainServiceAsync mainService;
	private UserComparisonInfo userComparisonInfo;
	private String userName1 = "", userName2 = "";
	private Command sendCommand;
	private boolean searched = false;

	@Inject
	public MockUserComparisonPresenter(final MainServiceAsync mainService) throws IllegalArgumentException {
		if(mainService == null) {
			throw new IllegalArgumentException("mainService == null");
		}
		this.mainService = mainService;
	}

	@Override
	public Command getSendCommand() {
		if(this.sendCommand == null) {
			this.sendCommand = new UICommand() {

				public void execute(final Object param) {
					setSearchStatus(false);
					MockUserComparisonPresenter.this.mainService.sendUserForComparison(MockUserComparisonPresenter.this.getUserName1(),
					        MockUserComparisonPresenter.this.getUserName2(), new AsyncCallback<UserComparisonInfo>() {

						        @Override
						        public void onSuccess(final UserComparisonInfo result) {
							        setSearchStatus(true);
							        setUserComparisonInfo(result);
						        }

						        public void onFailure(final Throwable caught) {
							        Window.alert(caught.getMessage());
						        }

					        });
				}

				public boolean canExecute(final Object param) {
					return (MockUserComparisonPresenter.this.getUserName1().length() > 0)
					        && (MockUserComparisonPresenter.this.getUserName2().length() > 0);
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(new Handler<EventArgs>() {

				public void invoke(final Object sender, final EventArgs e) {
					MockUserComparisonPresenter.this.getSendCommand().raiseCanExecuteChanged();
				}
			});
		}
		return this.sendCommand;
	}

	@Override
	public void setUserName1(final String userName) {
		if(!userName.equals(this.userName1)) {
			this.userName1 = userName;
			if(this.userName1.length() > 0 && Character.isLetter(this.userName1.charAt(0))) {
				this.userName1 = Character.toUpperCase(this.userName1.charAt(0)) + this.userName1.substring(1);
			}
			this.userName1Changed().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
			this.jGetUserName1(this.userName1);
			CommandManager.get().invalidateRequerySuggested();
		}
	}

	@Override
	public void setUserName2(final String userName) {
		if(!userName.equals(this.userName2)) {
			this.userName2 = userName;
			if(this.userName2.length() > 0 && Character.isLetter(this.userName2.charAt(0))) {
				this.userName2 = Character.toUpperCase(this.userName2.charAt(0)) + this.userName2.substring(1);
			}
			this.userName2Changed().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
			this.jGetUserName2(this.userName2);
			CommandManager.get().invalidateRequerySuggested();
		}
	}

	public final native void jGetUserName1(String word) /*-{
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::clearSuggestions()();
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::fireUser1SuggestionsChanged()();
		var inst = this;

		$wnd.$
				.getJSON(
						"http://de.wikipedia.org/w/api.php?action=query&format=json&list=allusers&callback=?",
						{
							aufrom : word
						},
						function(data) {
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::clearSuggestions()();
							$wnd.$
									.each(
											data["query"]["allusers"],
											function(i, val) {
												inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::addToUser1Suggestions(Ljava/lang/String;)(val.name);
											});
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::fireUser1SuggestionsChanged()();
						});
	}-*/;
	
	public final native void jGetUserName2(String word) /*-{
	this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::clearSuggestions()();
	this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::fireUser2SuggestionsChanged()();
	var inst = this;

	$wnd.$
			.getJSON(
					"http://de.wikipedia.org/w/api.php?action=query&format=json&list=allusers&callback=?",
					{
						aufrom : word
					},
					function(data) {
						inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::clearSuggestions()();
						$wnd.$
								.each(
										data["query"]["allusers"],
										function(i, val) {
											inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::addToUser2Suggestions(Ljava/lang/String;)(val.name);
										});
						inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockUserComparisonPresenter::fireUser2SuggestionsChanged()();
					});
}-*/;

	@Override
	public UserComparisonInfo getUserComparisonInfo() {
		return this.userComparisonInfo;
	}

	@Override
	public Event<EventArgs> userComparisonInfoChanged() {
		return this.userComparisonInfoChanged();
	}

	@Override
	public String getUserName1() {
		return this.userName1;
	}

	@Override
	public String getUserName2() {
		return this.userName2;
	}

	@Override
	public Event<EventArgs> userName1Changed() {
		return this.userName1Changed;
	}

	@Override
	public Event<EventArgs> userName2Changed() {
		return this.userName2Changed;
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
	public LinkedHashMap<String, String> getUser1Suggestions() {
		return this.user1Suggestions;
	}

	@Override
	public LinkedHashMap<String, String> getUser2Suggestions() {
		return this.user2Suggestions;
	}
	
	@Override
	public Event<EventArgs> user1SuggestionsChanged() {
		return this.user1SuggestionsChanged;
	}

	@Override
	public Event<EventArgs> user2SuggestionsChanged() {
		return this.user2SuggestionsChanged;
	}
	
	public void setUserComparisonInfo(UserComparisonInfo userComparisonInfo) {
		this.userComparisonInfo = userComparisonInfo;
		this.userComparisonInfoChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
	}

	public void setSearchStatus(boolean searched) {
		this.searched = searched;
	}

	private void fireUser1SuggestionsChanged() {
		this.user1SuggestionsChanged.fire(INIT_CONTEXT, this, EventArgs.EMPTY);
	}

	private void fireUser2SuggestionsChanged() {
		this.user2SuggestionsChanged.fire(INIT_CONTEXT, this, EventArgs.EMPTY);
	}
	
	private final void clearSuggestions() {
		this.user1Suggestions.clear();
		//this.user2Suggestions.clear();
	}

	private final void addToUser1Suggestions(final String name) {
		this.user1Suggestions.put(name, name);
	}
	
	private final void addToUser2Suggestions(final String name) {
		this.user2Suggestions.put(name, name);
	}
}
