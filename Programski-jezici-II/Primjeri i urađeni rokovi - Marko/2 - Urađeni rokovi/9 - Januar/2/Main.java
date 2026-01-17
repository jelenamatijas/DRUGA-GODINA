import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	
	public static Path subfolderPath;
	
	public static void main(String[] args) {
		long pocetakMjerenja = System.currentTimeMillis();
		
		if (args.length != 2) {
			System.out.println("GRESKA format: java Main <putanja_do_root_foldera> <naziv_podfoldera>");
			return;
		}
		
		Path rootPath = Paths.get(args[0]);
		String subfolderName = args[1];
		subfolderPath = Paths.get(rootPath.toString() + "/" + subfolderName);
		
		try {
			Files.createDirectory(subfolderPath);
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom pravljenja subfoldera");
		}
		
		HashMap<String, List<Path>> pathMap = new HashMap<>();
		
		try {
			List<Path> paths = Files.walk(rootPath).collect(Collectors.toList());
			for (Path path : paths) {
				if (!Files.isDirectory(path)) {
					String onlyName = path.getFileName().toString();
					String fileName = path.toString();
					String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					// System.out.println(extension);
					pathMap.computeIfAbsent(extension, k -> new ArrayList<>()).add(path);
					placeInFolder(extension, path, onlyName);
				}
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom rada sa fajlovima");
		}
		
		for (Map.Entry<String, List<Path>> entry : pathMap.entrySet()) {
			System.out.println("=============");
			String extension = entry.getKey();
			int numOfFiles = entry.getValue().size();
			System.out.println("Broj fajlova " + extension + " ekstenzije: " + numOfFiles);
			long velicinaFoldera = 0;
			try {
				for (Path path : entry.getValue()) {
					velicinaFoldera += Files.size(path);
				}
			} catch (IOException ex) {
				System.out.println("GRESKA prilikom racunanja velicine foldera");
				return;
			}
			
			System.out.println("Velicina foldera: " + velicinaFoldera + " bytes");
			System.out.println("Prosjecna velicina fajla: " + ((double) velicinaFoldera / (double) entry.getValue().size()) + " bytes");
		}
		
		long krajMjerenja = System.currentTimeMillis();
		System.out.println("\nVrijeme trajanja programa: " + ((double) (krajMjerenja - pocetakMjerenja) / 1000) + "s");
	}
	
	private static void placeInFolder(String extension, Path sourcePath, String onlyName) throws IOException {
		Path folderPath = Paths.get(subfolderPath.toString() + "/" + extension);
		if (!Files.exists(folderPath)) {
			Files.createDirectory(folderPath);
		}
		Path destinationPath = Paths.get(folderPath.toString() + "/" + onlyName);
		Files.copy(sourcePath, destinationPath);
	}
}