package net.etfbl.oop.package6;



public class Test implements Interface{

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		
	}
	
//	public abstract void metoda3();
	
}

interface Interface {
	void method1();
	void method2();
//	void method3();
	default void method3() {
	}
	private void method4() {
	}
	
	static void method5() {
		
	}
}
