import java.util.Objects;

public class Pas {
	
	public String ime;
	public int tezina;
	public int godinaRodjenja;
	public OmiljenaHrana omiljenaHrana;
	
	public static int redniBroj = 1;
	
	public Pas() {
		this.ime = "Ime" + redniBroj++;
		this.tezina = Main.random.nextInt(21) + 10; // 10-30 kg
		this.godinaRodjenja = Main.random.nextInt(10) + 2015;
		
		int randomOmiljenaHrana = Main.random.nextInt(3);
		if (randomOmiljenaHrana == 0) {
			this.omiljenaHrana = OmiljenaHrana.MESO;
		} else if (randomOmiljenaHrana == 1) {
			this.omiljenaHrana = OmiljenaHrana.PILETINA;
		} else if (randomOmiljenaHrana == 2) {
			this.omiljenaHrana = OmiljenaHrana.RIBA;
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object == null || this.getClass() != object.getClass()) {
			return false;
		} else {
			Pas pas = (Pas) object;
			return this.ime.equals(pas.ime) && this.godinaRodjenja == pas.godinaRodjenja;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.ime, this.godinaRodjenja);
	}
	
	@Override
	public String toString() {
		return "Pas{ime=" + this.ime + ", tezina=" + this.tezina + ", godinaRodjenja=" + this.godinaRodjenja + ", omiljenaHrana=" + this.omiljenaHrana + "}";
	}
	
	public enum OmiljenaHrana {
		MESO, PILETINA, RIBA;
	}
}