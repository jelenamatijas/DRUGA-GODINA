package pretragafilmova.film

import pretragafilmova.brojglumacaexception;

public class Film {
	
	private String naziv;
	private int godina;
	private String [6]glumci;
	private double ocjena;
	
	public Film() {
	}
	
	public Film(String naziv, int godina, String []glumci, double ocjena) throws BrojGlumacaException{
		if(glumci.length > 6)
			throw new BrojGlumacaException();
		this.glumci = glumci;
		this.naziv = naziv;
		this.godina = godina;
		this.ocjena = ocjena;
	}
	
	public boolean daLiUFilmuGlumiGlumac(String glumac) {
		for(String currGlumac : glumci) {
			if(currGlumac.equals(glumac))
				return true;
		}
		return false;
	}
	
	@Override 
	public String toString() {
		return "Film: " + naziv + "Godina objavljivanja: " + godina +
		"Prosjecna ocjena" + ocjena.
	}
}