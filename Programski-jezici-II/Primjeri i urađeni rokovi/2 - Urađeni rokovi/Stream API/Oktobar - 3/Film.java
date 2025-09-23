import java.util.*;

public class Film {
	
	String naziv;
	String reziser;
	int godinaIzdavanja;
	Zanr zanr;
	
	//private static int redniBroj = 1;
	
	public Film() {
		naziv = "Naziv" + Main.random.nextInt(5);
		reziser = "Reziser" + Main.random.nextInt(10);
		godinaIzdavanja = Main.random.nextInt(5) + 2015;
		
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
	}
	
	@Override
	public String toString() {
		return "Film{naziv=" + naziv + ", reziser=" + reziser + ", godinaIzdavanja=" + godinaIzdavanja + ", zanr=" + zanr + "}";
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object == null || this.getClass() != object.getClass()) {
			return false;
		} else {
			Film film = (Film) object;
			return this.naziv.equals(film.naziv) && this.godinaIzdavanja == film.godinaIzdavanja;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.naziv, this.godinaIzdavanja);
	}
	
	public enum Zanr {
		AKCIJA, DRAMA, KOMEDIJA, DOKUMENTARAC;
	}
}