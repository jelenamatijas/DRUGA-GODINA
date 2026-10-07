public class Glumac {
	
	String ime;
	String prezime;
	
	private static int redniBroj = 1;
	
	public Glumac() {
		ime = "Ime" + redniBroj;
		prezime = "Prezime" + redniBroj++;
		if (redniBroj == 5) {
			redniBroj = 1;
		}
	}
	
	@Override
	public String toString() {
		return "Glumac{ime=" + ime + ", prezime" + prezime + "}";
	}
	
	@Override
	public boolean equals(Object object) {
		if (object.getClass() == this.getClass()) {
			Glumac glumac = (Glumac) object;
			return glumac.ime.equals(this.ime) && glumac.prezime.equals(this.prezime);
		}
		return false;
	}
}