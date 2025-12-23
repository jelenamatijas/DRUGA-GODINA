class Senzor extends Komponenta{
	
	String vrsta;
	int raspon;
	
	public Senzor(String v){
		super();
		vrsta = v;
		raspon = Main.rand.nextInt(20)+5;
	}
	
	@Override
	public String toString(){
		return "Senzor{ID" + id + " Vrsta: " + vrsta +  " Proizvodjac: " + proizvodjac + " Godina proizvodnje: " + godinaProizvodnje + "}";
	}
}