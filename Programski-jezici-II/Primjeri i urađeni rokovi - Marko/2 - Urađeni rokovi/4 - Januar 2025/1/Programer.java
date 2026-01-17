import java.util.ArrayList;

public class Programer extends Osoba implements StraniJezikInterface, DobraOrganizacijaInterface {
	
	public ArrayList<String> programskiJezici = new ArrayList<>();
	
	private static int redniBroj = 1;
	
	public Programer(int red) {
		super(red);
		programskiJezici.add("Jezik" + redniBroj++);
		programskiJezici.add("Jezik" + redniBroj++);
		programskiJezici.add("Jezik" + redniBroj++);
	}
	
	@Override
	public String toString() {
		return "Programer{ime=" + this.ime + ", prezime=" + this.prezime + ", godineStarosti=" + this.godineStarosti + ", oblastInteresovanja= " + this.oblastInteresovanja + ", biografija=" + this.biografija + ", JEZICI=" + this.programskiJezici + "}";
	}
}