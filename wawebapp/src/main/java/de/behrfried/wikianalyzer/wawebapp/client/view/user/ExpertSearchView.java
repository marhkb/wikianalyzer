package de.behrfried.wikianalyzer.wawebapp.client.view.user;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.PresenterBase;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.Command;
import de.behrfried.wikianalyzer.wawebapp.client.view.View;

import java.util.List;


public abstract class ExpertSearchView extends View {
	/**
	 * Base interface for user comparison presenters.
	 * 
	 * @author marcus
	 * 
	 */
	public interface Presenter extends PresenterBase {

		public List<String> getCriterions();
		public List<Boolean> getIsCategories();

		public Command getAddCriterionCommand();
		public Command getRemoveCriterionCommand();
	}
	@Override
    public String getName() {
	    // TODO Auto-generated method stub
	    return "Experten Suche";
    }

}
