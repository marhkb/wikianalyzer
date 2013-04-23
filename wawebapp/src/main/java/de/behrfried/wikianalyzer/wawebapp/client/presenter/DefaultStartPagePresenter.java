package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import com.google.inject.Inject;

import de.behrfried.wikianalyzer.wawebapp.client.view.StartPageView;

public class DefaultStartPagePresenter implements StartPagePresenter {

    private StartPageView view;
    
    @Inject
    public DefaultStartPagePresenter(final StartPageView view) {
	this.view = view;
    }
}
