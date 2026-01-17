public class Laptop {
	
	String naziv;
	String sifra;
	int kolicina;
	double cijena;
	
	public Laptop() {
		super();
	}
	
	@Override
	public String toString() {
		return "Laptop{naziv=" + naziv + ", sifra=" + sifra + ", kolicina=" + kolicina + ", cijena=" + cijena + "}";
	}
}