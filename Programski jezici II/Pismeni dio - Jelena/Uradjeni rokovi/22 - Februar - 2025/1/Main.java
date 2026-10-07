import java.util.*;
import java.io.*;

class Main{
	static Map<String, Klijent> klijenti = new HashMap<>();
	static Banka banka = new Banka();
	static Random rand = new Random();
	
	public static void main(String []args){
		try(BufferedReader bf = new BufferedReader(new FileReader("racuni.txt"))){
			bf.readLine();
			String line = "";
			while((line = bf.readLine()) != null){
				String[] podaci = line.split(";");
				String imeKlijenta = podaci[0];
				String idRacuna = podaci[1];
				int iznos = Integer.parseInt(podaci[2]);
				Racun r = new Racun(idRacuna, imeKlijenta, iznos);
				if(klijenti.get(imeKlijenta) == null){
					klijenti.put(imeKlijenta, new Klijent(imeKlijenta, r));
				}else{
					klijenti.get(imeKlijenta).racuni.add(r);
				}
				banka.dodajRacun(imeKlijenta, r);
				
			}
		}catch(IOException e){
			System.out.println("GRESKA pri citanju iz fajla racuni.txt");
		}
		
		System.out.println("\n===================== STANJE NA RACUNIMA =====================");
		klijenti.forEach((ime, klijent) -> {
			System.out.println(klijent);
		});
		System.out.println("\n");
		
		try(BufferedReader bf = new BufferedReader(new FileReader("transakcije.txt"))){
			bf.readLine();
			String line = "";
			while((line = bf.readLine()) != null){
				String[] podaci = line.split(";");
				String vrsta = podaci[0];
				String klijent1 = podaci[1];
				int iznos = Integer.parseInt(podaci[2]);
				String klijent2 = "";
				if(vrsta.equals("p")){
					klijent2 = podaci[3];
				}
				Transakcija t = new Transakcija(vrsta, klijent1, iznos, klijent2);
				banka.transakcije.add(t);
				
			}
		}catch(IOException e){
			System.out.println("GRESKA pri citanju iz fajla racuni.txt");
		}
		
		banka.izvrsiTransakcije();
		
		System.out.println("\n===================== STANJE NA RACUNIMA NAKON TRANSAKCIJA =====================");
		klijenti.forEach((ime, klijent) -> {
			System.out.println(klijent);
		});
		
	}
}