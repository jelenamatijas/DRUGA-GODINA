import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		LinkedList<Automobil> automobili1 = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			automobili1.add(new Automobil());
		}
		
		LinkedList<Automobil> automobili2 = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			automobili2.add(new Automobil());
		}
		
		LinkedList<Automobil> automobili3 = new LinkedList<>();
		automobili3.addAll(automobili1);
		automobili3.addAll(automobili2);
		
		// Ispis svih automobila - zbog provjere rezultata
		System.out.println("====================================");
		automobili3.forEach(System.out::println);
		System.out.println("====================================");
		
		// a.
		System.out.println("\na. ----------------------------------------");
		automobili3.stream().filter(automobil -> (automobil.tip == Automobil.Tip.SUV && automobil.snagaMotora > 150)).collect(Collectors.toList()).forEach(automobil -> System.out.println("\n" + automobil));
		
		// alternativa
		//Stream.concat(automobili1.stream(), automobili2.stream()).filter(automobil -> (automobil.tip == Automobil.Tip.SUV && automobil.snagaMotora > 150)).collect(Collectors.toList()).forEach(automobil -> System.out.println("\n" + automobil));
		
		// b.
		System.out.println("\nb. ----------------------------------------");
		System.out.println("\nSortirana 1. grupa automobila:");
		automobili1.stream().sorted(Comparator.comparingInt(automobil -> -automobil.snagaMotora)).forEach(automobil -> System.out.println(automobil));
		
		// alternativa
		List<Automobil> sortiraniAutomobili1 = automobili1.stream().sorted(Comparator.comparingInt(automobil -> automobil.snagaMotora)).collect(Collectors.toList());
		
		Collections.reverse(sortiraniAutomobili1);
		
		//sortiraniAutomobili1.forEach(a -> System.out.println("\n" + a));
		
		System.out.println("\nSortirana 2. grupa automobila:");
		automobili2.stream().sorted(Comparator.comparingInt(automobil -> -automobil.snagaMotora)).forEach(automobil -> System.out.println(automobil));
		
		// c.
		System.out.println("\nc. ----------------------------------------");
		int suma = automobili3.stream().filter(a -> Automobil.Tip.HATCHBACK == a.tip && a.brojVrata > 2).mapToInt(a -> a.brojVrata).sum();
		System.out.println("Broj vrata: " + suma);
		
		// alternativa - koristenjem Function posebno
		Function<List<Automobil>, Integer> sumirajBrojVrataVecihOd2iHATCHBACK = lista -> lista.stream().filter(a -> a.tip == Automobil.Tip.HATCHBACK && a.brojVrata > 2).mapToInt(a -> a.brojVrata).sum();
		
		int brojVrata = sumirajBrojVrataVecihOd2iHATCHBACK.apply(automobili3);
		System.out.println("Broj vrata (alternativa): " + brojVrata);
		
		// d.
		System.out.println("\nd. ----------------------------------------");
		OptionalDouble prosjecnaSnagaMotora = automobili3.stream().mapToInt(a -> a.snagaMotora).average();
		System.out.println("Prosjecna snaga motora: " + prosjecnaSnagaMotora.getAsDouble());
		
		Optional<Automobil> auto = automobili3.stream().min(Comparator.comparingDouble(a -> Math.abs((double) a.snagaMotora - prosjecnaSnagaMotora.getAsDouble())));
		
		System.out.println("Auto sa snagom motora najblizom prosjecnoj: " + auto.get());
	}
}