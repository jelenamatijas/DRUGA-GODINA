public class FirmaKorisnik extends Korisnik {
	
	public String podaciFirme;
	
	private static int redniBroj = 1;
	
	public FirmaKorisnik() {
		super();
		this.podaciFirme = "PodaciFirme" + redniBroj;
		redniBroj++;
	}
	
	@Override
	public String toString() {
		return "FirmaKorisnik{ime=" + this.ime + ", prezime=" + this.prezime + ", godinaRodjenja=" + this.godinaRodjenja + ", podaciFirme= " + this.podaciFirme + "}";
	}
}