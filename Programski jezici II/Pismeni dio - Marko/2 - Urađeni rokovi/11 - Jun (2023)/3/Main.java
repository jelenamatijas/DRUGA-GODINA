import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static ArrayList<Film> filmovi = new ArrayList<>();
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			filmovi.add(new Film());
		}
		
		String unos = "";
		Scanner scanner = new Scanner(System.in);
		while (!"kraj".equalsIgnoreCase(unos)) {
			System.out.println("1 - Prikaz filmova grupisanih po zanru uz ukupan broj filmova po zanru");
			System.out.println("2 - Prikaz ukupnog broja filmova snimljenih u datom opsegu godina");
			System.out.println("3 - Prikaz filmova svakog zanra sa najvecim i najmanjim budzetom");
			System.out.println("4 - Sortirati i ispisati sve filmove za datog glumca");
			System.out.println("5 - Prikazati prosjecno vrijeme trajanja filmova za zadati zanr. Pretpostaviti da je vrijeme trajanja filma dato u minutama, pa prilikom ispisa prosjecnog vremena trajanja, vrijeme prikazati u obliku sat minut");
			System.out.println("6 - Pronaci sve filmove ciji budzet prelazi 800 000. Pretpostaviti da su vrijednosti budzeta izrazene u americkim dolarima, pa filmove prikazati sortirane po nazivu filma, sa budzetom iskonvertovanom u BAM (vrijednost u BAM racunati kao 1.82*USD)");
			
			unos = scanner.nextLine();
			if (Integer.parseInt(unos) == 1) {
				funkcija1();
			} else if (Integer.parseInt(unos) == 2) {
				funkcija2();
			} else if (Integer.parseInt(unos) == 3) {
				funkcija3();
			} else if (Integer.parseInt(unos) == 4) {
				funkcija4();
			} else if (Integer.parseInt(unos) == 5) {
				funkcija5();
			} else if (Integer.parseInt(unos) == 6) {
				funkcija6();
			}
		}
		
	}
	
	private static void funkcija1() {
		filmovi.stream().collect(Collectors.groupingBy(film -> film.zanr)).forEach((zanr, films) -> System.out.println("\n" + zanr + ": " + films.size() + films));
	}
	
	private static void funkcija2() {
		System.out.println("\nFILMOVI SNIMLJENI OD 2018 DO 2022\n");
		filmovi.stream().filter(film -> film.godinaObjavljivanja >= 2018 && film.godinaObjavljivanja <= 2020).forEach(System.out::println);
	}
	
	private static void funkcija3() {
		filmovi.stream().collect(Collectors.groupingBy(film -> film.zanr)).forEach((zanr, films) -> {
			Film minFilm = films.stream().min(Comparator.comparingDouble(film -> film.budzet)).get();
			Film maxFilm = films.stream().max(Comparator.comparingDouble(film -> film.budzet)).get();
			
			System.out.println("\n" + zanr + ": MIN BUDZET: " + minFilm);
			System.out.println("\n" + zanr + ": MAX BUDZET: " + maxFilm);
		});
	}
	
	private static void funkcija4() {
		Glumac keyGlumac = new Glumac();
		System.out.println("\nSORTIRANI FILMOVI GLUMCA " + keyGlumac);
		filmovi.stream().filter(film -> film.glumci.stream().anyMatch(glumac -> glumac.equals(keyGlumac))).sorted(Comparator.comparing(film -> film.godinaObjavljivanja, Comparator.reverseOrder())).forEach(System.out::println);
	}
	
	private static void funkcija5() {
		double prosjek = filmovi.stream().filter(film -> film.zanr == Zanr.AKCIJA).mapToInt(film -> film.vrijemeTrajanja).average().getAsDouble();
		int brojSati = 0;
		while (prosjek >= 60) {
			// System.out.println("\nPROSJEK: " + prosjek);
			// System.out.println("\n" + brojSati);
			brojSati++;
			prosjek -= 60.0;
		}
		System.out.println("\nPROSJECNO VRIJEME TRAJANJA FILMA IZ ZANRA AKCIJA: " + brojSati + "." + (int) prosjek);
	}
	
	private static void funkcija6() {
		System.out.println("\nFILMOVI SA BUDZETOM PREKO 800,000 SORTIRANI: ");
		filmovi.stream().filter(film -> film.budzet >= 800000).sorted(Comparator.comparing(film -> film.naziv)).forEach(film -> System.out.println(film + " budzet u KM: " + film.budzet * 1.82 + "KM\n"));
	}
}