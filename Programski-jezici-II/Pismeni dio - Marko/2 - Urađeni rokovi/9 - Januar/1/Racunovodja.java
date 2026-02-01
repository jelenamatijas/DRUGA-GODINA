import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Racunovodja extends Zaposleni {
	
	ArrayList<Zaposleni> radnici;
	
	public Racunovodja(ArrayList<Zaposleni> radnici) {
		this.radnici = radnici;
	}
	
	private boolean sviRadniciUradili10Zadataka() {
		return this.radnici.stream().allMatch(zaposleni -> zaposleni.uradjeniZadaci.size() == 10);
	}
	
	public void upisiUTxtFajl() {
		try (PrintWriter pw = new PrintWriter(new FileWriter("txtFajl.txt", true))) {
			for (int i = 0; i < this.radnici.size(); i++) {
				pw.println(this.radnici.get(i).ime + " " + this.radnici.get(i).prezime + ", iznos: " + (this.radnici.get(i).godineRada + this.radnici.get(i).cijenaRada));
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom upisa u txt fajl");
			return;
		}
	}
	
	@Override
	public void run() {
		radi = true;
		System.out.println(this + " POCINJE sa radom!");
		
		int n = 1;
		int prosloVrijeme = 0;
		
		while (radi) {
			String zadatak = "Obracun troskova #" + n;
			System.out.println(zadatak);
			uradjeniZadaci.add(zadatak);
			try {
				for (int i = 0; i < 10; i++) {
					sleep(1000);
					prosloVrijeme++;
					if (prosloVrijeme == vrijemePauze) {
						System.out.println(this + " otisao na PAUZU");
						sleep(5000);
						System.out.println(this + " se VRATIO sa pauze i nastavlja rad");
					}
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			n++;
			
			if (this.sviRadniciUradili10Zadataka()) {
				this.upisiUTxtFajl();
				synchronized (Main.lockObject) {
					Main.lockObject.notifyAll();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "\nRacunovodja{ime=" + ime + ", prezime=" + prezime + ", godineRada=" + godineRada + ", cijenaRada=" + cijenaRada + ", obracuniTroskova=" + uradjeniZadaci + ", radnici=" + radnici + "}";
	}
}