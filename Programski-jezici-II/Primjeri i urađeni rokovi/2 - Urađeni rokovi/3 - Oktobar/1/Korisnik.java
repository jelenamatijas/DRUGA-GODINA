import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.InterruptedException;

public class Korisnik {
	
	public String ime;
	public String prezime;
	public String godinaRodjenja;
	
	// 0 - sauna, 1 - masaza, 2 - bazen
	public int usluga;
	
	private static int redniBroj = 1;
	
	public Korisnik() {
		this.ime = "Ime" + redniBroj;
		this.prezime = "Prezime" + redniBroj;
		this.godinaRodjenja = "GodinjaRodjenja" + redniBroj;
		redniBroj++;
	}
}