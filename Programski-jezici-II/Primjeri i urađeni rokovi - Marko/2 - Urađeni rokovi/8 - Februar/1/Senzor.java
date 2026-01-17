public class Senzor extends Komponenta {
	
	String vrsta; // opticki, ultrazvucni
	int rasponDetekcije;
	
	public Senzor(String vrsta) {
		this.vrsta = vrsta;
		rasponDetekcije = Main.random.nextInt(51) + 50;
	}
	
	@Override
	public String toString() {
		return "Senzor{id=" + id + ", proizvodjac=" + proizvodjac + ", godinaProizvodnje=" + godinaProizvodnje + ", vrsta=" + vrsta + ", rasponDetekcije=" + rasponDetekcije + "}";
	}
}