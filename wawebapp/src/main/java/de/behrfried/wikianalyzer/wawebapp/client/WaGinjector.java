package de.behrfried.wikianalyzer.wawebapp.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import de.behrfried.wikianalyzer.wawebapp.client.presenter.StartPagePresenter;

@GinModules(WaGinModule.class)
public interface WaGinjector extends Ginjector {
    StartPagePresenter getStartPagePresenter();
}
