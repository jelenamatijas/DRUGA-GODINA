public class Vozilo {
	
	public String id;
	public String proizvodjac;
	
	private static int redniBroj = 1;
	
	public Vozilo(String proizvodjac) {
		this.id = "Id" + redniBroj;
		this.proizvodjac = proizvodjac;
		redniBroj++;
	}
	
	@Override
	public String toString() {
		return "Vozilo{id=" + this.id + ", proizvodjac=" + this.proizvodjac + "}";
	}
}