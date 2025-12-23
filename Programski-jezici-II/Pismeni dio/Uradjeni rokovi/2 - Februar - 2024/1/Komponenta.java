class Komponenta{
	String proizvodjac;
	int id;
	String godinaProizvodnje;
	
	static int ID = 1;
	
	public Komponenta(){
		proizvodjac = "Proizvodjac: " + Main.rand.nextInt(10) + 1;
		id = ID++;
		godinaProizvodnje = "Godina proizvodnje: " + Main.rand.nextInt(2000, 2025);
	}
	
}