import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static Random random = new Random();
	
	public static void main(String[] args) {
		ArrayList<Oglas> oglasi = new ArrayList<>();
		for (int i = 0; i < 60; i++) {
			oglasi.add(new Oglas());
		}
		
		System.out.println("\nUkupan broj oglasa po datumima:");
		oglasi.stream().collect(Collectors.groupingBy(o -> o.datum, Collectors.counting())).forEach((datum, count) -> System.out.println(datum + ": " + count));
		
		Oglas.Kategorija kategorija1 = Oglas.Kategorija.IT;
		Oglas.Kategorija kategorija2 = Oglas.Kategorija.EKONOMIJA;
		Oglas.Kategorija kategorija3 = Oglas.Kategorija.MEDICINA;
		Oglas.Kategorija kategorija4 = Oglas.Kategorija.NOVINARSTVO;
		Oglas.Kategorija kategorija5 = Oglas.Kategorija.PRAVO;
		
		double prosjecnaPlata1 = oglasi.stream().filter(o -> o.kategorija == kategorija1).mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("\nProsjecna plata u kategoriji " + kategorija1 + " je: " + prosjecnaPlata1);
		
		double prosjecnaPlata2 = oglasi.stream().filter(o -> o.kategorija == kategorija2).mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("Prosjecna plata u kategoriji " + kategorija2 + " je: " + prosjecnaPlata2);
		
		double prosjecnaPlata3 = oglasi.stream().filter(o -> o.kategorija == kategorija3).mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("Prosjecna plata u kategoriji " + kategorija3 + " je: " + prosjecnaPlata3);
		
		double prosjecnaPlata4 = oglasi.stream().filter(o -> o.kategorija == kategorija4).mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("Prosjecna plata u kategoriji " + kategorija4 + " je: " + prosjecnaPlata4);
		
		double prosjecnaPlata5 = oglasi.stream().filter(o -> o.kategorija == kategorija5).mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("Prosjecna plata u kategoriji " + kategorija5 + " je: " + prosjecnaPlata5);
		
		String gradSaNajvisePoslova = oglasi.stream().collect(Collectors.groupingBy(o -> o.grad, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("Nema oglasa");
		System.out.println("\nGrad sa najvise oglasa za posao: " + gradSaNajvisePoslova);
		
		System.out.println("\nOglasi grupisani po godinama:");
		oglasi.stream().collect(Collectors.groupingBy(o -> o.datum.split("\\.")[2])).forEach((godina, oglasiUGodini) -> {
			System.out.println("Godina " + godina + ": ");
			oglasiUGodini.forEach(System.out::println);
		});
		
		System.out.println("\nOglasi sortirani po vremenu trajanja:");
		oglasi.stream().sorted(Comparator.comparingInt(o -> -o.vrijemeTrajanja)).forEach(System.out::println);
		
		System.out.println("\nNajbolje placeni poslovi po kategorijama:");
		oglasi.stream().collect(Collectors.groupingBy(o -> o.kategorija, Collectors.maxBy(Comparator.comparingInt(o -> o.plata)))).forEach((kategorija, najboljiOglas) -> {
			System.out.println(kategorija + ": " + najboljiOglas.orElse(null));
		});
		
		double prosjecanBrojGodinaRadnogIskustva = oglasi.stream().mapToInt(o -> o.radnoIskustvo).average().orElse(0);
		System.out.println("\nProsjecno radno iskustvo: " + prosjecanBrojGodinaRadnogIskustva + " godina");
		
		System.out.println("\nProcenat remote poslova po kategorijama:");
		oglasi.stream().collect(Collectors.groupingBy(o -> o.kategorija, Collectors.partitioningBy(o -> o.remote, Collectors.counting()))).forEach((kategorija, map) -> {
                    long remoteCount = map.getOrDefault(true, 0L);
                    long ukupno = remoteCount + map.getOrDefault(false, 0L);
                    double procenat = ukupno > 0 ? (remoteCount * 100.0 / ukupno) : 0;
                    //System.out.println(kategorija + ": " + procenat + "% remote");
					System.out.printf("%s: %.2f%s remote\n", kategorija, procenat, "%");
                });
	}
}