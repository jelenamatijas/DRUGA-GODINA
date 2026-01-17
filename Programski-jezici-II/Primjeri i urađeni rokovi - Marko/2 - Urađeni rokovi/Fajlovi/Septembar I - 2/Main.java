import java.nio.file.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("GRESKA: format java Main <putanja_do_direktorijuma> <tekst>");
			return;
		}
		
		String putanja = args[0];
		String tekst = args[1];
		
		AtomicInteger brojObrisanihFajlova = new AtomicInteger(0);
		ArrayList<Integer> lista = new ArrayList<>();
		
		try {
			List<Path> paths = Files.walk(Paths.get(putanja)).collect(Collectors.toList());
			for (Path path : paths) {
				if (!Files.isDirectory(path)) {
					String nazivFajla = path.getFileName().toString();
					System.out.println(nazivFajla);
					nazivFajla = nazivFajla.substring(0, nazivFajla.lastIndexOf("."));
					if (nazivFajla.equalsIgnoreCase(tekst)) {
						System.out.println(path.toString());
						brojObrisanihFajlova.addAndGet(1);
						lista.add(0);
					}
				}
			}
		} catch (IOException ex) {
			return;
		}
		
		
		
		/*
		try {
			Files.walk(Paths.get(putanja)).forEach(path -> {
				if (!Files.isDirectory(path)) {
					String putanjaSaEkstenzijom = path.toString();
					int posljednjaTackaIndeks = putanjaSaEkstenzijom.lastIndexOf(".");
					String putanjaBezEkstenzije = putanjaSaEkstenzijom.substring(0, posljednjaTackaIndeks);
					
					String nazivFajla = path.getFileName().toString();
					nazivFajla = nazivFajla.substring(0, nazivFajla.lastIndexOf("."));
					if (nazivFajla.equalsIgnoreCase(tekst)) {
						System.out.println(putanjaBezEkstenzije);
						brojObrisanihFajlova.addAndGet(1);
						lista.add(0);
					}
				}
			});
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom rada sa fajlovima!");
			return;
		}
		*/
		System.out.println("\nUkupan broj fajlova koji odgovara unesenom tekstu: " + brojObrisanihFajlova.get());
		System.out.println("\nUkupan broj fajlova koji odgovara unesenom tekstu: " + lista.size());
	}
}