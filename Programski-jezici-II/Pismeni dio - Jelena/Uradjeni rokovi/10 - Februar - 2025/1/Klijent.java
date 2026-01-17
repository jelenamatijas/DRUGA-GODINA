import java.util.*;

class Klijent{
	String imeKlijenta;
	List<Racun> racuni;
	
	public Klijent(String imeKlijenta, Racun racun){
		this.imeKlijenta = imeKlijenta;
		racuni = new ArrayList<>();
		racuni.add(racun);
	}
	
	@Override
	public String toString(){
		String r = "";
		for(Racun a: racuni){ 
			r+="\n\t" + a.toString();
		}
		return "Klijent -> " + imeKlijenta + " Stanje na racunu:" + r;
	}
}