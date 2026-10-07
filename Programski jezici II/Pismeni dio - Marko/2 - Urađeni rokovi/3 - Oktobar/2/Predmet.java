public class Predmet {

	private String naziv;

	protected String sifra;

	public Predmet() {}

	public Predmet(String naziv, String sifra) {
		this.naziv = naziv;
		this.sifra = sifra;
	}

	public void setnaziv(String naziv) {
		this.naziv = naziv;
	}

	public void setsifra(String sifra) {
		this.sifra = sifra;
	}

	public String getnaziv() {
		return this.naziv;
	}
	public String getsifra() {
		return this.sifra;
	}
	@Override
	public String toString(){
		return "Predmet:" + "naziv=" + this.naziv + ", " + "sifra=" + this.sifra;

	}
	@Override
	public boolean equals(Object object) {
		if (this.getClass() == object.getClass()) {
			Predmet predmet = (Predmet) object;
			return this.naziv.equals(predmet.naziv) && this.sifra.equals(predmet.sifra);
		}
		return false;
	}
}
