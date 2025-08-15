package javatechnoshop.proizvodi;

/**
 *
 * @author Igor
 */
public class Racunar extends Proizvod {
	private String konfiguracija;

	public Racunar() {
	}

	public Racunar(String konfiguracija, String sifra, double cijena, String naziv, Proizvodjac proizvodjac) {
		super(sifra, cijena, naziv, proizvodjac);
		this.konfiguracija = konfiguracija;
	}

	public String getKonfiguracija() {
		return konfiguracija;
	}

	public void setKonfiguracija(String konfiguracija) {
		this.konfiguracija = konfiguracija;
	}

	@Override
	public String toString() {
		return "Racunar: " + "konfiguracija=" + konfiguracija + ' ' + super.toString();
	}

}
