public class Baterija extends Komponenta {
	
	int kapacitet;
	
	public Baterija() {
		super();
		this.kapacitet = Main.random.nextInt(100) + 1;
	}
	
	@Override
	public String toString() {
		return "Motor{id=" + id + ", proizvodjac=" + proizvodjac + ", godinaProizvodnje=" + godinaProizvodnje + ", kapacitet=" + kapacitet + "}";
	}
}