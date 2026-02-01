import java.util.*;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		HashSet<Pas> grupaPasa1 = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			grupaPasa1.add(new Pas());
		}
		
		Pas.redniBroj = 1;
		
		HashSet<Pas> grupaPasa2 = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			grupaPasa2.add(new Pas());
		}
		
		// 1.
		System.out.println("\nBroj pasa u grupi1 PRIJE spajanja: " + grupaPasa1.size());
		grupaPasa2.stream().forEach(p -> grupaPasa1.add(p));
		grupaPasa2.clear();
		System.out.println("\nBroj pasa u grupi1 NAKON spajanja: " + grupaPasa1.size());
		grupaPasa1.forEach(System.out::println);
		
		// 2.
		Predicate<Pas> filtrirajPoGodiniRodjenja = pas -> pas.godinaRodjenja == 2020;
        List<Pas> filtriraniPasi = grupaPasa1.stream().filter(filtrirajPoGodiniRodjenja).collect(Collectors.toList());
		System.out.println("\nFiltrirani psi rodjeni 2020. godine:");
        filtriraniPasi.forEach(System.out::println);
		
		Map<Integer, List<Pas>> grupisaniPasi = grupaPasa1.stream().collect(Collectors.groupingBy(pas -> pas.godinaRodjenja));
		System.out.println("\nGrupa pasa po godini rodjenja:");
        grupisaniPasi.forEach((godina, psi) -> {
            System.out.println("Godina rodjenja: " + godina);
            psi.forEach(System.out::println);
        });
		
		// 3.
		List<Pas> sortiraniPsi = grupaPasa1.stream().sorted(Comparator.comparing(pas -> pas.omiljenaHrana)).collect(Collectors.toList());
		System.out.println("\nPsi sortirani po omiljenoj hrani:");
        sortiraniPsi.forEach(System.out::println);
		
		// 4.
		Function<Pas, Integer> tezinaPasa = pas -> pas.tezina;
		int sumaTezina = grupaPasa1.stream().filter(pas -> pas.omiljenaHrana == Pas.OmiljenaHrana.PILETINA && pas.godinaRodjenja % 2 == 0).map(tezinaPasa).mapToInt(Integer::intValue).sum();
		
		System.out.println("\nSuma tezina pasa sa omiljenom hranom PILETINA i parnom godinom rodjenja: " + sumaTezina);
		
		// 5.
        Pas najviseGodinaPas = grupaPasa1.stream().min(Comparator.comparingInt(pas -> pas.godinaRodjenja)).orElse(null);
		Pas najmanjeGodinaPas = grupaPasa1.stream().max(Comparator.comparingInt(pas -> pas.godinaRodjenja)).orElse(null);
		
		double prosjecnaGodinaPsa = grupaPasa1.stream().collect(Collectors.averagingInt(pas -> pas.godinaRodjenja));
		
		Pas najbliziProsjekuPas = grupaPasa1.stream().min(Comparator.comparingDouble(pas -> Math.abs(pas.godinaRodjenja - prosjecnaGodinaPsa))).orElse(null);
		
		System.out.println("\nPas sa najmanje godina: " + najmanjeGodinaPas);
        System.out.println("Pas sa najvise godina: " + najviseGodinaPas);
        System.out.printf("Prosecna godina rodjenja: %.2f\n", prosjecnaGodinaPsa);
        System.out.println("Pas najblizi prosecnoj godini: " + najbliziProsjekuPas);
	}
}