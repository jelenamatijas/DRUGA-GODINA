public class Putnik {
	
	public String ime;
	public String prezime;
	
	private static int redniBroj = 1;
	
	public Putnik() {
		this.ime = "Ime" + redniBroj;
		this.prezime = "Prezime" + redniBroj;
		redniBroj++;
	}
	
	@Override
	public String toString() {
		return this.ime + " " + this.prezime;
	}
}