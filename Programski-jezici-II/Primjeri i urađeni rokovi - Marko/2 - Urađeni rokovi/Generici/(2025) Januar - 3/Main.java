import java.util.*;
import java.util.function.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		Storage<Proizvod> proizvodi = new Storage();
		for (int i = 0; i < 3; i++) {
			proizvodi.addItem(new Proizvod());
		}
		
		Storage<Vozilo> vozila = new Storage();
		vozila.addItem(new Vozilo("Mercedes"));
		vozila.addItem(new Vozilo("BMW"));
		vozila.addItem(new Vozilo("Audi"));
		
		proizvodi.printAll();
		System.out.println();
		vozila.printAll();
		
		Predicate<Proizvod> kolicinaVecaOd30 = proizvod -> proizvod.kolicina > 30;
		
		Predicate<Vozilo> voziloBMW = Vozilo -> Vozilo.proizvodjac.equals("BMW");
		
		System.out.println("\n" + proizvodi.findFirstMatch(kolicinaVecaOd30));
		System.out.println("\n" + vozila.findFirstMatch(voziloBMW));
	}
}