class Sfinga extends Grcka implements SfingaInterface{
	String osobine;
	
	Sfinga(String ime, double snaga, int x, int y){
		super(ime, snaga,x,y);
		osobine = "ima krila, tijelo lava i glavu covjeka";
	}
	
	@Override
	public String toString(){
		return "Sfinga " + ime + ", kategorija - grcka(snaga: " + snaga + "). Osobine: " + osobine + ".";
	}
}