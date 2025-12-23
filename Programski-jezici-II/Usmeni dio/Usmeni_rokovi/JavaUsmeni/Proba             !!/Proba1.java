public class Proba1{
	public static void main(String[] args){
		A a = new A();
		B b = new B();
		B b1 = new B();
		A a2 = new B();
		B a1 = (B)a2;
		if(a1 instanceof B)
			System.out.println("a1 je instanca B");
		if(a instanceof B)
			System.out.println("a je instanca B");
		if(b1 instanceof A)
			System.out.println("b1 je instanca A");
		if(a1 instanceof A)
			System.out.println("a1 je instanca A");
	}
}
class A{
	public void metoda(){System.out.println("Metoda a");}
}
class B extends A{
	public void metoda(){System.out.println("Metoda b");}
}