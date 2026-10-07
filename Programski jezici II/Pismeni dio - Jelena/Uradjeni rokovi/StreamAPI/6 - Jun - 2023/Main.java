import java.util.*;
import static java.util.stream.Collectors.*;

class Main{
	static Random rand = new Random();
	
	public static void main(String args[]){
		ArrayList<Film> filmovi = new ArrayList<>();
		for(int i=0; i<50;i++){
			filmovi.add(new Film());
		}
		filmovi.forEach(System.out::println);
		
		System.out.println("\n================================== 1 =================================="); 
		Map<Film.Zanr, List<Film>> poZanru = filmovi.stream().collect(groupingBy(f -> f.zanr, toList()));
		poZanru.forEach((zanr, lista) -> {
			System.out.println(zanr + ": " + lista.size());
			lista.forEach(System.out::println);
		});
		
		System.out.println("\n================================== 2 =================================="); 
		long brojFilmovaUOpsegu = filmovi.stream().filter(f -> (f.godina>=2015 && f.godina<=2020)).count();
		System.out.println("Broj filmova snimljenih izmedju 2015 i 2020 godine: " + brojFilmovaUOpsegu);
		
		System.out.println("\n================================== 3 =================================="); 
		Map<Film.Zanr, Film> najveciBudzet = filmovi.stream().collect(groupingBy(f -> f.zanr, collectingAndThen(maxBy(Comparator.comparing(f -> f.budzet)), opt -> opt.orElse(null))));
		System.out.println("FILMOVI PO KATEGORIJAMA SA NAJVECIM BUDZETOM:");
		najveciBudzet.forEach((zanr, film) -> {
			System.out.println(zanr + " -> " + film);
		});
		
		Map<Film.Zanr, Film> najmanjiBudzet = filmovi.stream().collect(groupingBy(f -> f.zanr, collectingAndThen(minBy(Comparator.comparing(f -> f.budzet)), opt -> opt.orElse(null))));
		System.out.println("FILMOVI PO KATEGORIJAMA SA NAJMANJIM BUDZETOM:");
		najmanjiBudzet.forEach((zanr, film) -> {
			System.out.println(zanr + " -> " + film);
		});
		
		System.out.println("\n================================== 4 =================================="); 
		
		Glumac glumac = new Glumac();
		System.out.println(glumac);
		filmovi.stream().filter(f -> f.glumci.contains(glumac)).sorted(Comparator.comparing(f -> f.budzet)).toList().forEach(System.out::println);
		
		
		System.out.println("\n================================== 5 =================================="); 
		double prosjecnoTrajanje = filmovi.stream().filter(f -> f.zanr == Film.Zanr.KOMEDIJA).mapToDouble(f -> f.trajanje).average().getAsDouble();
		int prosjecnoTrajanjeInt = (int)Math.round(prosjecnoTrajanje);
		System.out.println("Prosjecno trajanje filma zanra KOMEDIJA: " + (prosjecnoTrajanjeInt/60) + "." + (prosjecnoTrajanjeInt&60));
		
		System.out.println("\n================================== 6 =================================="); 
		List<Film> poBudzetu = filmovi.stream().filter(f -> f.budzet >=800000).toList();
		poBudzetu.forEach(f -> {
			f.budzet = f.budzet*1.82;
			System.out.println(f);
		});
	}
}