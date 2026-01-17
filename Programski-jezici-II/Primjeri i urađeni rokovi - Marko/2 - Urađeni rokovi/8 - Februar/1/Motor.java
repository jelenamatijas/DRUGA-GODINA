public class Motor extends Komponenta {
	
	int snaga; //kW
	
	public Motor() {
		super();
		this.snaga = Main.random.nextInt(10) + 1;
	}
	
	@Override
	public String toString() {
		return "Motor{id=" + id + ", proizvodjac=" + proizvodjac + ", godinaProizvodnje=" + godinaProizvodnje + ", snaga=" + snaga + "}";
	}
}