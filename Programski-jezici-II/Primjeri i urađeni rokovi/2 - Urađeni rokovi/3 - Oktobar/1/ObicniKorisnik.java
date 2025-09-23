public class ObicniKorisnik extends Korisnik {
	
	public double novac;
	public boolean kupon;
	
	public ObicniKorisnik() {
		super();
		this.novac = (double)(Main.random.nextInt(11) + 2);
		this.kupon = Main.random.nextInt(100) < 30;
	}
	
	@Override
	public String toString() {
		return "ObicniKorisnik{ime=" + this.ime + ", prezime=" + this.prezime + ", godinaRodjenja=" + this.godinaRodjenja + ", novac= " + this.novac + ", kupon=" + this.kupon + "}";
	}
}