public class Novinar extends Osoba implements MaternjiJezikInterface, VjestoPisanjeInterface {
	
	public String oblast;
	
	private static int redniBroj = 1;
	
	public Novinar(int red) {
		super(red);
		this.oblast = "Oblast" + redniBroj++;
	}
	
	@Override
	public String toString() {
		return "Novinar{ime=" + this.ime + ", prezime=" + this.prezime + ", godineStarosti=" + this.godineStarosti + ", oblastInteresovanja= " + this.oblastInteresovanja + ", biografija=" + this.biografija + ", OBLAST=" + this.oblast + "}";
	}
}