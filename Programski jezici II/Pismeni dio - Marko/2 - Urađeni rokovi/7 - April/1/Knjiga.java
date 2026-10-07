public abstract class Knjiga {
	
	public String naslov;
	public String autor;
	public int brojStranica;
	public int godinaIzdavanja;
	
	private static int redniBroj = 1;
	
	public Knjiga() {
		this.naslov = "Naslov" + redniBroj;
		this.autor = "Autor" + redniBroj;
		this.brojStranica = Main.random.nextInt(101) + 100; // 100-200
		this.godinaIzdavanja = Main.random.nextInt(10) + 2015;
		redniBroj++;
	}
}