package de.behrfried.wikianalyzer.util.list;

import java.util.List;
import de.behrfried.wikianalyzer.util.event.Event;


public interface ObservableList<E> extends List<E> {

	Event<ListChangedEventArgs<E>> listChanged();
}
