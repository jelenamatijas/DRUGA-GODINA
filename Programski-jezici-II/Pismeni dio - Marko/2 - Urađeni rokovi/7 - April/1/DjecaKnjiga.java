public class DjecaKnjiga extends Knjiga {
	
	public int preporucenaGodinaStarosti;
	
	public DjecaKnjiga() {
		super();
		this.preporucenaGodinaStarosti = Main.random.nextInt(17) + 1;
	}
	
	@Override
	public String toString() {
		return "DjecaKnjiga{naslov=" + this.naslov + ", autor=" + this.autor + ", brojStranica=" + this.brojStranica + ", godinaIzdavanja=" + this.godinaIzdavanja + ", preporucenaGodinaStarosti=" + this.preporucenaGodinaStarosti + "}";
	}
}