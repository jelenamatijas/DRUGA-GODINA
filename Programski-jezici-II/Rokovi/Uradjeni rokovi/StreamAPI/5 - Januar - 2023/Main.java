import java.util.*;
import java.util.stream.*;
import java.io.*;
import static java.util.stream.Collectors.*; 

class Main{
	static ArrayList<Product> proizvodi = new ArrayList<>();
	private static void ucitajPodatke(){
		try(BufferedReader br = new BufferedReader(new FileReader("products.csv"))){
			br.readLine();
			String linija = "";
			while((linija = br.readLine()) != null){
				String[] data = linija.split(";");
				proizvodi.add(new Product(data[0].strip(), data[1].strip(), data[2].strip(), data[3].strip(), data[4].strip(), data[5].strip()));
			}
		}catch(IOException e){
			System.out.println("GRESKA prilikom citanja podataka iz csv fajla.");
		}
	}
	
	public static void main(String []args){
		ucitajPodatke();
		
		System.out.println("\n========================== 1 ==========================");
		Map<String, Double> ukupnoPoKategorijama = proizvodi.stream().collect(Collectors.groupingBy(p -> p.category, Collectors.summingDouble(p->Double.parseDouble(p.quantity))));
		ukupnoPoKategorijama.forEach((cat, qunt) -> System.out.println(cat + " -> " + qunt)); 
		
		System.out.println("\n========================== 2 ==========================");
		double ukupno = proizvodi.stream().mapToDouble(p -> Double.parseDouble(p.quantity)).filter(p -> p>=50.0 && p<=70.0).sum();
		System.out.println("Ukupno = " + ukupno);
		
		System.out.println("\n========================== 3 ==========================\nNajjeftiniji:");
		Map<String, Product> najjeftiniji = proizvodi.stream().collect(groupingBy(p -> p.category, collectingAndThen(minBy(Comparator.comparing(p -> Double.parseDouble(p.price))), opt -> opt.orElse(null)))); 
		najjeftiniji.forEach((cat, p) -> {
			System.out.println(cat + " -> " + p);
		});
		
		System.out.println("Najskuplji:");
		Map<String, Product> najskuplji = proizvodi.stream().collect(groupingBy(p -> p.category, collectingAndThen(maxBy(Comparator.comparing(p -> Double.parseDouble(p.price))), opt -> opt.orElse(null))));
		najskuplji.forEach((cat, p) -> {
			System.out.println(cat + " -> " + p);
		});
		
		System.out.println("\n========================== 4 ==========================");
		System.out.println("EUR:");
		proizvodi.stream().filter(p -> p.currency.equals("EUR")).sorted(Comparator.comparing(p -> Double.parseDouble(p.price))).forEach(p -> System.out.println(p));
		System.out.println("KM:");
		proizvodi.stream().filter(p -> p.currency.equals("KM")).sorted(Comparator.comparing(p -> Double.parseDouble(p.price))).forEach(p -> System.out.println(p));

		System.out.println("\n========================== 5 ==========================");
		proizvodi.stream().filter(p -> p.currency.equals("EUR")).sorted(Comparator.comparing(p -> p.name)).forEach(p -> {
			p.price = String.valueOf(1.95 * Double.parseDouble(p.price));
			p.currency = "KM";
			System.out.println(p);
		});
	}
}