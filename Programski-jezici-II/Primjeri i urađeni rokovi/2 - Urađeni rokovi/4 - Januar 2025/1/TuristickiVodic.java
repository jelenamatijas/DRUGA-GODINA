import java.util.ArrayList;

public class TuristickiVodic extends Osoba implements StraniJezikInterface {
	
	public ArrayList<String> destinacije = new ArrayList<>();
	
	private static int redniBroj = 1;
	
	public TuristickiVodic(int red) {
		super(red);
		destinacije.add("Destinacija" + redniBroj++);
		destinacije.add("Destinacija" + redniBroj++);
		destinacije.add("Destinacija" + redniBroj++);
	}
	
	@Override
	public String toString() {
		return "TuristickiVodic{ime=" + this.ime + ", prezime=" + this.prezime + ", godineStarosti=" + this.godineStarosti + ", oblastInteresovanja= " + this.oblastInteresovanja + ", biografija=" + this.biografija + ", DESTINACIJE=" + this.destinacije + "}";
	}	
}