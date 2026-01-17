public class StrucnaLiteratura extends Knjiga {
	
	public String oblast;
	
	public StrucnaLiteratura() {
		super();
		this.oblast = "Oblast" + (Main.random.nextInt(5) + 1);
	}
	
	@Override
	public String toString() {
		return "StrucnaLiteratura{naslov=" + this.naslov + ", autor=" + this.autor + ", brojStranica=" + this.brojStranica + ", godinaIzdavanja=" + this.godinaIzdavanja + ", oblast=" + this.oblast + "}";
	}
}