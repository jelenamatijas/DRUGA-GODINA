import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		Biblioteka biblioteka = new Biblioteka();
		System.out.println(biblioteka);
		
		// 1.
		biblioteka.sekcije.stream().forEach(sekcija -> {
			int brojKnjigaUSekciji = sekcija.police.stream().mapToInt(polica -> polica.knjige.size()).sum();
			System.out.println("Sekcija: " + sekcija.id + ", broj knjiga: " + brojKnjigaUSekciji);
		});
		
		int ukupanBrojKnjiga = biblioteka.sekcije.stream().flatMap(sekcije -> sekcije.police.stream()).mapToInt(police -> police.knjige.size()).sum();
		
		System.out.println("Ukupan broj knjiga: " + ukupanBrojKnjiga);
		
		// 2.
		biblioteka.sekcije.stream().flatMap(sekcije -> sekcije.police.stream()).flatMap(police -> police.knjige.stream()).map(knjiga -> knjiga.autor).collect(Collectors.toSet()).forEach(System.out::println);
		
		// 3.
		biblioteka.sekcije.stream().map(sekcija -> sekcija.police.stream().max(Comparator.comparingInt(polica -> polica.knjige.size())).map(najvecaPolica -> Map.entry(sekcija, najvecaPolica))).map(Optional::get).forEach(entry -> {
			System.out.println("Sekcija ID: " + entry.getKey().id);
			System.out.println("Najveca polica ID: " + entry.getValue().id);
			entry.getValue().knjige.forEach(knjiga -> {
				System.out.println(knjiga);
			});
		});
		
		// 4.
		biblioteka.sekcije.stream().forEach(sekcija -> {
			System.out.println("Sekcija ID: " + sekcija.id);
			
			sekcija.police.forEach(polica -> {
				System.out.println("  Polica ID: " + polica.id);
				
				polica.knjige.stream().sorted(Comparator.comparing(knjiga -> knjiga.naslov, Comparator.reverseOrder())).forEach(knjiga -> {
					System.out.println(knjiga);
				});
			});
		});
		System.out.println("-------");
	}
}