package net.etfbl.oop.package3;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import net.etfbl.oop.package1.Hello;

public class DynamicLoading {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		String packageName = "net.etfbl.oop.package2";
		File file = new File("D:\\Eclipse\\workspace-oxygen\\JAVA_2018_04\\bin\\net\\etfbl\\oop\\package2");
		String classNames[] = file.list();
		for (String className : classNames) {
			Class cls = classLoader.loadClass(packageName+"."+className.substring(0, className.length()-6)); 
			Hello hello = (Hello) cls.getDeclaredConstructor().newInstance();
			hello.hello();
		}
	}

}
