public class Artikal {
	
	String naziv;
	double cijena;
	String barkod;
	double kolicina;
	
	public static int redniBroj = 1;
	
	public Artikal() {
		naziv = "naziv" + redniBroj;
		cijena = Main.random.nextInt(10) + Main.random.nextDouble();
		barkod = "barkod" + redniBroj++;
		kolicina = Main.random.nextInt(10) + Main.random.nextDouble();
	}
	
	@Override
	public String toString() {
		return "Artikal{naziv=" + naziv + ", cijena=" + cijena + ", barkod=" + barkod + ", kolicina=" + kolicina + "}";
	}
}