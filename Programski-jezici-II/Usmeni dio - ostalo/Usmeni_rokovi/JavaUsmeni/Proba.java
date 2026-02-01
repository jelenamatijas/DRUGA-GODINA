public class Proba {
	{
		System.out.println("Ispis obicnog bloka");
		System.out.println(pom);
	}
	static {
		pom = 2;
		System.out.println("Ispis statickog bloka");
		//System.out.println(pom);
		
	}
	static int pom;
	public static void main(String[] args) {
		System.out.println("Ispis main");
		new Proba();
		new A();
	}
}
public class A implements IA {
	B b = new B();
	IA ia = new A();
	public A() {
		System.out.println("Klasa A");
	}
	void metoda() {}
}
interface IA {
	public void metoda() {}
}
public class B {
	public B() {
		System.out.println("Klasa B");
	}
}
