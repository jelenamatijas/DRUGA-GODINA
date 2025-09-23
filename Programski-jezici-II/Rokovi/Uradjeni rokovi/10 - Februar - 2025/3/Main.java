import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
class Main{
	static Random rand = new Random();
	public static void main(String[] args){
		HashSet<Knjiga> grupa = new HashSet<>();
		HashSet<Knjiga> grupa1 = new HashSet<>();
		
		for(int i=0; i<10; i++){
			grupa.add(new Knjiga());
			grupa1.add(new Knjiga());
		}
		 
		System.out.println("======================= 1 =======================");
		grupa1.stream().forEach(k -> grupa.add(k));
		grupa1.clear();
		grupa.forEach(System.out::println);
		
		System.out.println("======================= 2 =======================");
		Predicate<Knjiga> veceOd2010 = knjiga -> knjiga.godina > 2010;
		
		Map<Knjiga.Zanr, List<Knjiga>> poZanru = grupa.stream().filter(k -> veceOd2010.test(k)).collect(Collectors.groupingBy(k -> k.zanr));
		poZanru.forEach((zanr, lista) -> {
			System.out.println(zanr);
			lista.forEach(System.out::println);
		});
		
		System.out.println("======================= 3 =======================");
		grupa.stream().sorted(Comparator.comparing(k -> -k.brojStranica)).forEach(System.out::println);
		
		System.out.println("======================= 4 =======================");
		Function<Knjiga, Integer> brojStranica = k -> {
			if(k.godina %2 == 1){
				return k.brojStranica;
			}
			return 0;
		};
		int ukupno = grupa.stream().mapToInt(k -> brojStranica.apply(k)).sum();
		System.out.println("Ukupno stranica: " + ukupno);
		
		System.out.println("======================= 5 =======================");
		Knjiga min = grupa.stream().min(Comparator.comparingInt(k -> k.brojStranica)).get();
		Knjiga max = grupa.stream().max(Comparator.comparingInt(k -> k.brojStranica)).get();
		
		double prosjek = grupa.stream().mapToInt(k -> k.brojStranica).average().getAsDouble();
		Knjiga najPribliznija = grupa.stream().min(Comparator.comparingDouble(k -> Math.abs((double)k.brojStranica - prosjek))).get();
		System.out.println("MIN: " + min + "\nMAX: " + max + "\nNAJPRIBLIZNIJA: " + najPribliznija);
	}
}