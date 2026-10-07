public class Kutija extends Predmet implements OtvaranjeInterface, ZatvaranjeInterface {
	
	String generickiSadrzaj;
	
	private static int redniBroj = 1;
	
	public Kutija() {
		super();
		generickiSadrzaj = "generickiSadrzaj" + redniBroj++;
	}
	
	@Override
	public String toString() {
		return "Kutija{id=" + id + ", statusOstecenja=" + statusOstecenja + ", tezina=" + tezina + ", vjerovatnocaOstecenja=" + vjerovatnocaOstecenja + ", generickiSadrzaj=" + generickiSadrzaj + "}";
	}
}