package net.etfbl.oop.package2;

import net.etfbl.oop.package1.Hello;

public class HelloA extends Hello {
	public HelloA() {
		System.out.println("HelloA");
	}

	@Override
	public void hello() {
		System.out.println("hello A");
	}
	
	public static void main(String []args){
		HelloA a = new HelloA();
		a.hello();
	}

}
