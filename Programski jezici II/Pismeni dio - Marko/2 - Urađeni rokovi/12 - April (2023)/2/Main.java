import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("GRESKA fomrat: java Main <putanja_do_foldera_ili_fajla>");
			return;
		}
		
		LinkedHashMap<Character, Integer> numsMap = new LinkedHashMap<>();
		
		Path path = Paths.get(args[0]);
		if (Files.isRegularFile(path)) {
			List<String> lines;
			try {
				lines = Files.readAllLines(path);
			} catch (IOException ex) {
				System.out.println("GRESKA prilikom citanja linija");
				return;
			}
			Collections.sort(lines);
			Map<Character, List<String>> rowsMap = lines.stream().collect(Collectors.groupingBy(line -> line.charAt(0)));
			rowsMap.forEach((letter, rows) -> {
				numsMap.put(letter, rows.size());
				try (PrintWriter pw = new PrintWriter(new FileWriter("sortirani" + letter + ".txt"))) {
					for (String row : rows) {
						pw.println(row);
					}
				} catch (IOException ex) {
					System.out.println("GRESKA prilikom upisa u txt fajl");
					return;
				}
			});
			System.out.println("Direktorijum u kojem se nalaze fajlovi: " + System.getProperty("user.dir"));
			for (Map.Entry<Character, Integer> entry : numsMap.entrySet()) {
				System.out.println("Za pocetno slovo: " + entry.getKey() + " ima " + entry.getValue() + " redova");
			}
		} else if (Files.isDirectory(path)) {
			try {
				System.out.println("Fajl sa najvecom velicinom: " +
					Files.walk(path)
						.filter(Files::isRegularFile)
						.max(Comparator.comparingLong(p -> {
							try {
								return Files.size(p);
							} catch (IOException e) {
								return Long.MIN_VALUE;
							}
						}))
						.orElseThrow(() -> new RuntimeException("Nema fajlova u direktorijumu"))
				);

				long totalSize = Files.walk(path)
					.filter(Files::isRegularFile)
					.mapToLong(p -> {
						try {
							return Files.size(p);
						} catch (IOException e) {
							return 0;
						}
					})
					.sum();
				System.out.println("Ukupna velicina svih fajlova: " + totalSize);

				Files.walk(path)
					.filter(Files::isRegularFile)
					.sorted(Comparator.comparing(p -> p.getFileName().toString().toLowerCase()))
					.collect(Collectors.groupingBy(p -> {
						String fileName = p.getFileName().toString();
						int lastDot = fileName.lastIndexOf('.');
						return (lastDot == -1) ? "No Extension" : fileName.substring(lastDot + 1);
					}))
					.forEach((extension, paths) -> {
						System.out.println(extension + ":");
						paths.forEach(p -> System.out.println("  " + p.getFileName()));
					});

				Map<Path, File> filesMap = Files.walk(path)
					.filter(Files::isRegularFile)
					.collect(Collectors.toMap(Path::toAbsolutePath, Path::toFile));

				filesMap.forEach((p, f) -> System.out.println(p + ": " + f.getName()));

			} catch (IOException ex) {
				System.out.println("GRESKA prilikom rada sa folderom");
			}
		}
	}
}