import java.util.*;
import java.util.stream.*;

class Main{
	static Random rand = new Random();
	
	static public void main(String args[]){
		List<Film> filmovi = new ArrayList<>();
		for(int i=0;i<50;i++){
			filmovi.add(new Film());
		}
		System.out.println("\n======================= FILMOVI =======================");
		filmovi.forEach(f -> System.out.println(f));
		
		System.out.println("\n======================= a =======================");
		filmovi.stream().collect(Collectors.groupingBy((Film f) -> f.zanr)).forEach((zanr, lista) -> {
			System.out.println(zanr + ": " + lista.size());
			lista.forEach(f -> System.out.println(f));
		});
		
		System.out.println("\n======================= b =======================");
		long ukupanBrojURasponu = filmovi.stream().filter((Film f) -> f.godina>1995 && f.godina<2010).count();
		System.out.println("Ukupan broj filmova u rasponu od 1995. do 2010. je: " + ukupanBrojURasponu);
		
		System.out.println("\n======================= c =======================");
		filmovi.stream().collect(Collectors.groupingBy((Film f) -> f.zanr)).forEach((zanr, lista) -> {
			Film min = lista.stream().min(Comparator.comparingDouble((Film f) -> f.budzet)).get();
			Film max = lista.stream().max(Comparator.comparingDouble((Film f) -> f.budzet)).get();
			System.out.println(zanr + ":");
			System.out.println(min);
			System.out.println(max);
		});
		
		System.out.println("\n======================= d =======================");
		Glumac g = filmovi.get(0).glumci.get(0);
		filmovi.stream().filter((Film f) -> f.glumci.contains(g)).sorted().collect(Collectors.toList()).forEach(f -> System.out.println(f));
		
		System.out.println("\n======================= e =======================");
		int suma = (int)filmovi.stream().mapToInt((Film f) -> f.trajanje).average().orElse(0.0);
		System.out.println("Prosjecno trajanje filma: " + (suma/60) + "." + (suma%60));
		
		System.out.println("\n======================= g =======================");
		filmovi.stream().filter((Film f) -> f.budzet>800000).peek((Film f) -> {f.budzet = f.budzet*1.82;}).sorted(Comparator.comparing(f -> f.naziv)).forEach(f -> System.out.println(f));
		
	}
}