package de.behrfried.wikianalyzer.wawebapp.client.view.user;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.PresenterBase;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.Command;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Event;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.view.View;
import de.behrfried.wikianalyzer.wawebapp.shared.user.CriterionInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.TitleOrCategory;

import java.util.LinkedHashMap;
import java.util.List;


public abstract class ExpertSearchView extends View {
	/**
	 * Base interface for user comparison presenters.
	 * 
	 * @author marcus
	 * 
	 */
	public interface Presenter extends PresenterBase {

		public Event<IntegerChangedEventArgs> titleOrCategoriesChanged();

		public Event<EventArgs> criterionInfoChanged();

		/**
		 * is to be fired when suggestion has changed
		 * @return
		 */
		Event<IntegerChangedEventArgs> articleSuggestionsChanged();


		public CriterionInfo getCriterionInfo();

		public List<TitleOrCategory> getTitleOrCategories();

		public void raiseSuggestionsChanged(int i);

		public void raiseTitleOrCategoryChanged(int i);

		/**
		 * contains the search suggestions for articles
		 * @return
		 */
		List<LinkedHashMap<String, String>> getArticleSuggestions();

		/**
		 *
		 */
		boolean getSearchStatus();

		Event<EventArgs> searchStatusChanged();

		public Command getAddTitleOrCategoryCommand();
		public Command getRemoveTitleOrCategoryCommand();
		public Command getSendCommand();
	}
	@Override
    public String getName() {
	    return "Experten Suche";
    }

	public final static class IntegerChangedEventArgs extends EventArgs {

		private final int index;

		public IntegerChangedEventArgs(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

}
