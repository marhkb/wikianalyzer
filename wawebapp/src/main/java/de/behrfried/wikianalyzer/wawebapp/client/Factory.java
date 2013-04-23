package de.behrfried.wikianalyzer.wawebapp.client;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.factory.DefaultPresenterFactory;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.factory.PresenterFactory;
import de.behrfried.wikianalyzer.wawebapp.client.view.factory.DefaultViewFactory;
import de.behrfried.wikianalyzer.wawebapp.client.view.factory.ViewFactory;

public final class Factory {
    
    private final static ViewFactory viewFactory = new DefaultViewFactory();
    private final static PresenterFactory presenterFactory = new DefaultPresenterFactory();
    
    public static ViewFactory getViewFactory() {
	return viewFactory;
    }
    
    public static PresenterFactory getPresenterFactory() {
	return presenterFactory;
    }
}