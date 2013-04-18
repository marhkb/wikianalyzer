package de.behrfried.wikianalyzer.simplemath;

public class SimpleMath {

	public static int fak(int n) {
		return n < 0 ? -1 : _fak(n);
	}
	
	private static int _fak(int n) {
		return n == 0 ? 1 : n * fak(n - 1);
	}
	
	public static int fibo(int n) {
		if(n <= 0) {
			return 0;
		}
		return _fibo(n);
	}
	
	private static int _fibo(int n) {
		if(n == 1 || n == 2) {
			return 1;
		}
		return _fibo(n-1) + _fibo(n -2);
	}
}
