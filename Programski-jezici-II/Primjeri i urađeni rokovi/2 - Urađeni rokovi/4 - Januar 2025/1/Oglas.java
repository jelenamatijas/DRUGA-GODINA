import java.util.ArrayList;

public class Oglas {
	
	public String naslov;
	public String opis;
	public ArrayList<String> kljucneRijeci = new ArrayList<>();
	
	private static int redniBroj = 1;
	private static int redniBrojRijeci = 1;
	
	public Oglas() {
		this.naslov = "Naslov" + redniBroj;
		this.opis = "Opis" + redniBroj;
		redniBroj++;
		
		this.kljucneRijeci.add("Rijec" + redniBrojRijeci++);
		this.kljucneRijeci.add("Rijec" + redniBrojRijeci++);
		this.kljucneRijeci.add("Rijec" + redniBrojRijeci++);
	}
}