package net.etfbl.object;

import java.lang.reflect.*;

public class Reflection2 {


	public static void main(String args[]) throws Exception {
		Method methods[] = String.class.getDeclaredMethods();
		for (Method m : methods) {
			System.out.println(m.toGenericString());
		}
		Method eq = String.class.getDeclaredMethod("equals");
		eq.setAccessible(false);
		
		String s = new String("test");
		System.out.println(s.equals("test"));
	}
}