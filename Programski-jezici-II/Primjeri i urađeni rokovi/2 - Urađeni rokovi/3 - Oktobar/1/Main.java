import java.util.ArrayList;
import java.util.Random;
import java.lang.InterruptedException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	public static Random random = new Random();
	
	public static ArrayList<Korisnik> saunaKorisnici = new ArrayList<>();
	public static ArrayList<Korisnik> masazaKorisnici = new ArrayList<>();
	public static ArrayList<Korisnik> bazenKorisnici = new ArrayList<>();
	
	public static ArrayList<ObicniKorisnik> obicniKorisnici = new ArrayList<>();
	
	private static void rasporedKorisnika() {
		ArrayList<Korisnik> sviKorisnici = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			sviKorisnici.add(new ObicniKorisnik());
		}
		
		for (int i = 0; i < 15; i++) {
			sviKorisnici.add(new FirmaKorisnik());
		}
		// Dodavanje korisnika za saunu
		ArrayList<Korisnik> saunaObicniKorisnici = new ArrayList<>();
		
		int j = 30;
		for (int i = 0; i < 10; ) {
			int randomIndex = random.nextInt(j);
			if (sviKorisnici.get(randomIndex) instanceof FirmaKorisnik) {
				sviKorisnici.get(randomIndex).usluga = 0;
				saunaKorisnici.add(sviKorisnici.get(randomIndex));
				sviKorisnici.remove(randomIndex);
				i++;
				j--;
			}
			else if (sviKorisnici.get(randomIndex) instanceof ObicniKorisnik) {
				sviKorisnici.get(randomIndex).usluga = 0;
				saunaObicniKorisnici.add(sviKorisnici.get(randomIndex));
				sviKorisnici.remove(randomIndex);
				i++;
				j--;
			}
		}
		
		// Dodavanje obicnih korisnika nakon firma korisnika
		for (int i = 0; i < saunaObicniKorisnici.size(); i++) {
			saunaKorisnici.add(saunaObicniKorisnici.get(i));
		}
		
		// Dodavanje korisnika za masazu
		for (int i = 0; i < 5; i++) {
			int randomIndex = random.nextInt(j);
			sviKorisnici.get(randomIndex).usluga = 1;
			masazaKorisnici.add(sviKorisnici.get(randomIndex));
			sviKorisnici.remove(randomIndex);
			j--;
		}
		
		// Dodavanje korisnika za bazen
		for (int i = 14; i >= 0; i--) {
			sviKorisnici.get(i).usluga = 2;
			bazenKorisnici.add(sviKorisnici.get(i));
			sviKorisnici.remove(i);
		}
		
	}
	
	private static void zapisiUDatoteku(ObicniKorisnik obicniKorisnik) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(obicniKorisnik.ime + "_" + obicniKorisnik.prezime + ".txt"))) {
			pw.write("Otakazana sauna jer nema kupon, kupon=" + obicniKorisnik.kupon);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		rasporedKorisnika();
		
		// Kreiraju se tri niti koje paralelno rade sa korisnicima različitih usluga, nema potrebe da nijedna klasa nasljeđuje Thread
		
		// ALTERNATIVA: Kreirati klasu Usluga koja nasljeđuje Thread i tri podklase vrste usluga, svaka usluga uzima korisnike iz svoje liste ali se sve tri paralelno izvršavaju
		
		// ALTERNATIVA 2: Korisnik nasljeđuje Thread i za svaki tip korisnika postoji poseban lockObject, kada jedan dođe na red, zaključava pristup ostalim koji čekaju istu vrstu usluge dok god ne završi posao, nakon što završi posao, oslobađa objekat i sljedeći iz liste te vrste usluge može ući
		
		Runnable zadatak1 = new Runnable() {
			@Override
			public void run() {
				while (!saunaKorisnici.isEmpty()) {
					Korisnik korisnik = saunaKorisnici.get(0);
					if (korisnik instanceof ObicniKorisnik) {
						ObicniKorisnik obicniKorisnik = (ObicniKorisnik) korisnik;
						if (!obicniKorisnik.kupon) {
							System.out.println(korisnik + " se OTKAZUJE sauna jer nema kupon!");
							zapisiUDatoteku(obicniKorisnik);
						}
						else {
							System.out.println(korisnik + " je USAO u saunu!");
							// Sauna se ne naplacuje
						}
						obicniKorisnici.add(obicniKorisnik);
					}
					else {
						System.out.println(korisnik + " je USAO u saunu!");
					}
					saunaKorisnici.remove(0);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		Thread nit1 = new Thread(zadatak1);
		nit1.start();
		
		Runnable zadatak2 = new Runnable() {
			@Override
			public void run() {
				while (!masazaKorisnici.isEmpty()) {
					Korisnik korisnik = masazaKorisnici.get(0);
					if (korisnik instanceof ObicniKorisnik) {
						ObicniKorisnik obicniKorisnik = (ObicniKorisnik) korisnik;
						obicniKorisnik.novac -= 10.0;
						System.out.println(korisnik + " je USAO na masazu!");
						obicniKorisnici.add(obicniKorisnik);
					}
					else {
						System.out.println(korisnik + " je USAO na masazu!");
					}
					masazaKorisnici.remove(0);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		Thread nit2 = new Thread(zadatak2);
		nit2.start();
		
		Runnable zadatak3 = new Runnable() {
			@Override
			public void run() {
				while (!bazenKorisnici.isEmpty()) {
					Korisnik korisnik = bazenKorisnici.get(0);
					if (korisnik instanceof ObicniKorisnik) {
						ObicniKorisnik obicniKorisnik = (ObicniKorisnik) korisnik;
						obicniKorisnik.novac -= 10.0;
						System.out.println(korisnik + " je USAO na masazu!");
						obicniKorisnici.add(obicniKorisnik);
					}
					else {
						System.out.println(korisnik + " je USAO na masazu!");
					}
					bazenKorisnici.remove(0);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		Thread nit3 = new Thread(zadatak3);
		nit3.start();
		
		try {
			nit1.join();
			nit2.join();
			nit3.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		for (ObicniKorisnik obicniKorisnik : obicniKorisnici) {
			System.out.print(obicniKorisnik.novac + "KM; ");
		}
	}
}