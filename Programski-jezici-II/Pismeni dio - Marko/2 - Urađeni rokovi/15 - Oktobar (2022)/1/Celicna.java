public class Celicna extends Sajla {
	
	public Celicna() {
		super();
		this.vjerovatnocaOstecenja = 7;
	}
	
	@Override
	public String toString() {
		return "CelicnaPloca{id=" + id + ", statusOstecenja=" + statusOstecenja + ", tezina=" + tezina + "}";
	}
}