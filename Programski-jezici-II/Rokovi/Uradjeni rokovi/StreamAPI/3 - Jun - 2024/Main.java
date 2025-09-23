import java.util.*;
import java.util.stream.*;
import java.util.function.Predicate;
import java.util.function.Function;

class Main{
	public static Random rand = new Random();
	
	public static void main(String []args){
		HashSet<Pas> grupa1 = new HashSet<>();
		HashSet<Pas> grupa2 = new HashSet<>();
		for(int i=0; i<10;i++){
			grupa1.add(new Pas());
			grupa2.add(new Pas());
		}
		
		System.out.println("\n============== Grupa 1 ==============");
		grupa1.forEach(System.out::println);
		System.out.println("\n============== Grupa 2 ==============");
		grupa2.forEach(System.out::println);
		
		System.out.println("\n================= 1 =================");
		grupa2.stream().forEach(p -> grupa1.add(p));
		grupa2.clear();
		grupa1.forEach(System.out::println);
		
		System.out.println("\n================= 2 =================");
		Predicate<Pas> filtriraj = p -> p.omiljenaHrana == Pas.OmiljenaHrana.RIBA;
		List<Pas> filtriraniPsi = grupa1.stream().filter(p -> filtriraj.test(p)).toList();
		System.out.println("Filtrirani psi na osnovu omiljene hrane: ");
		filtriraniPsi.forEach(System.out::println);
		Map<Integer, List<Pas>> grupisaniPoGodini = filtriraniPsi.stream().collect(Collectors.groupingBy(p -> p.godina));
		System.out.println("Filtrirani psi po grupama: ");
		grupisaniPoGodini.forEach((godina, lista) -> {
			System.out.println("Godina: " + godina);
			lista.forEach(System.out::println);
		});
		
		System.out.println("\n================= 3 =================");
		grupa1.stream().collect(Collectors.groupingBy(p -> p.omiljenaHrana)).forEach((omiljenaHrana, lista) -> {
			System.out.println("Omiljena hrana: " + omiljenaHrana);
			lista.forEach(System.out::println);
		});
		
		System.out.println("\n================= 4 =================");
		Function<HashSet<Pas>,Double>  odrediTezinu = lista -> lista.stream().filter(p -> p.omiljenaHrana==Pas.OmiljenaHrana.PILETINA && p.godina%2==0).mapToDouble(p -> p.tezina).sum();
		System.out.println("Suma tezina: " + odrediTezinu.apply(grupa1));
		
		System.out.println("\n================= 5 =================");
		Pas najmladji = grupa1.stream().min(Comparator.comparingInt(p -> p.godina)).get();
		Pas najstariji = grupa1.stream().max(Comparator.comparingInt(p -> p.godina)).get();
		System.out.println("Najmljadji: " + najmladji + "\nNajstariji: " + najstariji);
		double prosjekGodina = grupa1.stream().mapToInt(p -> 2025 - p.godina).average().getAsDouble();
		Pas najpriblizniji  = grupa1.stream().min(Comparator.comparing(p -> Math.abs((2025 - p.godina) - prosjekGodina))).get();
		System.out.println("Prosjek godina: " + prosjekGodina + "\nNajpriblizniji: " + najpriblizniji);
	}
}