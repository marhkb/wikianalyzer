package de.behrfried.wikianalyzer.wawebapp.client.view.factory;

import de.behrfried.wikianalyzer.wawebapp.client.view.DefaultStartPageView;
import de.behrfried.wikianalyzer.wawebapp.client.view.StartPageView;

public class DefaultViewFactory implements ViewFactory {

    private final StartPageView startPageView = new DefaultStartPageView();
    
    public StartPageView getStartpageView() {
	return this.startPageView;
    }
    
}
