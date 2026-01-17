import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static Random random = new Random();
	
	public static List<Map.Entry<String, Double>> generickaMetoda(Status status, ArrayList<Podatak>... nizovi) {
		return Arrays.stream(nizovi) // Uzimamo nizove
				.flatMap(niz -> niz.stream()) // Razflatovujemo svaki ArrayList u stream
				.filter(podatak -> podatak.getStatus() == status) // Filtriramo prema statusu
				.collect(Collectors.toMap(
						Podatak::getName, // Kljuc: naziv podatka
						Podatak::getValue, // Vrednost: vrednost podatka
						(a, b) -> a + b)) // Ako postoji duplikat, sabiramo vrednosti
				.entrySet()
				.stream() // Pretvaramo mapu u stream entry-set-a
				.toList(); // Konvertujemo u Listu
	}
	
	public static void main(String[] args) {
		ArrayList<Podatak> niz1 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			niz1.add(new KlasaA());
			niz1.add(new KlasaB());
			niz1.add(new KlasaC());
		}
		
		ArrayList<Podatak> niz2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			niz2.add(new KlasaA());
			niz2.add(new KlasaB());
			niz2.add(new KlasaC());
		}
		
		ArrayList<Podatak> niz3 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			niz3.add(new KlasaA());
			niz3.add(new KlasaB());
			niz3.add(new KlasaC());
		}
		
		List<Map.Entry<String, Double>> lista = generickaMetoda(Status.NEW, niz1, niz2, niz3);
		
		System.out.println("\nSvi NEW:");
		niz1.stream().filter(podatak -> podatak.getStatus() == Status.NEW).forEach(System.out::println);
		niz2.stream().filter(podatak -> podatak.getStatus() == Status.NEW).forEach(System.out::println);
		niz2.stream().filter(podatak -> podatak.getStatus() == Status.NEW).forEach(System.out::println);
		System.out.println("\n Kljuc: Name, Vrijednost: Zbir Value za svako ime:\n");
		
		System.out.println(lista);
	}
}