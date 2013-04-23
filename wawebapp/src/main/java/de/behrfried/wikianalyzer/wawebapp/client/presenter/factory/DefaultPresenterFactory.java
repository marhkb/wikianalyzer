package de.behrfried.wikianalyzer.wawebapp.client.presenter.factory;

import de.behrfried.wikianalyzer.wawebapp.client.Factory;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultStartPagePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.StartPagePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.view.factory.ViewFactory;

public class DefaultPresenterFactory implements PresenterFactory {
    
    private final StartPagePresenter startPagePresenter;
    
    public DefaultPresenterFactory() {
	final ViewFactory viewFactory = Factory.getViewFactory();
	this.startPagePresenter = new DefaultStartPagePresenter(viewFactory.getStartpageView());
    }
    
    public StartPagePresenter getStartPagePresenter() {
	return this.startPagePresenter;
    }
}
