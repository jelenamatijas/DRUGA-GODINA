package org.unibl.etf.clientapp;

import org.unibl.etf.myapp.HelloWorld;
import org.unibl.etf.myapp.extended.HelloWorldExtended;
import org.unibl.etf.util.Util;

public class HelloWorldClient {
	public static void main(String arg[]) {
		HelloWorld hello = new HelloWorld();
		System.out.println(hello.sayHelloWorld());
		HelloWorldExtended helloAdv = new HelloWorldExtended();
		System.out.println(helloAdv.sayHello("Extended"));
		System.out.println(hello.sayHelloWorldUpperCase());
		String worldUpper = Util.upperCase("World!");
		System.out.println(helloAdv.sayHello(worldUpper));
	}
}
