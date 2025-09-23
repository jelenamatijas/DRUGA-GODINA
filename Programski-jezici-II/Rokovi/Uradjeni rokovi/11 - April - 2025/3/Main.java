import java.util.stream.*;
import java.util.*;
import java.io.*;
import static java.util.stream.Collectors.*;
import java.util.function.*;

class Main{
	public static void main(String args[]){
		HashSet<Film> filmovi = new HashSet<>();
	
		for(int i=0; i< 10;){
			if(filmovi.add(new Film(Film.Zanr.NAUCNA_FANTASTIKA))){
				i++;
			}
		}
		for(int i=0; i< 10;){
			if(filmovi.add(new Film(Film.Zanr.HOROR))){
				i++;
			}
		}
		for(int i=0; i< 10;){
			if(filmovi.add(new Film(Film.Zanr.DRAMA))){
				i++;
			}
		}
		for(int i=0; i< 10;){
			if(filmovi.add(new Film(Film.Zanr.KOMEDIJA))){
				i++;
			}
		}
		for(int i=0; i< 10;){
			if(filmovi.add(new Film(Film.Zanr.AKCIJA))){
				i++;
			}
		}
		
		filmovi.stream().forEach(film -> System.out.println(film));
		
		System.out.println("\n===============================================================================");
		
		//1
		Map<Film.Zanr, Double> ocjene = filmovi.stream().collect(groupingBy(Film::getZanr,averagingDouble(Film::getOcjena)));
		ocjene.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(film -> System.out.println(film.getKey() + " -> " + film.getValue()));
	
		System.out.println("\n===============================================================================");
		
		//2
		double projekSvih = (filmovi.stream().mapToDouble(Film::getOcjena).average()).getAsDouble();
		long veciOdProsjeka = filmovi.stream().filter(film -> film.getOcjena() > projekSvih).count();
		System.out.println("Postotak: " + ((double)veciOdProsjeka/filmovi.size()*100) + "%");
		
		System.out.println("\n===============================================================================");
		
		//3
		Map<Integer, Long> countByDecade = filmovi.stream().collect(groupingBy(f -> (f.godina/10)*10, TreeMap::new, counting()));
		countByDecade.forEach((decade, count) -> System.out.println("Decade: " + decade + " -> " + count));
	
		System.out.println("\n===============================================================================");
		
		//4
		Function<Film, Integer> getTrajanje = Film::getTrajanje;
		int ukupnoTrajanje = filmovi.stream().filter(film -> film.getZanr() == Film.Zanr.NAUCNA_FANTASTIKA && film.godina%2 == 0).map(getTrajanje).mapToInt(Integer::intValue).sum();
		System.out.println("Ukupno trajanje: " + ukupnoTrajanje);
		System.out.println("\n===============================================================================");
		
		//5
		Optional<Film> najkraci = filmovi.stream().min(Comparator.comparingInt(f -> f.trajanje));
		System.out.println("Film sa najkracim trajanjem: " + najkraci.get());
		Optional<Film> najduzi = filmovi.stream().max(Comparator.comparingInt(f -> f.trajanje));
		System.out.println("Film sa najduzim trajanjem: " + najduzi.get());
		Optional<Film> najpriblizniji = filmovi.stream().min(Comparator.comparingDouble(f -> Math.abs(f.trajanje-projekSvih)));
		System.out.println("Film sa najpribliznijim trajanjem srednjoj vrijednosti (" + projekSvih + "): " + najpriblizniji.get());
	}
	
	
}