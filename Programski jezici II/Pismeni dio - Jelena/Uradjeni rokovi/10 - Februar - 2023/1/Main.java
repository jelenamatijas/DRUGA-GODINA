import java.util.*;

class Main{
	static String putanja;
	static Object lock = new Object();
	static Object lock1 = new Object();
	static Object lock2 = new Object();
	static public void main(String args[]){
		if(args.length != 1){
			System.out.println("Pogresan format pri pokretanju.");
			return;
		}
		
		putanja = args[0];
		List<Uredjaj> uredjaji = new ArrayList<>();
		uredjaji.add(new Skener()); uredjaji.add(new Skener()); uredjaji.add(new Skener());
		uredjaji.add(new Stampac()); uredjaji.add(new Stampac()); uredjaji.add(new Stampac());
		uredjaji.add(new Kopir()); uredjaji.add(new Kopir()); uredjaji.add(new Kopir());
		uredjaji.add(new KopiSkener()); uredjaji.add(new KopiSkener()); uredjaji.add(new KopiSkener());
		uredjaji.add(new Kombo()); uredjaji.add(new Kombo()); uredjaji.add(new Kombo());
		for(Uredjaj u : uredjaji){
			u.start();
		}
	}
}