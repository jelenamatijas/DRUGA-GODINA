import java.util.*;

public class Film {
	
	String naziv;
	ArrayList<Glumac> glumci = new ArrayList<>();
	int godinaObjavljivanja;
	Zanr zanr;
	int vrijemeTrajanja;
	double budzet;
	
	private static int redniBroj = 1;
	
	public Film() {
		naziv = "Naziv" + redniBroj++;
		
		int brojGlumaca = Main.random.nextInt(2) + 1;
		for (int i = 0; i < brojGlumaca; i++) {
			glumci.add(new Glumac());
		}
		
		godinaObjavljivanja = Main.random.nextInt(10) + 2015;
		
		int randomZanr = Main.random.nextInt(4);
		if (randomZanr == 0) {
			zanr = Zanr.AKCIJA;
		} else if (randomZanr == 1) {
			zanr = Zanr.DRAMA;
		} else if (randomZanr == 2) {
			zanr = Zanr.KOMEDIJA;
		} else {
			zanr = Zanr.DOKUMENTARAC;
		}
		
		vrijemeTrajanja = Main.random.nextInt(61) + 60;
		budzet = (double) Main.random.nextInt(1000000); // milion
	}
	
	@Override
	public String toString() {
		return "Film{naziv=" + naziv + ", glumci=" + glumci + ", godinaObjavljivanja=" + godinaObjavljivanja + ", zanr=" + zanr + ", vrijemeTrajanja=" + vrijemeTrajanja + "m, budzet=" + budzet + "}";
	}
}

enum Zanr {
	AKCIJA, DRAMA, KOMEDIJA, DOKUMENTARAC;
}