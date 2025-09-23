import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		HashSet<Pas> grupaPasa1 = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			grupaPasa1.add(new Pas());
		}
		
		HashSet<Pas> grupaPasa2 = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			grupaPasa2.add(new Pas());
		}
		
		// 1.
		System.out.println("1.-------------------");
		grupaPasa2.forEach(pas -> grupaPasa1.add(pas));
		grupaPasa2.clear();
		System.out.println("Grupa 1 nakon spajanja:\n=============================");
		grupaPasa1.forEach(System.out::println);
		System.out.println("=============================");
		System.out.println("Grupa 2 nakon brisanja:\n=============================");
		grupaPasa2.forEach(System.out::println);
		System.out.println("=============================");
		
		// 2.
		System.out.println("\n2.-------------------\nPsi koji su rodjeni 2020 godine:");
		//grupaPasa1.stream().filter(pas -> pas.godinaRodjenja == 2020).collect(Collectors.toList()).forEach(System.out::println);
		
		// Alternativa
		Predicate<Pas> psiRodjeni2020 = pas -> pas.godinaRodjenja == 2020;
		grupaPasa1.stream().filter(psiRodjeni2020).collect(Collectors.toList()).forEach(System.out::println);
		
		Map<Integer, List<Pas>> mapaPasa = grupaPasa1.stream().collect(Collectors.groupingBy(pas -> pas.godinaRodjenja));
		System.out.println("\nPsi grupisani po godinama:");
		mapaPasa.forEach((godina, psi) -> System.out.println("\n" + godina + ": " + psi));
		
		// 3.
		System.out.println("\n3.-------------------\nPsi sortirani po omiljenoj hrani:");
		grupaPasa1.stream().sorted(Comparator.comparing(pas -> pas.omiljenaHrana)).forEach(System.out::println);
		
		// 4.
		System.out.println("\n4.-------------------");
		System.out.println("Suma tezina pasa kojima je omiljena hrana PILETINA i godinja rodjenja PARNA: " + grupaPasa1.stream().filter(pas -> (pas.omiljenaHrana == Pas.OmiljenaHrana.PILETINA && (pas.godinaRodjenja % 2 == 0))).mapToInt(pas -> pas.tezina).sum() + "kg.");
		
		// 5.
		System.out.println("\n5.-------------------");
		System.out.println("Pas sa najmanje godina: " + grupaPasa1.stream().max(Comparator.comparing(pas -> pas.godinaRodjenja)).get());
		System.out.println("Pas sa najvise godina: " + grupaPasa1.stream().min(Comparator.comparing(pas -> pas.godinaRodjenja)).get());
		
		double prosjekGodina = grupaPasa1.stream().mapToInt(pas -> pas.godinaRodjenja).average().getAsDouble();
		System.out.println("Prosjek godina: " + grupaPasa1.stream().mapToInt(pas -> pas.godinaRodjenja).average().getAsDouble());
		Pas pasNajbliziProsjeku = grupaPasa1.stream().min(Comparator.comparingDouble(pas -> Math.abs(pas.godinaRodjenja - prosjekGodina))).get();
		System.out.println("Pas najblizi prosjeku godina: " + pasNajbliziProsjeku);
	}
}