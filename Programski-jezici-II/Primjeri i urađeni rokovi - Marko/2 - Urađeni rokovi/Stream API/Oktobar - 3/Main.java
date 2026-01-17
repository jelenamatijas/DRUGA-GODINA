import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		HashSet<Film> grupaFilmova1 = new HashSet<>();
		HashSet<Film> grupaFilmova2 = new HashSet<>();
		for (int i = 0; i < 20; i++) {
			grupaFilmova1.add(new Film());
			grupaFilmova2.add(new Film());
		}
		System.out.println("Grupa 1 prije spajanja: " + grupaFilmova1.size());
		grupaFilmova1.forEach(System.out::println);
		System.out.println("\nGrupa 2 prije spajanja: " + grupaFilmova2.size());
		grupaFilmova2.forEach(System.out::println);
		
		
		// 1.
		grupaFilmova2.stream().forEach(film -> grupaFilmova1.add(film));
		grupaFilmova2.clear();
		
		System.out.println("Grupa 1 nakon spajanja: " + grupaFilmova1.size());
		grupaFilmova1.forEach(System.out::println);
		System.out.println("\nGrupa 2 nakon brisanja: " + grupaFilmova2.size());
		grupaFilmova2.forEach(System.out::println);
		
		Set<String> razlicitiReziseri = grupaFilmova1.stream().sorted(Comparator.comparing(film -> film.reziser, Comparator.reverseOrder())).map(film -> film.reziser).collect(Collectors.toSet());
		System.out.println("\nRazliciti reziseri:");
		razlicitiReziseri.forEach(System.out::println);
		
		// 2.
		grupaFilmova1.stream().collect(Collectors.groupingBy(film -> film.zanr)).forEach((zanr, filmovi) -> System.out.println("\n" + zanr + ": " + filmovi));
		
		grupaFilmova1.stream().sorted(Comparator.comparing(film -> film.godinaIzdavanja, Comparator.reverseOrder())).forEach(System.out::println);
		
		System.out.println("\nSuma godina izdavanja filmova koji su DOKUMENTARAC i godina izdavanja djeljiva sa 5: " + grupaFilmova1.stream().filter(film -> (film.zanr == Film.Zanr.DOKUMENTARAC && (film.godinaIzdavanja % 5 == 0))).mapToInt(film -> film.godinaIzdavanja).sum());
		
		int randomNajduzi = random.nextInt(grupaFilmova1.size());
		int randomNajkraci = Main.random.nextInt(grupaFilmova1.size());
		
		while (randomNajkraci == randomNajduzi) {
			randomNajkraci = Main.random.nextInt(grupaFilmova1.size());
		}
		
		int i = 0;
		for (Film film : grupaFilmova1) {
			if (i == randomNajduzi) {
				film.naziv += "NAJDUZI";
			}
			if (i == randomNajkraci) {
				film.naziv = "nkr"; // najkraci
			}
			i++;
		}
		
		Film najkraciNazivFilm = grupaFilmova1.stream().min(Comparator.comparingInt(film -> film.naziv.length())).get();
		Film najduziNazivFilm = grupaFilmova1.stream().max(Comparator.comparingInt(film -> film.naziv.length())).get();
		System.out.println("\nFilm sa najkracim nazivom: " + najkraciNazivFilm);
		System.out.println("\nFilm sa najduzim nazivom: " + najduziNazivFilm);
	}
}