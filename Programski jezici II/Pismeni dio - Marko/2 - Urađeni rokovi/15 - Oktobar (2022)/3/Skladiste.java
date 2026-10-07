import java.util.*;

public class Skladiste {
	
	String adresa;
	ArrayList<Raf> rafe = new ArrayList<>();
	String tip;
	
	private static int redniBroj = 1;
	
	public Skladiste() {
		adresa = "adresa" + redniBroj;
		for (int i = 0; i < 5; i++) {
			rafe.add(new Raf());
		}
		tip = "tip" + redniBroj++;
	}
	
	@Override
	public String toString() {
		return "Skladiste{adresa=" + adresa + ", tip=" + tip + ", rafe=" + rafe + "}";
	}
}