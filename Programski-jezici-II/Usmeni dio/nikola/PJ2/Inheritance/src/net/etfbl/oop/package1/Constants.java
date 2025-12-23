package net.etfbl.oop.package1;

interface Constants1 extends Constants2 {
	int ONE = 1, TWO = 2;
}

interface Constants2 {
	int ONE = 111, TWO = 222;
}

public class Constants  {
	public void method() {
		System.out.println(Constants1.ONE);
		System.out.println(Constants2.ONE);
	}

	public static void main(String[] args) {
		X x = new X();
		x.method();
	}
}