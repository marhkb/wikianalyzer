package de.behrfried.wikianalyzer.wawebapp.shared;

import java.io.Serializable;

public class StringObject implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6407435912570206085L;
    
    private String str;
    
    public StringObject() {  }

    public StringObject(String str) {
	super();
	this.str = str;
    }

    public String getStr() {
        return this.str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
