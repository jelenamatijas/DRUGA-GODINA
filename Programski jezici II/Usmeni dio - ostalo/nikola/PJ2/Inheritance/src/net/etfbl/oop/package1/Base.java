package net.etfbl.oop.package1;

public class Base {
	private String a;
	protected String b;
	public String c;
	String d;

	public Base() {
		a = "a";
		b = "b";
		c = "c";
		d = "d";
		System.out.println("Constructor Base()");
	}
	
	public void setA(String a){
		this.a = a;
	}
	private void methodA(){
		System.out.println("methodA in Base " + a);
	}
	
	protected void methodB(){
		System.out.println("methodB in Base " + b);
	}
	
	public void methodC(){
		System.out.println("methodC in Base " + c);
	}
	
	void methodD(){
		System.out.println("methodD in Base " + d);
	}
	
	public Base getNew(){
		return new Base();
	}
	
	public static void staticMethodA(){
		System.out.println("staticMethodA in Base");
	}
	
	public final void finalMethodA(){
		System.out.println("finalMethodA in Base");
	}
	
	public A returnObjectA(){
		return new A();
	}
}
