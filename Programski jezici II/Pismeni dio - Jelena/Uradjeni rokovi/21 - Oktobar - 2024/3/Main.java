import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Main{
	static Random rand = new Random();
	
	public static void main(String []args){
		HashSet<Film> filmovi1 = new HashSet<>();
		HashSet<Film> filmovi2 = new HashSet<>();
		for(int i=0;i<20;i++){
			filmovi1.add(new Film());
			filmovi2.add(new Film());
		}
		System.out.println("\n========================== FILMOVI 1 ==========================");
		filmovi1.forEach(System.out::println);
		System.out.println("\n========================== FILMOVI 2 ==========================");
		filmovi2.forEach(System.out::println);
		
		System.out.println("\n========================== 1 ==========================");
		filmovi2.stream().forEach(f -> filmovi1.add(f));
		filmovi2.clear();
		System.out.println("Ukupan broj filmovi u prvoj grupi sada je: " + filmovi1.size());
		filmovi1.stream().map(f -> f.reditelj).collect(Collectors.toSet()).forEach(System.out::println);
		
		System.out.println("\n========================== 2 ==========================");
		filmovi1.stream().collect(Collectors.groupingBy(f -> f.zanr)).forEach((zanr, lista) -> {
			System.out.println("Zanr -> " + zanr);
			lista.forEach(System.out::println);
		});
		
		System.out.println("\n========================== 3 ==========================");
		filmovi1.stream().sorted(Comparator.comparing(f -> f.godina, Comparator.reverseOrder())).forEach(System.out::println);
		
		System.out.println("\n========================== 4 ==========================");
		Function<HashSet<Film>, Integer> poGodiniIzdavanja = lista -> lista.stream().filter(f -> (f.zanr == Film.Zanr.DOKUMENTARAC) && (f.godina%5==0)).mapToInt(f -> f.godina).sum();
		System.out.println("Suma godina izavanja: " + poGodiniIzdavanja.apply(filmovi1));
		
		System.out.println("\n========================== 5 ==========================");
		Film najkraci = filmovi1.stream().min(Comparator.comparingInt(f -> f.naziv.length())).get();
		Film najduzi = filmovi1.stream().max(Comparator.comparingInt(f -> f.naziv.length())).get();
		System.out.println("Sa najkracim: " + najkraci + "\nSa najduzim: " + najduzi);
	}
}