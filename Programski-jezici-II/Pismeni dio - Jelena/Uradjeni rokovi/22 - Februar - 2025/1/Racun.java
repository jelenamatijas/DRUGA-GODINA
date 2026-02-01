import java.util.*;

class Racun{
	String id;
	String imeVlasnika;
	int stanje;
	
	public Racun(String id, String imeVlasnika, int stanje){
		this.id = id;
		this.imeVlasnika = imeVlasnika;
		this.stanje = stanje;
	}
	
	@Override
	public String toString(){
		return "Racun -> ID racuna: " + id + " Ime vlasnika: " + imeVlasnika + " Stanje: " + stanje;
	}
}