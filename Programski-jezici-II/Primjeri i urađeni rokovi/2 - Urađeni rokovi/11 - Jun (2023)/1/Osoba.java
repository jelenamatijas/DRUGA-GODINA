public abstract class Osoba {
	
	String ime;
	String prezime;
	int novac;
	
	public static int redniBroj = 1;
	
	public Osoba() {
		ime = "Ime" + redniBroj;
		prezime = "Prezime" + redniBroj;
		novac = Main.random.nextInt(521) + 80;
	}
}