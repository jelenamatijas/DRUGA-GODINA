import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Stampac extends Uredjaj {
	
	public Stampac(Path path) {
		super(path);
	}
	
	@Override
	public void run() {
		
		while (radi) {
			long pocetakRunde = System.currentTimeMillis();
			
			try {
				List<Path> paths = Files.walk(this.path).collect(Collectors.toList());
				for (Path path : paths) {
					if (Files.isRegularFile(path) && Files.size(path) <= 2048) {
						System.out.println("STAMPAC: Naziv fajla: " + path.getFileName());
						System.out.println("STAMPAC: Sadrzaj fajla: " + Files.readString(path));
					}
				}
			} catch (IOException ex) {
				System.out.println("STAMPAC: GRESKA prilikom rada sa fajlovima");
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