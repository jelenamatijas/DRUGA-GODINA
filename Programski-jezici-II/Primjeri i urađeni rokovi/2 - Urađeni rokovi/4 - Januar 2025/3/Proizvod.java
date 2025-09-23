public class Proizvod {
	
	public String id;
	public String naziv;
	public double kolicina;
	
	private static int redniBroj = 1;
	
	public Proizvod(double kolicina) {
		this.id = "Id" + redniBroj;
		this.naziv = "Naziv" + redniBroj;
		this.kolicina = kolicina;
		redniBroj++;
	}
	
	@Override
	public String toString() {
		return "Proizvod{id=" + this.id + ", naziv=" + this.naziv + ", kolicina=" + this.kolicina + "}";
	}
	
}