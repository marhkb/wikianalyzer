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
import de.behrfried.wikianalyzer.wawebapp.client.view.user.ExpertSearchView;
import de.behrfried.wikianalyzer.wawebapp.shared.user.CriterionInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.TitleOrCategory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class DefaultExpertSearchPresenter implements ExpertSearchView.Presenter {

	private static final Object INIT_CONTEXT = new Object();
	private final Event<ExpertSearchView.IntegerChangedEventArgs> titleOrcategoriesChanged =
			new Event<ExpertSearchView.IntegerChangedEventArgs>(INIT_CONTEXT);
	private final Event<ExpertSearchView.IntegerChangedEventArgs> suggestionsChanged =
			new Event<ExpertSearchView.IntegerChangedEventArgs>(INIT_CONTEXT);
	private final Event<EventArgs> criterionInfoChanged = new Event<EventArgs>(INIT_CONTEXT);
	private final MainServiceAsync mainService;
	private final List<TitleOrCategory> titleOrCategories = new ArrayList<TitleOrCategory>();
	private final List<LinkedHashMap<String, String>> suggestions = new ArrayList<LinkedHashMap<String, String>>();
	private CriterionInfo criterionInfo;
	private Command addTitleOrCategoryCommand;
	private Command sendCommand;
	private Command removeTitleOrCategorCommand;

	@Inject
	public DefaultExpertSearchPresenter(final MainServiceAsync mainService) {
		this.mainService = mainService;
		this.getTitleOrCategories().add(new TitleOrCategory("", false));
		this.getArticleSuggestions().add(new LinkedHashMap<String, String>());
	}

	@Override
	public Event<ExpertSearchView.IntegerChangedEventArgs> titleOrCategoriesChanged() {
		return this.titleOrcategoriesChanged;
	}

	@Override
	public Event<EventArgs> criterionInfoChanged() {
		return this.criterionInfoChanged;
	}

	@Override
	public CriterionInfo getCriterionInfo() {
		return this.criterionInfo;
	}

	public void setCriterionInfo(CriterionInfo criterionInfo) {
		this.criterionInfo = criterionInfo;
		this.criterionInfoChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
	}

	@Override
	public List<TitleOrCategory> getTitleOrCategories() {
		return this.titleOrCategories;
	}

	@Override
	public void raiseSuggestionsChanged(int i) {
		if(this.getTitleOrCategories().get(i).isCategory()) {
			this.jGetCategories(this.getTitleOrCategories().get(i).getTitle() + "", 10, new Integer(i));
		} else {
			this.jGetArticleTitles(this.getTitleOrCategories().get(i).getTitle(), 10, new Integer(i));
		}
		CommandManager.get().invalidateRequerySuggested();
	}

	@Override
	public void raiseTitleOrCategoryChanged(int i) {
		this.titleOrCategoriesChanged().fire(INIT_CONTEXT, this, new ExpertSearchView.IntegerChangedEventArgs(i));
	}

	@Override
	public List<LinkedHashMap<String, String>> getArticleSuggestions() {
		return this.suggestions;
	}

	private boolean searchStatus = true;

	@Override
	public boolean getSearchStatus() {
		return searchStatus;
	}

	private void setSearchStatus(final boolean searchStatus) {
		if(this.searchStatus != searchStatus) {
			this.searchStatus = searchStatus;
			this.searchStatusChanged().fire(INIT_CONTEXT, this, EventArgs.EMPTY);
		}
	}

	private final Event<EventArgs> searchStatusChanged = new Event<EventArgs>(INIT_CONTEXT);

	@Override
	public Event<EventArgs> searchStatusChanged() {
		return this.searchStatusChanged;
	}

	@Override
	public Event<ExpertSearchView.IntegerChangedEventArgs> articleSuggestionsChanged() {
		return this.suggestionsChanged;
	}

	@Override
	public Command getAddTitleOrCategoryCommand() {
		if(this.addTitleOrCategoryCommand == null) {
			this.addTitleOrCategoryCommand = new UICommand() {
				@Override
				protected EventArgs getEventArgs() {
					return null;
				}

				@Override
				public void execute(Object param) {
					getTitleOrCategories().add(new TitleOrCategory("", false));
					getArticleSuggestions().add(new LinkedHashMap<String, String>());
					CommandManager.get().invalidateRequerySuggested();
				}

				@Override
				public boolean canExecute(Object param) {
					return true;
				}
			};
		}
		return this.addTitleOrCategoryCommand;
	}

	@Override
	public Command getRemoveTitleOrCategoryCommand() {
		if(this.removeTitleOrCategorCommand == null) {
			this.removeTitleOrCategorCommand = new UICommand() {
				@Override
				protected EventArgs getEventArgs() {
					return null;
				}

				@Override
				public void execute(Object param) {
					getTitleOrCategories().remove(getTitleOrCategories().size() - 1);
					CommandManager.get().invalidateRequerySuggested();
				}

				@Override
				public boolean canExecute(Object param) {
					return !getTitleOrCategories().isEmpty();
				}
			};
		}
		return this.removeTitleOrCategorCommand;
	}

	@Override
	public Command getSendCommand() {
		if(this.sendCommand == null) {
			this.sendCommand = new UICommand() {
				public void execute(final Object param) {
					setSearchStatus(false);
					mainService.sendCriterionInfo(
							getTitleOrCategories(),
							new AsyncCallback<CriterionInfo>() {

								public void onSuccess(final CriterionInfo result) {
									setSearchStatus(true);
									setCriterionInfo(result);
								}

								public void onFailure(final Throwable caught) {
									Window.alert(caught.getMessage());
								}
							}
					);
				}

				public boolean canExecute(final Object param) {
					for(TitleOrCategory toc : getTitleOrCategories()) {
						if(toc.getTitle().isEmpty()) {
							return false;
						}
					}
					return true;
				}

				@Override
				protected EventArgs getEventArgs() {
					return EventArgs.EMPTY;
				}
			};
			CommandManager.get().requerySuggested().addHandler(
					new Handler<EventArgs>() {
						public void invoke(final Object sender, final EventArgs e) {
							getSendCommand().raiseCanExecuteChanged();
						}
					}
			);
		}
		return this.sendCommand;
	}

	private void fireSuggestionsChanged(Integer i) {
		this.suggestionsChanged.fire(INIT_CONTEXT, this, new ExpertSearchView.IntegerChangedEventArgs(i));
	}

	private final void clearSuggestions(Integer i) {
		this.suggestions.get(i).clear();
	}

	private final void addToSuggestions(final String name, Integer i) {
		this.suggestions.get(i).put(name, name);
	}

	public final native void jGetArticleTitles(String word, int maxResults, Integer i) /*-{
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::clearSuggestions(Ljava/lang/Integer;)(i);
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::fireSuggestionsChanged(Ljava/lang/Integer;)(i);
		var inst = this;
		$wnd.$
				.getJSON(
						"http://de.wikipedia.org/w/api.php?action=query&format=json&generator=allpages&callback=?",
						{
							gaplimit : maxResults,
							gapfrom : word
						},
						function(data) {
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::clearSuggestions(Ljava/lang/Integer;)(i);
							for ( var d in data["query"]["pages"]) {
								var title = data["query"]["pages"][d].title;
								inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::addToSuggestions(Ljava/lang/String;Ljava/lang/Integer;)(title, i);

							}
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::fireSuggestionsChanged(Ljava/lang/Integer;)(i);
						});
	}-*/;

	public final native void jGetCategories(String word, int maxResults, Integer index) /*-{
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::clearSuggestions(Ljava/lang/Integer;)(index);
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::fireSuggestionsChanged(Ljava/lang/Integer;)(index);
		var inst = this;
		$wnd.$
				.getJSON(
						"http://de.wikipedia.org/w/api.php?action=query&format=json&generator=allcategories&callback=?",
						{
							gacprefix : word
						},
						function(data) {
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::clearSuggestions(Ljava/lang/Integer;)(index);
							for ( var d in data["query"]["pages"]) {
								var title = data["query"]["pages"][d].title;
								title = title.substring(10, title.length);
								inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::addToSuggestions(Ljava/lang/String;Ljava/lang/Integer;)(title, index);

							}
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::fireSuggestionsChanged(Ljava/lang/Integer;)(index);
						});
	}-*/;
}
