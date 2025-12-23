import java.util.*;

class Zaposleni extends Thread{
	String ime, prezime;
	int godine, cijena;
	ArrayList<String> zadaci;
	
	boolean radi;
	int vrijemePauze;
	
	static int redniBroj = 1;
	
	public Zaposleni(){
		ime = "Ime" + redniBroj;
		prezime = "Prezime" + redniBroj;
		godine = (new Random()).nextInt(5) + 1;
		cijena = (new Random()).nextInt(54) + 1;
		zadaci = new ArrayList<>();
		radi = false;
		vrijemePauze = (new Random()).nextInt(5, 21);
		redniBroj++;
	}
	
}