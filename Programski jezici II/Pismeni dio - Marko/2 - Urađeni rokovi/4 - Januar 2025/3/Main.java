import java.util.function.Predicate;

public class Main {
	
	public static void main(String[] args) {
		Storage<Proizvod> skladisteProizvoda = new Storage<>();
		for (int i = 0; i < 3; i++) {
			skladisteProizvoda.addItem(new Proizvod((double) (i + 30)));
		}
		
		Storage<Vozilo> skladisteVozila = new Storage<>();
		skladisteVozila.addItem(new Vozilo("BMW"));
		skladisteVozila.addItem(new Vozilo("Mercedes"));
		skladisteVozila.addItem(new Vozilo("Peugeot"));;
		
		System.out.println("\n==========ISPIS SVIH PROIZVODA==========");
		skladisteProizvoda.printAll();
		
		System.out.println("\n==========ISPIS SVIH VOZILA==========");
		skladisteVozila.printAll();
		
		Predicate<Proizvod> kolicinaVecaOd30 = p -> p.kolicina > 30.0;
		Predicate<Vozilo> voziloBMW = v -> v.proizvodjac.contentEquals("BMW");
		
		Proizvod proizvod = skladisteProizvoda.findFirstMatch(kolicinaVecaOd30);
		Vozilo vozilo = skladisteVozila.findFirstMatch(voziloBMW);
		
		System.out.println("\n==========ISPIS PROIZVODA SA KOLICINOM > 30==========");
		System.out.println(proizvod);
		
		System.out.println("\n==========ISPIS VOZILA BMW==========");
		System.out.println(vozilo);
		
	}
}