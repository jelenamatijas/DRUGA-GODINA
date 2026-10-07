package net.etfbl.oop.package1;

public class ExtendedA extends Base {
	private String e;
	protected String f;
	public String g;
	String h;
	
	public ExtendedA() {
		//a = ""; 	the field a is not visible
		b = c = d = e = f = g = h = "x";
		System.out.println("Constructor ExtendedA()");
	}
	
	private void methodE(){
		System.out.println("methodE in ExtendedA " + e);
	}
	
	protected void methodF(){
		System.out.println("methodF in ExtendedA " + f);
	}
	
	public void methodG(){
		System.out.println("methodG in ExtendedA " + g);
	}
	
	void methodH(){
		System.out.println("methodH in ExtendedA " + h);
	}
	
	protected void methodB(){ // redefinisanje metode methodB is Base
		System.out.println("methodB in ExtendedA " + b);
	}
	
	@Override
	public ExtendedA getNew() { // kovarijantni povratni tip
		return new ExtendedA();
	}
	
	//private void staticMethodA() { // This instance method cannot override the static method from Base
	//}

	//public void finalMethodA() { // Cannot override the final method from Base
	//}
	
	public static void main(String[] args) {
		ExtendedA extendedA = new ExtendedA();
		// extendedA.methodA(); method methodA from the type Base is not visible
		extendedA.methodB();
		extendedA.methodC();
		extendedA.methodD();
		Base b = new ExtendedA(); // b je tipa Base, u runtime-u referencira objekat klase ExtendedA
		b.methodB();
		//b.methodE(); // method methodE is undefined for the type Base 
		Base.staticMethodA();
	}
	
//	@Override
	public B returnObjectB() {
		return new B();
	}
	
}
