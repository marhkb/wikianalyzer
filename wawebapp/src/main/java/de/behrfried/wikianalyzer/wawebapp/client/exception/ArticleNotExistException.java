package de.behrfried.wikianalyzer.wawebapp.client.exception;

import java.io.Serializable;

/**
 * @author marcus
 */
public class ArticleNotExistException extends Exception implements Serializable {

	public ArticleNotExistException() { }

	public ArticleNotExistException(String message) {
		super(message);
	}
}
