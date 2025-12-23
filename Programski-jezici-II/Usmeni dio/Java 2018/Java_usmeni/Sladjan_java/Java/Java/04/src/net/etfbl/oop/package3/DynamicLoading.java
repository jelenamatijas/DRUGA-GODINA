package net.etfbl.oop.package3;

import java.io.File;

import net.etfbl.oop.package1.Hello;

public class DynamicLoading {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		String packageName = "net.etfbl.oop.package2";
		File file = new File("D:\\Eclipse\\workspace\\PJ2_2015_04\\bin\\net\\etfbl\\oop\\package2");
		String classNames[] = file.list();
		for (String className : classNames) {
			Class cls = classLoader.loadClass(packageName+"."+className.substring(0, className.length()-6)); 
			Hello hello = (Hello) cls.newInstance();
			hello.hello();
		}
	}

}
