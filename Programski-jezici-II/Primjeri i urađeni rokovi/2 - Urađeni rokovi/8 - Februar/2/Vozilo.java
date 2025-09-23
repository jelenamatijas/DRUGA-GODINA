public class Vozilo {
	
	String naziv;
	String sifra;
	String opis;
	
	public Vozilo() {
		super();
	}
	
	@Override
	public String toString() {
		return "Vozilo{naziv=" + naziv + ", sifra=" + sifra + ", opis=" + opis + "}";
	}
}