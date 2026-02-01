class Pegaz extends Grcka implements PegazInterface{
	String osobine;
	
	Pegaz(String ime, double snaga, int x, int y){
		super(ime, snaga,x,y);
		osobine = "je konj sa krilima";
	}
	
	public double getSnaga(){return snaga;}
	
	@Override
	public String toString(){
		return "Pegaz " + ime + ", kategorija - grcka(snaga: " + snaga + "). Osobine: " + osobine + ".";
	}
}