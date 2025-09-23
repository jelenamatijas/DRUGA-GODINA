import java.util.*;
import java.util.stream.*;

class Main{
	
	public static void main(String[] args){
		List<Oglas> oglasi = new ArrayList<>();
		for(int i=0; i<20; i++){
			oglasi.add(new Oglas());
		}
		
		oglasi.forEach(System.out::println);
		
		System.out.println("===================================================================================");
		//1
		oglasi.stream().collect(Collectors.groupingBy(oglas -> oglas.datumObjavljivanja, Collectors.counting())).forEach((dan, broj) -> System.out.println("DAN: " + dan + " Broj oglasa: " + broj));
		
		System.out.println("===================================================================================");
		
		//2
		Map<Oglas.Kategorija, List<Oglas>> oglasiPoKategoriji = oglasi.stream().collect(Collectors.groupingBy(oglas -> oglas.kategorija));
		for(Oglas.Kategorija s : oglasiPoKategoriji.keySet()){
			double p = oglasiPoKategoriji.get(s).stream().mapToInt(o -> o.plata).average().getAsDouble();
			System.out.println("Kategorija " + s + " Prosjecna plata: " + p);
		}
		
		System.out.println("===================================================================================");
		
		//3
		String grad = oglasi.stream().collect(Collectors.groupingBy(oglas -> oglas.grad, Collectors.counting())).
						entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("Nema oglasa");
		System.out.println("Grad sa najvise oglasa: " + grad);
		
		System.out.println("===================================================================================");
		
		//4
		
		oglasi.stream().collect(Collectors.groupingBy(oglas -> oglas.datumObjavljivanja.split("\\.")[2])).forEach((godina, oglasiUGodini) -> {
			System.out.println("Godina: " + godina);
			oglasiUGodini.forEach(System.out::println);
		});
		
		System.out.println("===================================================================================");
		
		//5
		
		oglasi.stream().sorted(Comparator.comparingInt(o -> -o.vrijemeTrajanja)).forEach(System.out::println);
		
		System.out.println("===================================================================================");
		
		//6
		
		oglasi.stream().collect(Collectors.groupingBy(oglas -> oglas.kategorija, Collectors.maxBy(Comparator.comparingInt(oglas -> oglas.plata)))).
						forEach((kategorija, o) -> 
						{
							System.out.println("Kategorija: " + kategorija + " -> \n" + o.get());
						});
						
		
		System.out.println("===================================================================================");
		
		//7
		
		double prosjecnoIskustvo = oglasi.stream().mapToInt(o -> o.iskustvo).average().getAsDouble();
		System.out.println("Prosjecno iskustvo: " + prosjecnoIskustvo);

		
		System.out.println("===================================================================================");
		
		//7
		
		oglasi.stream().collect(Collectors.groupingBy(o -> o.kategorija, Collectors.partitioningBy(o -> o.remote, Collectors.counting()))).
					forEach((kategorija, map) -> {
						long count = map.getOrDefault(true, 0L);
						long ukupno = count + map.getOrDefault(false, 0L);
						double procenat = ukupno>0 ? (double)count/ukupno*100 : 0;
						System.out.println(kategorija +":"+procenat + "%");
					});
	}
		
}