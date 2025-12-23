package net.etfbl.exception;

public class ExceptionTest1 {

	public static void main(String[] args) throws Exception {
		ExceptionTest1 test = new ExceptionTest1();
//		try {
			test.method1();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.out.println("poruka...");
	}

	
	public void method1() throws Exception{
			method2();
	}
	
	public void method2() throws Exception{
		method3();
	}
	
	public void method3() throws Exception{
		throw new Exception();
	}
}