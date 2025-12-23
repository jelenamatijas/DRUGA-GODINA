package net.etfbl.oop.package1;

public class ExtendedWithSuper extends Base{

	public ExtendedWithSuper() {
		System.out.println("Constructor ExtendedWithSuper()");
	}
	
	@Override
	protected void methodB() {
		super.methodB();
		System.out.println("methodB in ExtendedWithSuper");
	}
	
	public static void main(String[] args) {
		ExtendedWithSuper ews = new ExtendedWithSuper();
		ews.methodB();
		ews.setA("a");
	}
}

