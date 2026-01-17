import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Skener extends Uredjaj {
	
	public Skener(Path path) {
		super(path);
	} 
	
	@Override
	public void run() {
		
		while (radi) {
			long pocetakRunde = System.currentTimeMillis();
			try {
				int numOfFiles = 0;
				int numOfFolders = 0;
				List<Path> paths = Files.walk(this.path).collect(Collectors.toList());
				for (Path path : paths) {
					if (Files.isRegularFile(path)) {
						numOfFiles++;
					} else if (Files.isDirectory(path)) {
						numOfFolders++;
					}
				}
				System.out.println("SKENER: Broj fajlova u folderu: " + numOfFiles);
				System.out.println("SKENER: Broj direktorijuma u folderu: " + numOfFolders);
				
				int randomEkstenzija = random.nextInt(5);
				if (randomEkstenzija == 0) {
					paths.stream().filter(path -> path.toString().endsWith(Ekstenzija.txt.name())).forEach(System.out::println);
				} else if (randomEkstenzija == 1) {
					paths.stream().filter(path -> path.toString().endsWith(Ekstenzija.jpg.name())).forEach(System.out::println);
				} else if (randomEkstenzija == 1) {
					paths.stream().filter(path -> path.toString().endsWith(Ekstenzija.png.name())).forEach(System.out::println);
				} else if (randomEkstenzija == 1) {
					paths.stream().filter(path -> path.toString().endsWith(Ekstenzija.docx.name())).forEach(System.out::println);
				} else if (randomEkstenzija == 1) {
					paths.stream().filter(path -> path.toString().endsWith(Ekstenzija.pdf.name())).forEach(System.out::println);
				}
				
			} catch (IOException ex) {
				System.out.println("SKENER: GRESKA prilikom rada sa fajlovima");
				ex.printStackTrace();
			}
			
			// radi = false;
			
			long krajRunde = System.currentTimeMillis();
			brojRadnihMiliSekundi += (krajRunde - pocetakRunde);
			if (brojRadnihMiliSekundi > 2000) {
				radi = false;
			}
		}
		
		synchronized (Main.lock) {
			Main.brojUredjajaKojiSuZavrsili++;
		}
	}
}