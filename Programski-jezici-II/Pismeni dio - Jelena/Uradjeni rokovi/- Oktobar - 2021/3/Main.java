import java.util.*;
import java.io.*;
import java.util.stream.*;
import java.util.function.Function;

class Main{
	static Random rand = new Random();
	static public void main(String argsp[]){
		List<Oglas> oglasi = new ArrayList<>();
		for(int i=0;i<12;i++){
			oglasi.add(new Oglas(Oglas.Kategorija.IT));
			oglasi.add(new Oglas(Oglas.Kategorija.MEDICINA));
			oglasi.add(new Oglas(Oglas.Kategorija.EKONOMIJA));
			oglasi.add(new Oglas(Oglas.Kategorija.NOVINARSTVO));
			oglasi.add(new Oglas(Oglas.Kategorija.PRAVO));
		}
		
		oglasi.stream().collect(Collectors.groupingBy(o -> o.datum, Collectors.counting())).forEach((datum, broj) -> System.out.println(datum + " -> " + broj));
		int plata = oglasi.stream().filter(o -> o.kategorija == Oglas.Kategorija.IT).mapToInt((Oglas o) -> o.plata).sum();
		int broj = oglasi.stream().filter(o -> o.kategorija == Oglas.Kategorija.IT).toList().size();
		System.out.println("\nProsjecna plata u IT: " + ((double)plata/broj));
		
		Map.Entry<String, Long> mapa = oglasi.stream().collect(Collectors.groupingBy(o->o.grad, Collectors.counting()))
				.entrySet().stream().max(Map.Entry.comparingByValue()).get();
		System.out.println("Najcesci grad: " + mapa.getKey() + " -> " + mapa.getValue());
		
		System.out.println();
		
		oglasi.stream().collect(Collectors.groupingBy(o -> o.datum.split(":")[1])).forEach((datum, lista) -> {
			System.out.println(datum + " -> ");
			lista.forEach(System.out::println);
		});
		
		System.out.println();
		
		oglasi.stream().sorted(Comparator.comparing(o -> -o.vrijemeTrajanja)).forEach(System.out::println);
		
		System.out.println();
		
		oglasi.stream().collect(Collectors.groupingBy((Oglas o) -> o.kategorija)).forEach((posao, lista) -> {
			System.out.println(posao + ":" + lista.stream().max(Comparator.comparing((Oglas o) -> o.plata)).get());
		});
		
		System.out.println();
		Function<List<Oglas>, Double> prosjek = lista ->  {
				return ((double) lista.stream().mapToInt((Oglas o) -> o.godineIskustva).sum())/60;
			};
		System.out.println("Prosjecne godine iskustva ukupno: " + prosjek.apply(oglasi));
		
		oglasi.stream().collect(Collectors.groupingBy(o -> o.kategorija)).forEach((kat, lista) -> {
			System.out.println(kat + " -> " + prosjek.apply(lista));
		});
	}
}