import java.util.Objects;

public class Autor {
	
	String ime;
	String prezime;
	
	public Autor() {
		ime = "Ime" + (Main.random.nextInt(3) + 1);
		prezime = "Prezime" + (Main.random.nextInt(3) + 1);
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object == null || object.getClass() != this.getClass()) {
			return false;
		} else {
			Autor autor = (Autor) object;
			return ime.equals(autor.ime) && prezime.equals(autor.prezime);
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ime, prezime);
	}
	
	@Override
	public String toString() {
		return "Autor{ime=" + ime + ", prezime=" + prezime + "}";
	}
}