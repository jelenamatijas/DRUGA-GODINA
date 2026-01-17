class Cudoviste extends Egipatska implements CudovisteInterface{
	String osobine;
	
	Cudoviste(String ime, double snaga, int x, int y){
		super(ime, snaga,x,y);
		osobine = "ima glavu krokodila, tijelo leoparda i nilskog konja";
	}
	
	@Override
	public String toString(){
		return "Cudoviste " + ime + ", kategorija - egipatska(snaga: " + snaga + "). Osobine: " + osobine + ".";
	}
}