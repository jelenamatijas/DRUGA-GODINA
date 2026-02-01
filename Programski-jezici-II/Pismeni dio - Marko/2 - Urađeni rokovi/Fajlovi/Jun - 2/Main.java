import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static int duzina;
	public static String putanjaTxtFajla = "";
	public static int ukupanBrojRijeci = 0;
	
	public static void main(String[] args) {
		if (args.length != 4 || !args[0].equals("-d") || !args[2].equals("-l")) {
			System.out.println("GRESKA format: java Main -d <putanja_do_foldera> -l <duzina>");
			return;
		}
		
		String putanja = args[1];
		duzina = Integer.parseInt(args[3]);
		
		putanjaTxtFajla = "rijeci_duzine_" + duzina + ".txt";
		
		SortedMap<Path, Integer> mapaDuzina = new TreeMap<>();
		
		try {
			List<Path> paths = Files.walk(Paths.get(putanja)).collect(Collectors.toList());
			for (Path path : paths) {
				if (!Files.isDirectory(path) && path.toString().endsWith(".txt")) {
					int brojRijeci = obradiTxtFajl(path);
					mapaDuzina.put(path, brojRijeci);
					ukupanBrojRijeci += brojRijeci;
				}
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom rada sa fajlovima!");
			return;
		}
		
		mapaDuzina.forEach((path, duzina) -> System.out.println(path + " " + duzina));
		System.out.println("Ukupan broj rijeci date duzine: " + ukupanBrojRijeci);
	}
	
	public static int obradiTxtFajl(Path path) throws IOException {
		int brojRijeci = 0;
		List<String> linije = Files.readAllLines(path);
		for (String linija : linije) {
			linija = linija.replaceAll("[,.?!]", "").trim();
			String[] rijeci = linija.split(" ");
			for (String rijec : rijeci) {
				if (rijec.length() == duzina) {
					upisiRijec(rijec);
					brojRijeci++;
				}
			}
			// System.out.println(linija);
			// break;
		}
		
		return brojRijeci;
	}
	
	public static void upisiRijec(String rijec) {
		try (PrintWriter pw = new PrintWriter(rijec)) {
			pw.println(rijec);
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom upisa u zajednicki txt fajl");
			return;
		}
	}
}