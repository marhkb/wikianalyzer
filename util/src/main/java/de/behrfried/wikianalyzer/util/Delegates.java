package de.behrfried.wikianalyzer.util;

public final class Delegates {

	public interface Action {
		public void invoke();
	}

	public interface Action1<E1> {
		public void invoke(E1 e1);
	}

	
	public interface Func<EResult> {
		public EResult invoke();
	}
	
	public interface Func1<E1, EResult> {
		public EResult invoke(E1 e1);
	}
}
