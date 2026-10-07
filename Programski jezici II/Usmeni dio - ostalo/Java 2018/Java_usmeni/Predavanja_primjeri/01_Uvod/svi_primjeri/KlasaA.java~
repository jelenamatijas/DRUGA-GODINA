

public class KlasaA {
	//private Finalize finalize = new Finalize();
	private KlasaB klasaB = new KlasaB();
	private int i;
	private float f;
	private String s;
	private double niz[] = new double[100_000_000];
	
	static {
		System.out.println("KlasaA staticki inicijalizacioni blok");
	}
	{
		System.out.println("KlasaA nestaticki inicijalizacioni blok");
	}
	KlasaA() {
		super();
		klasaB.metoda();
		System.out.println("konstruktor KlasaA");
	}

	protected void finalize() throws Throwable {
		System.out.println("finalize");
	}

	public static void main(String args[]) {
		KlasaA a = new KlasaA();
		a = null;
		System.gc();
		System.out.println("main");
		KlasaA a2 = new KlasaA();
	}
}

class KlasaB{
	static {
		System.out.println("KlasaB staticki inicijalizacioni blok");
	}
	{
		System.out.println("KlasaB nestaticki inicijalizacioni blok");
	}
	
	public KlasaB() {
		System.out.println("konstruktor KlasaB...");
	}
	
	public void metoda(){
		System.out.println("metoda iz KlasaB...");
	}
}