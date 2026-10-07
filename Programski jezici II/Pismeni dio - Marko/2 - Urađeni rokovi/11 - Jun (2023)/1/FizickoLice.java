import java.io.*;

public class FizickoLice extends Osoba implements Serializable  {
	
	String jmbg;
	String vozilo;
	
	public FizickoLice() {
		super();
		jmbg = "JMBG" + Osoba.redniBroj++;
		
		int randomVozilo = Main.random.nextInt(4);
		if (randomVozilo == 0) {
			vozilo = "MOTOR";
		} else if (randomVozilo == 1) {
			vozilo = "AUTOMOBIL";
		} else if (randomVozilo == 2) {
			vozilo = "AUTOBUS";
		} else {
			vozilo = "KAMION";
		}
	}
	
	@Override
	public String toString() {
		return "\nFizickoLice{ime=" + ime + ", prezime=" + prezime + ", novac=" + novac + "KM, jmbg=" + jmbg + ", vozilo=" + vozilo + "}";
	}
}