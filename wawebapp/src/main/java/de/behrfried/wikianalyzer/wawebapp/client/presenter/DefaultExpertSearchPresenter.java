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

	private CriterionInfo criterionInfo;
	private final List<TitleOrCategory> titleOrCategories = new ArrayList<TitleOrCategory>();
	private final List<LinkedHashMap<String, String>> suggestions = new ArrayList<LinkedHashMap<String, String>>();

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
	}

	@Override
	public List<TitleOrCategory> getTitleOrCategories() {
		return this.titleOrCategories;
	}

	@Override
	public void raiseChanged(int i) {
		this.jGetArticleTitles(this.getTitleOrCategories().get(i).getTitle(), 5, new Integer(i));
	}

	@Override
	public List<LinkedHashMap<String, String>> getArticleSuggestions() {
		return this.suggestions;
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
				}

				@Override
				public boolean canExecute(Object param) {
					return false;
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
					mainService.sendCriterionInfo(getTitleOrCategories(),
							new AsyncCallback<CriterionInfo>() {

								public void onSuccess(final CriterionInfo result) {
									setCriterionInfo(result);
								}

								public void onFailure(final Throwable caught) {
									Window.alert(caught.getMessage());
								}
							}
					);
				}

				public boolean canExecute(final Object param) {
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

//	public final native void jTest(String word, int maxResults, Integer i) /*-{
//		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultExpertSearchPresenter::clearSuggestions(Ljava/lang/Integer;)(i);
//	}-*/;

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

	public final native void jGetCategories(String word, int maxResults) /*-{
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::clearSuggestions()();
		this.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::fireSuggestionsChanged()();
		var inst = this;
		$wnd.$
				.getJSON(
						"http://de.wikipedia.org/w/api.php?action=query&format=json&generator=allpages&callback=?",
						{
							gaplimit : maxResults,
							gapfrom : word
						},
						function(data) {
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::clearSuggestions()();
							for ( var d in data["query"]["pages"]) {
								var title = data["query"]["pages"][d].title;
								inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::addToSuggestions(Ljava/lang/String;)(title);

							}
							inst.@de.behrfried.wikianalyzer.wawebapp.client.presenter.mock.MockArticlePresenter::fireSuggestionsChanged()();
						});
	}-*/;
}
