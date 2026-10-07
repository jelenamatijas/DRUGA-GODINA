package org.unibl.etf.pj2.util;

import org.unibl.etf.pj2.string.PJ2String;
import org.unibl.etf.pj2.integer.PJ2Integer;

public class Util{
	public static String upConcat(String str1, String str2){
		return PJ2String.up(str1) + " " + PJ2String.up(str2);
	}
	public static int reverseSum(int val1, int val2){
		return PJ2Integer.reverseInt(val1) + PJ2Integer.reverseInt(val2);
	}
}