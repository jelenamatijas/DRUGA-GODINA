import java.util.*;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.time.Year;

class Main{
	public static void main(String []args){
		//1.
		HashSet<Pas> grupa1 = new HashSet<>();
		HashSet<Pas> grupa2 = new HashSet<>();
		
		for(int i=0; i<10; i++){
			grupa1.add(new Pas());
			grupa2.add(new Pas());
		}
		System.out.println("PRIJE BRISANJA:");
		System.out.println("Grupa 1:\n");
		grupa1.forEach(System.out::println);
		System.out.println("Grupa 2:\n");
		grupa2.forEach(System.out::println);
		
		grupa2.stream().forEach( p -> {
			grupa1.add(p);
		});
		
		grupa2.clear();
		
		System.out.println("POSLIJE BRISANJA:");
		System.out.println("Grupa 1:\n");
		grupa1.forEach(System.out::println);
		System.out.println("Grupa 2:\n");
		grupa2.forEach(System.out::println);
		
		System.out.println("=======================================================================================");
		
		//2
		
		Predicate<Pas> filtrirajPoGodinama = p -> p.godinaRodjenja== 2018;
		List<Pas> filtritaniPoGodini = grupa1.stream().filter(filtrirajPoGodinama).collect(Collectors.toList());
		System.out.println("Nakon filtriranja po godinama (godina rodjenja == 2018):\n");
		filtritaniPoGodini.forEach(System.out::println);
		
		System.out.println();
		
		Map<Integer, List<Pas>> filtriraniPoSvimGodinama = grupa1.stream().collect(Collectors.groupingBy(p -> p.godinaRodjenja));
		filtriraniPoSvimGodinama.forEach((godina, psi) -> {
			System.out.println("GODINE: " + godina);
			psi.forEach(System.out::println);
		});
		
		System.out.println("=======================================================================================");
		
		//3
		
		Map<Pas.OmiljenaHrana, List<Pas>> filtriraniPoHrani = grupa1.stream().collect(Collectors.groupingBy(Pas::getOmiljenaHrana));
		filtriraniPoHrani.forEach((hrana, psi) -> {
			System.out.println("OMILJENA HRANA: " + hrana);
			psi.forEach(System.out::println);
		});
		
		System.out.println("=======================================================================================");
		
		//4
		Function<Pas, Double> tezinaPsa = p -> p.tezina;
		double tezina = grupa1.stream().filter(p -> p.godinaRodjenja % 2 == 0 && p.omiljenaHrana == Pas.OmiljenaHrana.PILETINA).map(tezinaPsa).mapToDouble(Double::doubleValue).sum();
		System.out.println("Tezina = " + tezina);
		
		System.out.println("=======================================================================================");
		
		//5
		Pas najmanjeGodina = grupa1.stream().min(Comparator.comparingInt(p -> p.godinaRodjenja)).get();
		System.out.println("Najmladji pas: " + najmanjeGodina);
		Pas najstariji = grupa1.stream().max(Comparator.comparingInt(p -> p.godinaRodjenja)).get();
		System.out.println("Najstariji pas: " + najstariji);
		
		int year = Year.now().getValue();
		
		double srednjaVrijednost = grupa1.stream().mapToInt(p -> (year - p.godinaRodjenja)).average().getAsDouble();
		System.out.println("Prosjecna vrijednost godina: " + srednjaVrijednost);
		Pas najpriblizniji = grupa1.stream().min(Comparator.comparingDouble(p -> Math.abs((year - p.godinaRodjenja)-srednjaVrijednost))).get();
		System.out.println("Najstariji pas: " + najpriblizniji);
	}
}
