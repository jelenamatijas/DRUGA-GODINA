public class Beletristika extends Knjiga {
	
	public String zanr;
	
	public Beletristika() {
		super();
		this.zanr = "Zanr" + (Main.random.nextInt(5) + 1);
	}
	
	@Override
	public String toString() {
		return "Beletristika{naslov=" + this.naslov + ", autor=" + this.autor + ", brojStranica=" + this.brojStranica + ", godinaIzdavanja=" + this.godinaIzdavanja + ", zanr=" + this.zanr + "}";
	}
}