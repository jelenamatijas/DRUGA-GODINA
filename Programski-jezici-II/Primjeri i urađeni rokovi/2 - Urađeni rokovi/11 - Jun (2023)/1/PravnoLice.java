import java.util.*;
import java.io.*;

public class PravnoLice extends Osoba implements Serializable {
	
	String jib;
	String nazivFirme;
	ArrayList<String> vozila = new ArrayList<>();
	
	public PravnoLice() {
		super();
		jib = "JIB" + Osoba.redniBroj;
		nazivFirme = "NazivFirme" + Osoba.redniBroj++;
		
		int brojVozila = Main.random.nextInt(5) + 1;
		for (int i = 0; i < brojVozila; i++) {
			int randomVozilo = Main.random.nextInt(4);
			if (randomVozilo == 0) {
				vozila.add("MOTOR");
			} else if (randomVozilo == 1) {
				vozila.add("AUTOMOBIL");
			} else if (randomVozilo == 2) {
				vozila.add("AUTOBUS");
			} else {
				vozila.add("KAMION");
			}
		}
	}
	
	@Override
	public String toString() {
		return "\nPravnoLice{ime=" + ime + ", prezime=" + prezime + ", novac=" + novac + "KM, jib=" + jib + ", nazivFirme=" + nazivFirme + ", vozila=" + vozila + "}";
	}
}