import java.util.*;

class Main{
	static Object lock = new Object();
	
	public static void main(String args[]){
		long pocetak = System.currentTimeMillis();
		RadnikNabavke rn = new RadnikNabavke();
		RadnikProdaje rp = new RadnikProdaje();
		
		ArrayList<Zaposleni> r = new ArrayList<>();
		r.add(rn);
		r.add(rp);
		
		Racunovodja rr = new Racunovodja(r);
		
		rn.start();
		rp.start();
		rr.start();
		
		String unos = "";
		Scanner scanner = new Scanner(System.in);
		while(!unos.equalsIgnoreCase("kraj")){
			unos = scanner.nextLine();
		}
		
		rn.radi = false;
		rp.radi = false;
		rr.radi = false;
		
		try{
			rn.join();
			rp.join();
			rr.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.println("Broj uradjenih zadataka radnika nabavke: " + rn.zadaci.size());
		System.out.println("Broj uradjenih zadataka radnika prodaje: " + rp.zadaci.size());
		System.out.println("Broj uradjenih zadataka racunovodje: " + rr.zadaci.size());
		long kraj = System.currentTimeMillis();
		System.out.println("Vrijeme trajanja simulacije: " + (double)(kraj - pocetak)/1000 + "s.");
	}
}