class Feniks extends Egipatska implements FeniksInterface{
	String osobine;
	
	Feniks(String ime, double snaga, int x, int y){
		super(ime, snaga,x,y);
		osobine = "ima krila";
	}
	
	@Override
	public String toString(){
		return "Feniks " + ime + ", kategorija - egipatska(snaga: " + snaga + "). Osobine: " + osobine + ".";
	}
}