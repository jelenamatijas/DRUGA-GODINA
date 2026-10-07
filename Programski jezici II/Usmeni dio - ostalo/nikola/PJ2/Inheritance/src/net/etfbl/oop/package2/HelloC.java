package net.etfbl.oop.package2;

import net.etfbl.oop.package1.Hello;

public class HelloC extends Hello {
	public HelloC() {
		System.out.println("HelloC");
	}

	@Override
	public void hello() {
		System.out.println("hello C");
	}

}
