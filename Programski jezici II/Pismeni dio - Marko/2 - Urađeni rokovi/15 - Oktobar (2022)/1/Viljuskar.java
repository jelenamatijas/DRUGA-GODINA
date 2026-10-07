public class Viljuskar extends Vozac implements ViljuskarInterface {
	
	public Viljuskar() {
		super();
	}
	
	@Override
	public String toString() {
		return "Viljuskar{ime=" + ime + ", prezime=" + prezime + ", godinaRodjenja=" + godinaRodjenja + "}";
	}
}