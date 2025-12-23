import java.util.*;

class Film{
	String naziv;
	String reziser;
	int godina;
	Zanr zanr;
	int trajanje;
	double ocjena;
	
	public Film(Zanr z){
		Random rand = new Random();
		naziv = "Naziv" + rand.nextInt(10)+1;
		reziser = "Reziser" + rand.nextInt(10)+1;
		godina = rand.nextInt(25) + 2000;
		zanr = z;
		trajanje = rand.nextInt(120)+90;
		ocjena = rand.nextDouble(0,10);
	}
	
	public Zanr getZanr(){
		return zanr;
	}
	
	public int getTrajanje(){
		return trajanje;
	}
	
	public double getOcjena(){
		return ocjena;
	}
	
	public enum Zanr{
		AKCIJA, KOMEDIJA, DRAMA, HOROR, NAUCNA_FANTASTIKA;
	}
	
	
	
	@Override
	public String toString(){
		return "FILM{" + naziv + " " + reziser + " " + zanr + " Godina izdavanja: " + godina + " Trajanje: " + trajanje + "}";
	}
}