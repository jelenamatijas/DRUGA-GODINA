class Motor extends Komponenta{
	String snaga;
	
	public Motor(){
		super();
		snaga = (Main.rand.nextInt(40)+20) + "kW";
	}
	
	@Override
	public String toString(){
		return "Senzor{ID" + id + " Snaga: " + snaga +  " Proizvodjac: " + proizvodjac + " Godina proizvodnje: " + godinaProizvodnje + "}";
	}
}