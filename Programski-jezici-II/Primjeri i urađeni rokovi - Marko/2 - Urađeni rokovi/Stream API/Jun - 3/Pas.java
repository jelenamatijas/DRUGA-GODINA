import java.util.Objects;

public class Pas {
	
	int godinaRodjenja;
	String ime;
	int tezina;
	OmiljenaHrana omiljenaHrana;
	
	private static int redniBroj = 1;
	
	public Pas() {
		this.godinaRodjenja = Main.random.nextInt(5) + 2020;
		this.ime = "Ime" + redniBroj++;
		this.tezina = Main.random.nextInt(20) + 1;
		
		int randomOmiljenaHrana = Main.random.nextInt(3);
		if (randomOmiljenaHrana == 0) {
			this.omiljenaHrana = OmiljenaHrana.MESO;
		} else if (randomOmiljenaHrana == 1) {
			this.omiljenaHrana = OmiljenaHrana.PILETINA;
		} else {
			this.omiljenaHrana = OmiljenaHrana.RIBA;
		}
		
		// Samo 5 razlicitih imena
		if (redniBroj > 5) {
			redniBroj = 1;
		}
	}
	
	@Override
	public String toString() {
		return "Pas{godinaRodjenja=" + godinaRodjenja + ", ime=" + ime + ", tezina=" + tezina + "kg, omiljenaHrana=" + omiljenaHrana + "}";
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object == null || this.getClass() != object.getClass()) {
			return false;
		} else {
			Pas pas = (Pas) object;
			return this.godinaRodjenja == pas.godinaRodjenja && this.ime.equals(pas.ime);
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.godinaRodjenja, this.ime);
	}
	
	public enum OmiljenaHrana {
		MESO, PILETINA, RIBA;
	}
}