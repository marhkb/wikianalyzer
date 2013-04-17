package de.behrfried.wikianalyzer.simplemath;

public class SimpleMath {

	public static int fak(int n) {
		return n < 0 ? -1 : _fak(n);
	}
	
	private static int _fak(int n) {
		return n == 0 ? 1 : n * fak(n - 1);
	}
}
