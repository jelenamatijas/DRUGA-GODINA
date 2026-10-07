import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		Firma firma = new Firma();
		
		// a.
		double sum = firma.skladista.stream().flatMap(skladiste -> skladiste.rafe.stream()).flatMap(raf -> raf.artikli.stream()).filter(artikal -> artikal.barkod.equals("barkod1")).mapToDouble(artikal -> artikal.kolicina).sum();
		System.out.println("Suma kolicina svih artikala koji imaju barkod1: " + sum + "\n");
		
		// b.
		
		// c.
		Raf raf = firma.skladista.stream().flatMap(skladiste -> skladiste.rafe.stream()).max(Comparator.comparingInt(r -> r.artikli.size())).get();
		System.out.println("Raf sa najvise artikala: " + raf.id);
		
		firma.skladista.stream().flatMap(skladiste -> skladiste.rafe.stream()).forEach(r -> {
			System.out.println(r.id + ": " + r.artikli.size());
		});
		
		// d.
		firma.skladista.stream()
		.sorted(Comparator.comparingInt(
			skladiste -> skladiste.rafe.stream()
				.flatMap(rafa -> rafa.artikli.values().stream()) // Uzimamo sve kolicine artikala
				.mapToInt(Integer::intValue) // Konvertujemo u int
				.sum()
		).reversed()) // Sortiramo opadajuće
		.forEach(skladiste -> {
			int ukupnaKolicina = skladiste.rafe.stream()
				.flatMap(rafa -> rafa.artikli.values().stream())
				.mapToInt(Integer::intValue)
				.sum();
			
			System.out.println("Skladiste: " + skladiste.adresa + " | Ukupna kolicina artikala: " + ukupnaKolicina);

			skladiste.getRafe().forEach(rafa -> {
				System.out.println("  Rafa: " + rafa.getOznaka());
				rafa.artikli.forEach((artikal, kolicina) -> {
					System.out.println("    Artikal: " + artikal.getNaziv() + " | Barkod: " + artikal.getBarkod() + " | Kolicina: " + kolicina);
				});
			});

			System.out.println(); // Razmak izmedju skladista za bolju citljivost
		});

	}
}