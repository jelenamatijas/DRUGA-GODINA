import java.util.Objects;

public class Film {
	
	public String naziv;
	public String reziser;
	public int godinaIzdavanja;
	public Zanr zanr;
	
	public Film() {
		this.naziv = "Naziv" + (Main.random.nextInt(10) + 1);
		this.reziser = "Reziser" + (Main.random.nextInt(10) + 1);
		this.godinaIzdavanja = Main.random.nextInt(20) + 2000;
		int zanr = Main.random.nextInt(4);
		if (zanr == 0) {
			this.zanr = Zanr.AKCIJA;
		}
		else if (zanr == 1) {
			this.zanr = Zanr.DRAMA;
		}
		else if (zanr == 2) {
			this.zanr = Zanr.KOMEDIJA;
		}
		else if (zanr == 3) {
			this.zanr = Zanr.DOKUMENTARAC;
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || this.getClass() != object.getClass())
			return false;
		Film film = (Film) object;
		return this.naziv.equals(film.naziv) && this.godinaIzdavanja == film.godinaIzdavanja;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.naziv, this.godinaIzdavanja);
	}
	
	@Override
	public String toString() {
		return "Film{naziv=" + this.naziv + ", reziser=" + this.reziser + ", godinaIzdavanja=" + this.godinaIzdavanja + ", zanr=" + this.zanr + "}";
	}
	
	public enum Zanr {
		AKCIJA, DRAMA, KOMEDIJA, DOKUMENTARAC;
	}
}