import java.util.*;

public class Sekcija {
	
	Integer id;
	ArrayList<Polica> police = new ArrayList<>();
	
	private static int redniBroj = 1;
	
	public Sekcija() {
		id = redniBroj++;
		for (int i = 0; i < 2; i++) {
			police.add(new Polica());
		}
	}
	
	@Override
	public String toString() {
		return "Sekcija{id=" + id + ", police=" + police + "}\n";
	}
}