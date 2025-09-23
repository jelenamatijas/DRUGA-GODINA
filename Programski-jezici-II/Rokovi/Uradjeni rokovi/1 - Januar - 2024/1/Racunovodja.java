import java.util.*;
import java.util.stream.*;
import java.io.*;

class Racunovodja extends Zaposleni{
	ArrayList<Zaposleni> radnici;
	public Racunovodja(ArrayList<Zaposleni> r){
		radnici = r;
	}
	
	private boolean sviUradili10Zadataka(){
		return this.radnici.stream().allMatch(radnici -> radnici.zadaci.size() == 10);
	}
	
	private void writeFile(){
		try(PrintWriter pw = new PrintWriter(new FileWriter("fajl.txt"))){
			for(int i=0;i<radnici.size();i++){
				pw.println("Ime: " + radnici.get(i).ime + " Prezime: " + radnici.get(i).prezime + " Cijena rada: " + (radnici.get(i).cijena + radnici.get(i).godine));
			}
		}catch(IOException e){
			System.out.println("Greska pri upisu u fajl.");
			return;
		}
	}
	
	@Override
	public void run(){
		radi = true;
		System.out.println(this + " pocije sa radom.");
		
		int n = 1;
		int prosloVrijeme = 0;
		
		while(radi){
			try{
				String zadatak = "Obracun troskova #" + n;
				System.out.println(zadatak);
				zadaci.add(zadatak);
				for(int i=0;i<10;i++){
					sleep(1000);
					prosloVrijeme++;
					if(prosloVrijeme == vrijemePauze){
						System.out.println(this + " otisao na pauzu.");
						sleep(5000);
						System.out.println(this + " vratio se sa pauze. Nastavlja s radom.");
					}
					
				}
				n++;
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			if(sviUradili10Zadataka()){
				writeFile();
				synchronized(Main.lock) {
					try{
						Main.lock.notifyAll();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public String toString(){
		return "Racunovodja{" + ime + " " + prezime + " Godine rada: " + godine + " Cijena rada: " + cijena + " Upiti za nabavku: " + zadaci + "}\n";
	}
}