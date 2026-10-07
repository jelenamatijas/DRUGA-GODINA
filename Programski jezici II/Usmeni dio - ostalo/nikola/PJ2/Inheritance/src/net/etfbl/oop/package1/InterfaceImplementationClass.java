package net.etfbl.oop.package1;

public class InterfaceImplementationClass implements InterfaceA{

	@Override
	public void methodA() {
		System.out.println("methodA");
	}

	@Override
	public void methodB() {
		System.out.println("methodB");
	}

	@Override
	public void methodC() {
		System.out.println("methodC");
	}

	@Override
	public void methodX() {
		System.out.println("methodX");
	}

	public static void main(String[] args) {
		InterfaceA iic = new InterfaceImplementationClass();
		iic.methodA();
		iic.methodB();
		iic.methodC();
	}
}
