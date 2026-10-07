import java.util.*;
import java.util.stream.*;

class Main{
	public static void main(String args[]){
		Biblioteka b = new Biblioteka();
		System.out.println(b);
		
		//1.
		b.sekcije.stream().forEach(sekcija -> {
			int brojKnjigaUSkeciji = sekcija.police.stream().mapToInt(polica -> polica.knjige.size()).sum();
			System.out.println("Broj knjiga u sekciji " + sekcija.idSekcije + " je " + brojKnjigaUSkeciji);
		});
		
		//2.
		
		b.sekcije.stream().flatMap(sekcija -> sekcija.police.stream()).flatMap(polica -> polica.knjige.stream()).map(knjiga -> knjiga.autor).collect(Collectors.toSet()).forEach(System.out::println);
	
		//3.
		b.sekcije.stream().map(sekcija -> sekcija.police.stream().max(Comparator.comparingInt(polica -> polica.knjige.size())).map(najvecaPolica -> Map.entry(sekcija, najvecaPolica))).map(Optional::get).forEach(entry ->{
			System.out.println("Sekcija ID: " + entry.getKey().idSekcije);
			System.out.println("Najveca polica: " + entry.getValue().idPolice);
			entry.getValue().knjige.forEach(k -> System.out.println(k));
		});
		
		//4.
		
		b.sekcije.stream().forEach(sekcija -> {
			System.out.println("ID sekcije: " + sekcija.idSekcije);
			sekcija.police.stream().forEach(polica -> {
				System.out.println("ID police: " + polica.idPolice);
				polica.knjige.stream().sorted(Comparator.comparing(knjiga -> knjiga.naslov, Comparator.reverseOrder())).forEach(knjiga -> {
					System.out.println(knjiga);
				});
			});
		});
	}
}