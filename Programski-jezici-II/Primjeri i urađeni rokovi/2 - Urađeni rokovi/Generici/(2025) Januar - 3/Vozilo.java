public class Vozilo {
	
	int ID;
	String proizvodjac;
	
	private static int redniBroj = 1;
	
	public Vozilo(String proizvodjac) {
		ID = redniBroj++;
		this.proizvodjac = proizvodjac;
	}
	
	@Override
	public String toString() {
		return "Vozilo{ID=" + ID + ", proizvodjac=" + proizvodjac + "}";
	}
}