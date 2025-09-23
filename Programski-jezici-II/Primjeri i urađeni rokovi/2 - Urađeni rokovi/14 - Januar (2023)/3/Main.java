import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class Main {
	
	public static List<Product> parseCSV() {
		List<Product> products = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(Paths.get("products.csv"));
			lines.remove(0);
			for (String line : lines) {
				String[] parts = line.split("; ");
				products.add(new Product(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4], Double.parseDouble(parts[5])));
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom citanja CSV fajla");
		}
		return products;
	}
	
	public static void main(String[] args) {
		List<Product> products = parseCSV();
		
		/*
		// a.
		products.stream().collect(Collectors.groupingBy(product -> product.category)).forEach((category, ps) -> {
			double sum = ps.stream().mapToDouble(p -> p.quantity).sum();
			System.out.println("Za kategoriju: " + category + " suma kolicina je: " + sum);
		});
		
		// b.
		double sum = products.stream().filter(p -> p.quantity >= 50.0 && p.quantity <= 70.0).mapToDouble(p -> p.quantity).sum();
		System.out.println("Ukupna kolicina proizvoda kojih na stanju ima izmedju 50 i 70 je: " + sum);
		
		// c.
		products.stream().collect(Collectors.groupingBy(p -> p.category)).forEach((category, ps) -> {
			System.out.println("\nKategorija: " + category + "\n");
			System.out.println("Min cijena: " + ps.stream().mapToDouble(p -> p.price).min().getAsDouble());
			System.out.println("Max cijena: " + ps.stream().mapToDouble(p -> p.price).max().getAsDouble());
		});
		
		// d.
		products.stream().filter(p -> p.currency.equals("KM")).sorted(Comparator.comparing(p -> p.price, Comparator.reverseOrder())).forEach(System.out::println);
		
		// e.
		products.stream().filter(p -> p.currency.equals("EUR")).sorted(Comparator.comparing(p -> p.name)).forEach(p -> {
			System.out.println(p.price * 1.95);
		});
		*/
		
		// Moje vjezbanje
		Comparator<Product> cmp = Comparator.comparingDouble((Product p) -> p.price).reversed();
		products.stream().sorted(Comparator.comparing((Product p) -> p.name)).forEach(System.out::println);
		System.out.println("Product with highest price: " + products.stream().reduce((p1, p2) -> p1.price > p2.price ? p1 : p2).get());
		
		// 2024: septembar 2, jun, januar
		// 2023: septembar 1, april
		// 2022: oktobar, jul, februar
	}
}