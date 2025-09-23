public class Kamiondzija extends Vozac implements KamionInterface {
	
	public Kamiondzija() {
		super();
	}
	
	@Override
	public String toString() {
		return "Kamiondzija{ime=" + ime + ", prezime=" + prezime + ", godinaRodjenja=" + godinaRodjenja + "}";
	}
}