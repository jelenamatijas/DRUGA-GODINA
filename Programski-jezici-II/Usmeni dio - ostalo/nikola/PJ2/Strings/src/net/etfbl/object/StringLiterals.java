package net.etfbl.object;

import java.lang.reflect.Field;
import java.util.Iterator;

public class StringLiterals {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static void main(String[] args) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		testString("s");
		String string = "string";
		String s1 = "test string";
		String s2 = "test string";
		String s3 = "test" + " string";
		String s4 = "test" + " " + string;
		String s5 = new String("test string");
		String s6 = ("test " + string).intern();
		String s7 = "atest string".substring(1);
		String s8 = new String("tes" + "t " + string);
//		s7 = s7.intern();
		System.out.println(s1==s1);
		System.out.println(s1==s2);
		System.out.println(s1==s3);
		System.out.println(s1==s4);
		System.out.println(s1==s5);
		System.out.println(s1==s6);
		System.out.println(s1==s7);
		System.out.println(s1==s8);
		System.out.println("==============InternalCharArrayHashCode=====================");
		System.out.println(showStringInternalCharArrayHashCode(s1));
		System.out.println(showStringInternalCharArrayHashCode(s2));
		System.out.println(showStringInternalCharArrayHashCode(s3));
		System.out.println(showStringInternalCharArrayHashCode(s4));
		System.out.println(showStringInternalCharArrayHashCode(s5));
		System.out.println(showStringInternalCharArrayHashCode(s6));
		System.out.println(showStringInternalCharArrayHashCode(s7));
		System.out.println(showStringInternalCharArrayHashCode(s8));
		System.out.println("===============identityHashCode====================");
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		System.out.println(System.identityHashCode(s3));
		System.out.println(System.identityHashCode(s4));
		System.out.println(System.identityHashCode(s5));
		System.out.println(System.identityHashCode(s6));
		System.out.println(System.identityHashCode(s7));
		System.out.println(System.identityHashCode(s8));
		System.out.println("=================hashCode==================");
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
		System.out.println(s4.hashCode());
		System.out.println(s5.hashCode());
		System.out.println(s6.hashCode());
		System.out.println(s7.hashCode());
		System.out.println(s8.hashCode());
		System.out.println("===================================");
		s4 = s4.intern();
		System.out.println(showStringInternalCharArrayHashCode(s4));
		System.out.println(System.identityHashCode(s4));
	}
	
	
	private static int showStringInternalCharArrayHashCode(String s) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	    final Field value = String.class.getDeclaredField("value");
	    value.setAccessible(true);
	    return value.get(s).hashCode();
	}

	private static void testString(String s) {
		Field fields[] = String.class.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.toGenericString());
		}
	}
}
