package net.etfbl.oop.package2;

import net.etfbl.oop.package1.Hello;

public class HelloB extends Hello {
	public HelloB() {
		System.out.println("HelloB");
	}

	@Override
	public void hello() {
		System.out.println("hello B");
	}

}
