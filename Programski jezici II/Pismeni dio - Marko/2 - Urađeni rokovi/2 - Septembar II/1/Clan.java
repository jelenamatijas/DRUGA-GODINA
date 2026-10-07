public class Clan extends Thread {
	
	String ime;
	String prezime;
	int godineRada;
	
	private static int redniBroj = 1;
	
	public Clan() {
		ime = "ime" + redniBroj;
		prezime = "prezime" + redniBroj++;
		godineRada = Main.random.nextInt(5) + 1;
	}
}