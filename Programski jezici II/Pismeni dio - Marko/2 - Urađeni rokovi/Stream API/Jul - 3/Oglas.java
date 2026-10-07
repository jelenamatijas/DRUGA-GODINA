public class Oglas {
	
	String naziv;
	String opisPosla;
	String datumObjavljivanja;
	int vrijemeTrajanja;
	int plata;
	int godineIskustva;
	boolean remote;
	String grad;
	Kategorija kategorija;
	
	private static int redniBroj = 1;
	
	public Oglas(Kategorija kategorija) {
		naziv = "Naziv" + redniBroj;
		opisPosla = "Opis" + redniBroj;
		datumObjavljivanja = (Main.random.nextInt(31) + 1) + ".01." + (Main.random.nextInt(2) == 0 ? "2024." : "2025.");
		vrijemeTrajanja = Main.random.nextInt(7) + 1; // 1-7 dana
		plata = Main.random.nextInt(6) * 100 + 1000; // 1000-1500 KM
		godineIskustva = Main.random.nextInt(5); // 0-4 godine
		remote = Main.random.nextInt(2) == 0 ? true : false;
		grad = "Grad" + redniBroj;
		this.kategorija = kategorija;
		
		if (redniBroj > 5) {
			redniBroj = 1;
		}
		redniBroj++;
	}
	
	@Override
	public String toString() {
		return "Oglas{naziv=" + naziv + ", opis=" + opisPosla + ", datum=" + datumObjavljivanja + ", trajanja=" + vrijemeTrajanja + ", plata=" + plata + ", iskustvo=" + godineIskustva + ", nacinRada=" + (remote ? "remote" : "sjediste") + ", grad=" + grad + ", kategorija=" + kategorija + "}";
	}
	
	public enum Kategorija {
		IT, EKONOMIJA, MEDICINA, NOVINARSTVO, PRAVO;
	}
}