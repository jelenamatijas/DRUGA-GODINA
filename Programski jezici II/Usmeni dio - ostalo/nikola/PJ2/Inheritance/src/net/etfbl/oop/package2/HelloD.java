package net.etfbl.oop.package2;

import net.etfbl.oop.package1.Hello;

public class HelloD extends Hello {
	public HelloD() {
		System.out.println("HelloD");
	}

	@Override
	public void hello() {
		System.out.println("hello D");
	}

}
