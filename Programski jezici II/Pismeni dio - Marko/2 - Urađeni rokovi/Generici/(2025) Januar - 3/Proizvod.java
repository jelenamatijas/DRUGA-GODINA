public class Proizvod {
	
	int ID;
	String naziv;
	int kolicina;
	
	private static int redniBroj = 1;
	
	public Proizvod() {
		ID = redniBroj;
		naziv = "Naziv" + redniBroj;
		kolicina = Main.random.nextInt(60);
	}
	
	@Override
	public String toString() {
		return "Proizvod{ID=" + ID + ", naziv=" + naziv + ", kolicina=" + kolicina + "}";
	}
}