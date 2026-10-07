import java.nio.file.*;
import java.io.*;
import java.util.stream.*;
import java.util.*;

public class FileWatcher extends Thread {
	
	Path rootPath;
	boolean radi;
	
	public FileWatcher(Path rootPath) {
		this.rootPath = rootPath;
	}
	
	public static ArrayList<Integer> obradiPromjenu(Path path) throws IOException {
		String tekst = Files.readString(path).replaceAll("[,.!?:;]", "");
		String[] rijeci = tekst.split(" ");
		ArrayList<Integer> samoglasnici = new ArrayList<>();
		int samoglasnikA = 0;
		int samoglasnikE = 0;
		int samoglasnikI = 0;
		int samoglasnikO = 0;
		int samoglasnikU = 0;
		for (String rijec : rijeci) {
			for (char c : rijec.toCharArray()) {
				if (c == 'a' || c == 'A') {
					samoglasnikA++;
				} else if (c == 'e' || c == 'E') {
					samoglasnikE++;
				} else if (c == 'i' || c == 'I') {
					samoglasnikI++;
				} else if (c == 'o' || c == 'O') {
					samoglasnikO++;
				} else {
					samoglasnikU++;
				}
			}
		}
		samoglasnici.add(samoglasnikA);
		samoglasnici.add(samoglasnikE);
		samoglasnici.add(samoglasnikI);
		samoglasnici.add(samoglasnikO);
		samoglasnici.add(samoglasnikU);
		
		Main.ukupanBrojSamoglasnika += (samoglasnikA + samoglasnikE + samoglasnikI + samoglasnikO + samoglasnikU);
		
		return samoglasnici;
	}
	
	@Override
	public void run() {
		this.radi = true;
		List<Path> lastPaths;
		try {
			lastPaths = Files.walk(rootPath).collect(Collectors.toList());
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom rada FileWatchera na pocetku");
			return;
		}
		
		while (this.radi) {
			try {
				List<Path> paths = Files.walk(this.rootPath).collect(Collectors.toList());
				for (Path path : paths) {
					if (!lastPaths.contains(path)) {
						ArrayList<Integer> samoglasnici = obradiPromjenu(path);
						System.out.println("Broj samoglasnika (a, e, i, o, u) respektivno u fajlu " + path + ": " + samoglasnici);
					}
				}
				
				lastPaths = paths;
			} catch (IOException ex) {
				System.out.println("GRESKA prilikom rada FileWatchera");
				return;
			}
			
			try {
				sleep(500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}