import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
	
	public static <T extends Podatak> List<T> filtrirajISortiraj(List<Predicate<T>> listaPredikata, int pocetniIndeks, int krajnjiIndeks, List<T>... listePodataka) {
		List<T> listaRezultata = new ArrayList<>();
		
		for (List<T> lista : listePodataka) {
			for (T podatak : lista) {
				if (listaPredikata.stream().allMatch(predikat -> predikat.test(podatak))) {
					listaRezultata.add(podatak);
				}
			}
		}
		//listaRezultata = listaRezultata.stream().sorted(Comparator.comparingInt(podatak -> -podatak.hashCode())).collect(Collectors.toList());
		
		//listaRezultata = listaRezultata.stream().sorted(Comparator.comparing(podatak -> podatak.hashCode(), Comparator.reverseOrder())).collect(Collectors.toList());
		
		//listaRezultata.sort(Comparator.comparing(podatak -> podatak.hashCode(), Comparator.reverseOrder()));
		//listaRezultata.sort(Comparator.comparingInt(podatak -> podatak.hashCode()).reversed());
		listaRezultata.sort((a, b) -> Integer.compare(b.hashCode(), a.hashCode()));
		
		return listaRezultata.subList(pocetniIndeks, Math.min(krajnjiIndeks, listaRezultata.size()));
		
	} 
	
	public static void main (String[] args) {
		List<Student> stundeti1 = new ArrayList<>();
		List<Student> stundeti2 = new ArrayList<>();
		List<Student> stundeti3 = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			stundeti1.add(new Student());
			stundeti2.add(new Student());
			stundeti3.add(new Student());
		}
		
		List<Predicate<Student>> listaPredikata = new ArrayList<>();
		
		Predicate<Student> predikat1 = student -> Integer.parseInt(student.ime.substring(3)) >= 0;
		Predicate<Student> predikat2 = student -> Integer.parseInt(student.prezime.substring(7)) >= 0;
		Predicate<Student> predikat3 = student -> Integer.parseInt(student.brojIndeksa.substring(6)) <= 10;
		
		listaPredikata.add(predikat1);
		listaPredikata.add(predikat2);
		listaPredikata.add(predikat3);
		
		System.out.println(filtrirajISortiraj(listaPredikata, 0, 10, stundeti1, stundeti2, stundeti3));
	}
}