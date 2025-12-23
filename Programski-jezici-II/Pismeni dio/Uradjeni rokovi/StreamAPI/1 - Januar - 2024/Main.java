import java.util.*;
import java.util.stream.*;

class Main{
	static Random rand = new Random();
	
	public static void main(String []args){
		Biblioteka b = new Biblioteka();
		System.out.println(b);
		
		System.out.println("\n============================ 1 ============================");
		b.sekcije.stream().forEach(sekcija -> {
			int brojKnjigaUSekciji = sekcija.police.stream().mapToInt(polica -> polica.knjige.size()).sum();
			System.out.println("Sekcija -> " + sekcija.id + " -> " + brojKnjigaUSekciji);
		});
		
		int ukupanBrojKnjiga = b.sekcije.stream().flatMap(sekcija -> sekcija.police.stream()).mapToInt(polica -> polica.knjige.size()).sum();
		System.out.println("Ukupno knjiga u biblioteci: " + ukupanBrojKnjiga);
		
		System.out.println("\n============================ 2 ============================");
		b.sekcije.stream().flatMap(sekcija -> sekcija.police.stream()).flatMap(polica -> polica.knjige.stream()).map(knjiga -> knjiga.autor).collect(Collectors.toSet()).forEach(System.out::println);
		
		System.out.println("\n============================ 3 ============================");
		b.sekcije.stream().map(sekcija -> sekcija.police.stream().max(Comparator.comparingInt(polica -> polica.knjige.size())).map(najvecaPolica -> Map.entry(sekcija, najvecaPolica))).map(Optional::get).forEach(par -> {
			System.out.println("Sekcija_" + par.getKey().id);
			System.out.println("Polica_" + par.getValue().id);
			par.getValue().knjige.forEach(System.out::println);
		});
		
		System.out.println("\n============================ 4 ============================");
		b.sekcije.stream().forEach(sekcija -> {
			System.out.println("Sekcija -> " + sekcija.id);
			sekcija.police.forEach(polica -> {
				System.out.println("Polica -> " + polica.id);
				polica.knjige.stream().sorted(Comparator.comparing(knjiga -> knjiga.naziv, Comparator.reverseOrder())).forEach(System.out::println);
			});
		});
	}
}