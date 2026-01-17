import java.util.*;

class Kamion{
	String sifra;
	int maxTezina;
	double zapremina;
	double popunjenostZapremine;
	int popunjenostTezine;
	TipKamiona tip;
	ArrayList<Kutija> kutije;
	
	public Kamion(String sifra, int maxTezina, double zapremina, TipKamiona tip){
		this.sifra = sifra;
		this.maxTezina = maxTezina;
		this.zapremina = zapremina;
		this.tip = tip;
		kutije = new ArrayList<Kutija>();
		popunjenostZapremine = 0;
		popunjenostTezine = 0;
	}
	
	@Override
	public String toString(){
		String s = "";
		if(kutije.size() != 0){
			s = "\n\tKutije:";
			for(Kutija k: kutije){
				s += "\n\t\t" + k.toString();
			}
		}
		return "KAMION -> Sifra: " + sifra + " Max Tezina: " + maxTezina + " Zapremina: " + zapremina + " Tip: " + tip + s;
	}
	
	public enum TipKamiona{
		STANDARD, REFRIGERATED;
	}
}