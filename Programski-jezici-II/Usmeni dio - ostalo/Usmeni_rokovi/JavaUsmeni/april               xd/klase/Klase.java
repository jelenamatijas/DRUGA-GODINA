import java.io.*;

public class Klase extends A
{
	//public static A a = new A();
	{
		System.out.println("nestatik klase");
	}
	static
	{
		System.out.println("statik klase");
	}
	public Klase(A a){}
	
}
class A 
{
	{
		System.out.println("Nestatik A");
	}
	static
	{
		System.out.println("statik A");
	}
	public static void main(String[] args) throws Throwable
	{
		Glavna prava = new Glavna();
		Glavna glavna = new Izvedena();
		Izvedena izvedene = new Izvedena();
		((Izvedena)glavna).metoda();
		/*try{
		glavna.metoda();}
		catch(Exception ex){
			System.out.println("Uhvacen izuzetak iz glavne klase ");
		}
		izvedene.metoda2();
		I1 i1 = new Izvedena();
		i1.metoda2();
		((Izvedena)glavna).metoda();
		if(glavna instanceof Izvedena)
			System.out.println("1");
		if(izvedene instanceof Glavna)
			System.out.println("2");
		if(prava instanceof Izvedena)
			System.out.println("3");*/
		
	}
}
interface I1{
	void metoda2();
}
class Glavna{
	{
		System.out.println("kekek");
	}
	Glavna(){}
	Glavna(String s){}
	public static Izvedena metoda() throws Exception{
		System.out.println("Metoda iz glavne klase");
		return new Izvedena();
	}
}
class Izvedena extends Glavna implements I1{
	Izvedena(){
		super("aas");
		System.out.println("Konstruktor izvedene klase");
	}
	public static Izvedena metoda() throws IOException {
		System.out.println("Metoda iz izvedene klase");
		return new Izvedena();
	}
	public void metoda2(){
		System.out.println("Metoda 2 iz izvedene klase");
	}
}
