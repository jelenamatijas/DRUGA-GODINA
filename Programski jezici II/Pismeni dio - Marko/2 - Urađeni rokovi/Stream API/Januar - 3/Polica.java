import java.util.*;

public class Polica {
	
	Integer id;
	ArrayList<Knjiga> knjige = new ArrayList<>();
	
	private static int redniBroj = 1;
	
	public Polica() {
		id = redniBroj++;
		int brojKnjiga = (Main.random.nextInt(3) + 1 ) * 5;
		for (int i = 0; i < brojKnjiga; i++) {
			knjige.add(new Knjiga());
		}
	}
	
	@Override
	public String toString() {
		return "Polica{id=" + id + ", knjige=" + knjige + "}\n";
	}
}