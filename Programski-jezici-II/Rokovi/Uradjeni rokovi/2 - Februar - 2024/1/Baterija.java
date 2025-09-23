class Baterija extends Komponenta{
	int kapacitet;
	
	public Baterija(){
		super();
		kapacitet = Main.rand.nextInt(10)+2;
	}
	
	@Override
	public String toString(){
		return "Senzor{ID" + id + " Kapacitet: " + kapacitet +  " Proizvodjac: " + proizvodjac + " Godina proizvodnje: " + godinaProizvodnje + "}";
	}
}