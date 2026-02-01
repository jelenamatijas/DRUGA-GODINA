package net.etfbl.oop.package2;

import net.etfbl.oop.package1.Hello;

public class HelloE extends Hello {

	public HelloE() {
		System.out.println("HelloE");
	}
	@Override
	public void hello() {
		System.out.println("hello E");
	}

}
