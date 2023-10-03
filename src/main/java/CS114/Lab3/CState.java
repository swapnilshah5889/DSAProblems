package CS114.Lab3;/*
 * Class to define a state for the programs generating all substrings.
 */

public class CState {
	String prefix;
	String suffix;

	public CState(String p, String s) {
		prefix = p;
		suffix = s;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public String toString() {
		return prefix + ":" + suffix;
	}

}
