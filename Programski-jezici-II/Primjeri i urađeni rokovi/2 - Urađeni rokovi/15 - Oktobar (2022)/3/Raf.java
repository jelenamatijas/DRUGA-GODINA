import java.util.*;

public class Raf {

	int id;
	ArrayList<Artikal> artikli = new ArrayList<>();
	
	private static int redniBroj = 1;
	
	public Raf() {
		id = redniBroj++;
		int randomBrojArtikala = Main.random.nextInt(6) + 5;
		for (int i = 0; i < randomBrojArtikala; i++) {
			artikli.add(new Artikal());
		}
		Artikal.redniBroj = 0;
	}
	
	@Override
	public String toString() {
		return "Raf{id=" + id + ", artikli=" + artikli + "}";
	}
}