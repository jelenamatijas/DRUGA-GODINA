import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.nio.file.StandardCopyOption;

public class Kopir extends Uredjaj {
	
	public Kopir(Path path) {
		super(path);
	}
	
	@Override
	public void run() {
		
		while (radi) {
			long pocetakRunde = System.currentTimeMillis();
			
			try {
				int numOfCopiedFiles = 0;
				List<Path> paths = Files.walk(this.path).collect(Collectors.toList());
				for (Path path : paths) {
					if (Files.isRegularFile(path)) {
						String destinationFile = path.getParent().toString() + "/COPY-" + path.getFileName();
						Path destination = Paths.get(destinationFile);
						synchronized (Main.lock) {
							Files.copy(path, destination, StandardCopyOption.REPLACE_EXISTING);
						}
						numOfCopiedFiles++;
					}
				}
				System.out.println("KOPIR: Broj kopiranih fajlova: " + numOfCopiedFiles);
			} catch (IOException ex) {
				System.out.println("KOPIR: GRESKA prilikom rada sa fajlovima");
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