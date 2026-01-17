import java.util.*;

public class Biblioteka {
	
	ArrayList<Sekcija> sekcije = new ArrayList<>();
	
	public Biblioteka() {
		for (int i = 0; i < 3; i++) {
			sekcije.add(new Sekcija());
		}
	}
	
	@Override
	public String toString() {
		return "Biblioteka{sekcije=" + sekcije + "}";
	}
}