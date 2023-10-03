package CS114.Lab3;/*
 * Class to define a pair of arguments for tail-recursive factorial function.
 */

public class NState {
	long n;
	long a;

	NState(long n, long a) {
		this.n = n;
		this.a = a;
	}

	public long getN() {
		return n;
	}

	public long getA() {
		return a;
	}

	public String toString() {
		return n + ":" + a;
	}

}
