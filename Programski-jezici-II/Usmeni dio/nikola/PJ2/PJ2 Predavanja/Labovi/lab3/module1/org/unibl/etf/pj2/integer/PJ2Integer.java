package org.unibl.etf.pj2.integer;

public class PJ2Integer{
	public static String int2string(int value){
		return Integer.toBinaryString(value);
	}
	public static int reverseInt(int n){
		    long r = 0;
    while (n != 0) {
        r *= 10;
        r += (n % 10);
        n /= 10;
    }
	return (int)r;
	}
}