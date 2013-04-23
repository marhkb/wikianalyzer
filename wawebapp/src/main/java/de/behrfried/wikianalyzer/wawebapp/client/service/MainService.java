package de.behrfried.wikianalyzer.wawebapp.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.behrfried.wikianalyzer.wawebapp.shared.StringObject;

@RemoteServiceRelativePath("main")
public interface MainService extends RemoteService {
    StringObject getStringObject(StringObject o);
}
