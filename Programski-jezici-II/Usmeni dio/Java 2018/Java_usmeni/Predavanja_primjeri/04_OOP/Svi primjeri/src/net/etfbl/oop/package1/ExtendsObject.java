package net.etfbl.oop.package1;

public class ExtendsObject {
	public void method(){
		System.out.println("method");
	}
	
	public static void main(String[] args) {
		ExtendsObject eo = new ExtendsObject();
		eo.method();
		System.out.println(eo.toString());
		System.out.println(eo.hashCode());
	}
}
