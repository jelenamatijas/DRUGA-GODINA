import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main{
	static public void main(String args[]){
		List<Product> products = new ArrayList<>();
		try(BufferedReader bf = new BufferedReader(new FileReader("products.csv"))){
			bf.readLine();
			String s = null;
			while((s = bf.readLine())!=null){
				String podaci[] = s.split(";");
				Product p = new Product(podaci[0],podaci[1], podaci[2], Double.parseDouble(podaci[3]), podaci[4], Integer.parseInt(podaci[5]));
				products.add(p);
			}
		}catch(Exception e){
			System.out.println("Greska pri citanju podataka iz fajla.");
		}
		
		products.stream().forEach(p -> System.out.println(p));
		
		System.out.println("====================== 1 ======================\n");
		products.stream().collect(Collectors.groupingBy((Product p) -> p.category, Collectors.summingInt((Product p) -> p.quantity))).forEach((k, v) -> System.out.println(k + " -> " + v));
		
		System.out.println("====================== 2 ======================\n");
		products.stream().filter(p -> p.quantity >=50 && p.quantity<=70).forEach(p -> System.out.println(p));
		
		System.out.println("====================== 3 ======================\n");
		products.stream().collect(Collectors.groupingBy((Product p) -> p.category)).forEach((cat, list) -> {
			Product min = list.stream().min(Comparator.comparing((Product p) -> p.price)).orElse(null);
			Product max = list.stream().max(Comparator.comparing((Product p) -> p.price)).orElse(null);
			
			System.out.println(cat + ":\n" + min + "\n" + max); 
		});
		
		System.out.println("====================== 4 ======================\n");
		products.stream().filter(p -> p.currency.equals("EUR")).sorted(Comparator.comparing((Product p) -> p.price)).forEach(p -> System.out.println(p));
		
		System.out.println("====================== 5 ======================\n");
		products.stream().filter(p -> p.currency.equals("EUR")).sorted(Comparator.comparing((Product p) -> p.name)).forEach(p -> {
			p.price = p.price*1.95;
			p.currency = "BAM";
			System.out.println(p);
		});
	}
}