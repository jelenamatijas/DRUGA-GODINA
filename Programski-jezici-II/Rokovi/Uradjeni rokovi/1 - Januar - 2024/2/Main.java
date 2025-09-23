import java.util.*;
import java.io.*;
import java.util.stream.*;
import java.nio.file.*;

class Main{

	public static void main(String args[]){
		long start = System.currentTimeMillis();
		
		if(args.length != 2){
			System.out.println("Pogresan unos argumenata!");
			return;
		}
		
		
		Path rootFolder = Paths.get(args[0]);
		String subfolderName = args[1];
		Path subfolderPath = Paths.get(rootFolder + File.separator + subfolderName);
		
		try{
			Files.createDirectory(subfolderPath);
		}catch(IOException e){
			System.out.println("Greska pri kreiranju direktorijuma!");
		}
		
		HashMap<String, List<Path>> pathMap = new HashMap<>();
		
		try{
			List<Path> paths = Files.walk(rootFolder).collect(Collectors.toList());
			for(Path path : paths){
				if(!Files.isDirectory(path)){
					String onlyName = path.getFileName().toString();
					String fileName = path.toString();
					String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
					
					pathMap.computeIfAbsent(extension, k -> new ArrayList<>()).add(path);
					
					Path folderPath = Paths.get(subfolderPath + File.separator + extension);
					if(!Files.exists(folderPath)){
						Files.createDirectory(folderPath);
					}
					Path destinationPath = Paths.get(folderPath + File.separator + onlyName);
					Files.copy(path, destinationPath);
				}
			}
		}catch(IOException e){
			System.out.println("Greska pri radu sa fajlovima.");
		}
		
		pathMap.forEach((ext, paths) -> {
			System.out.println("************************");
			int size = paths.size();
			System.out.println("Broj fajlova sa ekstenzijom: " + ext + " je " + size + ".");
			double filesSize = 0;
			for(Path p : paths){
				try{
					filesSize += Files.size(p);
				}catch(IOException e){
					System.out.println("Desila se greska pri racunanju velicine fajla.");
				}
			}
			System.out.println("Prosjecna velicina fajla u ovom folderu je: " + (filesSize/size));
		});
		
		long stop = System.currentTimeMillis();
		System.out.println("Vrijeme izvrsavanja: " + ((double)(stop-start)/1000) + "s.");
		
	}
}