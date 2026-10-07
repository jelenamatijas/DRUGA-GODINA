import java.nio.file.*;
import java.util.stream.*;
import java.util.*;
import java.io.*;

public class Main {
	
	public static String keyWord;
	public static Map<Path, Integer> map = new HashMap<>();
	
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("GRESKA format: java Main -d putanja_do_foldera -w rijec");
			return;
		}
		
		Path folderPath = Paths.get(args[1]);
		keyWord = args[3];
		
		List<Path> paths;
		try {
			paths = Files.walk(folderPath).collect(Collectors.toList());
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom ucitavanja putanja");
			return;
		}
		
		for (Path path : paths) {
			if (path.toString().endsWith(".txt")) {
				obradiTxtFajl(path);
			}
		}
		
		Map<Path, Integer> sortedMap = new LinkedHashMap<>();
		
		List<Map.Entry<Path, Integer>> pairsList = new ArrayList<>(map.entrySet());
		Collections.sort(pairsList, new Comparator<Map.Entry<Path, Integer>>() {
			@Override
			public int compare(Map.Entry<Path, Integer> e1, Map.Entry<Path, Integer> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		
		for (Map.Entry<Path, Integer> entry : pairsList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		for (Map.Entry<Path, Integer> entry : sortedMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
	}
	
	public static void obradiTxtFajl(Path path) {
		int numOfWords = 0;
		
		try {
			String[] words = Files.readString(path).trim().split("\\s+");
			for (String word : words) {
				if (word.equals(keyWord)) {
					numOfWords++;
				}
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom obrade txt fajla");
			return;
		}
		
		if (numOfWords > 0) {
			map.put(path, numOfWords);
		}
	}
}