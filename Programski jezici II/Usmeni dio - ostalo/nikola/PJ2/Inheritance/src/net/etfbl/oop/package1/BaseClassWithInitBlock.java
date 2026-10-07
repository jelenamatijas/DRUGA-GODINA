package net.etfbl.oop.package1;

public class BaseClassWithInitBlock {
	int i;
	{
		System.out.println("BaseClassWithInitBlock - init block");
		i = 10;
	}
	
	public BaseClassWithInitBlock() {
		System.out.println("BaseClassWithInitBlock - constructor");
		i = 11;
	}

	public static void main(String[] args) {
		ExtendedClassWitInitBlock ex = new ExtendedClassWitInitBlock();
		System.out.println(ex.i);
	}
}

class ExtendedClassWitInitBlock extends BaseClassWithInitBlock{
	{
		System.out.println("ExtendedClassWitInitBlock - init block");
		i = 20;
	}
	
	public ExtendedClassWitInitBlock() {
		System.out.println("ExtendedClassWitInitBlock - constructor");
		i = 21;
	}
}
