public class Komponenta {
	
	String id;
	String proizvodjac;
	int godinaProizvodnje;
	
	private static int redniBroj = 1;
	
	public Komponenta() {
		id = "Id" + redniBroj;
		proizvodjac = "Proizvodjac" + redniBroj;
		godinaProizvodnje = Main.random.nextInt(5) + 2020;
		redniBroj++;
	}
}