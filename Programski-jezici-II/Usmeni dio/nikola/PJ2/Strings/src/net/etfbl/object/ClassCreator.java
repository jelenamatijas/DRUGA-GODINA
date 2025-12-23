package net.etfbl.object;

import java.lang.reflect.Field;

public class ClassCreator {

	  public static void main(String[] args){

	    ClassLoader classLoader = ClassCreator.class.getClassLoader();

	    try {
	        Class aClass = classLoader.loadClass("net.etfbl.object.Phone");
	        Phone t = (Phone) aClass.newInstance();
	        System.out.println(t);
	        Field[] f = t.getClass().getDeclaredFields();
	        for (Field field : f) {
	        	System.out.println(field.getName());
	        }

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}