package de.behrfried.wikianalyzer.wawebapp.client.view.user;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.PresenterBase;
import de.behrfried.wikianalyzer.wawebapp.client.view.View;


public abstract class ExpertSearchView extends View {
	/**
	 * Base interface for user comparison presenters.
	 * 
	 * @author marcus
	 * 
	 */
	public interface Presenter extends PresenterBase {

	}
	@Override
    public String getName() {
	    // TODO Auto-generated method stub
	    return "Experten Suche";
    }

}
