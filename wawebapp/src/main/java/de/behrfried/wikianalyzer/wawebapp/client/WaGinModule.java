package de.behrfried.wikianalyzer.wawebapp.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.DefaultStartPagePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.StartPagePresenter;
import de.behrfried.wikianalyzer.wawebapp.client.view.DefaultStartPageView;
import de.behrfried.wikianalyzer.wawebapp.client.view.StartPageView;

public class WaGinModule extends AbstractGinModule {

    @Override
    protected void configure() {
	this.bind(StartPageView.class).to(DefaultStartPageView.class).in(Singleton.class);
	this.bind(StartPagePresenter.class).to(DefaultStartPagePresenter.class).in(Singleton.class);
    }
    
}
