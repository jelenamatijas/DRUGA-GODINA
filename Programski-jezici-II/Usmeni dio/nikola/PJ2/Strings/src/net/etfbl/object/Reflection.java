package net.etfbl.object;

import java.lang.reflect.*;

public class Reflection {
	static void changeBooleanNonStatic(Boolean instance) throws Exception {
		Field fields[] = Boolean.class.getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f.toGenericString());
		}
		Field field = Boolean.class.getDeclaredField("value");
		field.setAccessible(true);
		field.setBoolean(instance, true);
	}
	
	static void changeBooleanStatic() throws Exception {
		Field fields[] = Boolean.class.getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f.toGenericString());
		}
		Field field = Boolean.class.getDeclaredField("FALSE");
		field.setAccessible(true);

		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, true);
	}

	public static void main(String args[]) throws Exception {
		changeBooleanStatic();
		Boolean f = false;
//		changeBooleanNonStatic(f);
		System.out.println(f);
		System.out.format("Everything is %s", f); // "Everything is true"
	}
}