public class Istoricar extends Osoba implements MaternjiJezikInterface, VjestoPisanjeInterface, DobraOrganizacijaInterface {
	
	public String period;
	
	private static int redniBroj = 1;
	
	public Istoricar(int red) {
		super(red);
		this.period = "Period" + redniBroj++;
	}
	
	@Override
	public String toString() {
		return "Istoricar{ime=" + this.ime + ", prezime=" + this.prezime + ", godineStarosti=" + this.godineStarosti + ", oblastInteresovanja= " + this.oblastInteresovanja + ", biografija=" + this.biografija + ", PERIOD=" + this.period + "}";
	}
}