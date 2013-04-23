package de.behrfried.wikianalyzer.wawebapp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.behrfried.wikianalyzer.wawebapp.client.service.MainService;
import de.behrfried.wikianalyzer.wawebapp.shared.StringObject;

public class MainServiceImpl extends RemoteServiceServlet implements MainService {

    public StringObject getStringObject(StringObject o) {
	return o;
    }
    
}
