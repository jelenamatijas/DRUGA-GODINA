import java.io.*;
public class Proba{
	
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
	public static void main(String[] args){
		//System.out.println("Ispis main");
		//new Proba();
		new A();
	}
}
class A extends C{
	B b = new B();
	Serializable ic = new C();
	Serializable id = new D();
	public A(){
		System.out.println("Klasa A");
	}
	
}
interface IA{
	 void metoda();
}
class B{
	public B(){
		System.out.println("Klasa B");
	}
}
class Djuka{
	public Djuka(){
		System.out.println("Djuka kloc");
	}
}
class C extends Djuka implements Serializable{
	//C c = new C();
	Stefo kloc = new Stefo();
	{
	System.out.println("neki blok C");
	}
	public C(){
		//System.out.println("prije konsturktora");
		this(1);
		System.out.println("Klasa C");
	}
	public C(int i){
		System.out.println("Klasa C1");
	}
	public void metoda(){int x = 1;}
}class D implements Serializable{
	{
	System.out.println("neki blok D");
	}
	public D(){
		System.out.println("Klasa D");
	}
	public void metoda(){int x = 1;}
}
class Stefo {
	//Stefo s = new Stefo();
	Stefo(){System.out.println("Stefo");}
}