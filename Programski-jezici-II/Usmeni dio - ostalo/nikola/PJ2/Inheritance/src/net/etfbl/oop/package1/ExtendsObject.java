package net.etfbl.oop.package1;

public class ExtendsObject {
	public void method(){
		System.out.println("method");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "string reprezentacija objekta...";
	}
	
	public static void main(String[] args) {
		ExtendsObject eo = new ExtendsObject();
		eo.method();
		System.out.println(eo);
		System.out.println(eo.hashCode());
	}
}
