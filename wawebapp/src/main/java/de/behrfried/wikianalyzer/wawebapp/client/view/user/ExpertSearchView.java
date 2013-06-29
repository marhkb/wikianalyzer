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

		public Event<CriterionChangedEventArgs> titleOrCategoriesChanged();

		public Event<EventArgs> criterionInfoChanged();

		public CriterionInfo getCriterionInfo();

		public List<TitleOrCategory> getTitleOrCategories();

		/**
		 * contains the search suggestions for articles
		 * @return
		 */
		LinkedHashMap<String, String> getArticleSuggestions();

		/**
		 * is to be fired when suggestion has changed
		 * @return
		 */
		Event<EventArgs> articleSuggestionsChanged();

		public Command getAddCriterionCommand();
		public Command getRemoveCriterionCommand();
		public Command getSendCommand();
	}
	@Override
    public String getName() {
	    // TODO Auto-generated method stub
	    return "Experten Suche";
    }

	public final static class CriterionChangedEventArgs extends EventArgs {

		private final int index;

		public CriterionChangedEventArgs(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}

}
