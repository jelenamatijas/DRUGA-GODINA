import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.nio.file.WatchKey;
import java.nio.file.WatchEvent;
import static java.nio.file.StandardWatchEventKinds.*;
import java.util.*;


public class Main {
	
	public static int ukupanBrojSamoglasnika = 0;
	
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("GRESKA! Format: java Main <broj_direktorijuma> <putanja_do_direktorijuma>");
			return;
		}
		
		int brojDirektorijuma = 0;
		try {
			brojDirektorijuma = Integer.parseInt(args[0]);
		} catch (NumberFormatException ex) {
			System.out.println("GRESKA! Broj direktorijuma mora biti int");
		}
		
		String putanjaDoDirektorijuma = args[1];
		Path rootDirektorijum = Paths.get(putanjaDoDirektorijuma);
		try {
			if (!Files.isDirectory(rootDirektorijum) || !Files.exists(rootDirektorijum)) {
				Files.createDirectory(Paths.get(putanjaDoDirektorijuma));
				System.out.println("Proslijedjeni direktorijum ne postoji ili nije folder pa je napravljen novi: " + rootDirektorijum);
			}
			
			for (int i = 1; i <= brojDirektorijuma; i++) {
				String novaPutanja = putanjaDoDirektorijuma + i;
				Path noviDirektorijum = rootDirektorijum.resolve(String.valueOf(i));
				Files.createDirectory(noviDirektorijum);
				System.out.println("Napravljen novi direktorijum: " + noviDirektorijum);
			}
		} catch (IOException ex) {
			System.out.println("GRESKA tokom pravljenja fajlova!");
		}
		
		try {
			WatchService watcher = FileSystems.getDefault().newWatchService();
		
			for (int i = 1; i <= brojDirektorijuma; i++) {
				Path direktorijum = rootDirektorijum.resolve(String.valueOf(i));
				direktorijum.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
			}
			
			System.out.println("Pracenje promjena u direktorijumima je pocelo...");
			
			while (true) {
				WatchKey key = watcher.take();
				
				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					
					@SuppressWarnings("unchecked")
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path imeFajla = ev.context();
					Path direktorijum = (Path) key.watchable();
					Path putanjaTxtFajla = direktorijum.resolve(imeFajla);
					
					if (kind == ENTRY_CREATE || kind == ENTRY_MODIFY) {
						if (imeFajla.toString().endsWith(".txt")) {
							ArrayList<Integer> samoglasnici = obradiTxtFajl(putanjaTxtFajla);
							
							System.out.println("Datoteka: " + putanjaTxtFajla);
							System.out.println("NAPOMENA: Broj samoglasnika je prikazan redoslijedom a, e, i, o, u, respektivno.");
							samoglasnici.stream().forEach(samoglasnik -> System.out.println("Broj samoglasnika: " + samoglasnik));
							ukupanBrojSamoglasnika += samoglasnici.stream().mapToInt(Integer::intValue).sum();
							
							System.out.println("Ukupan broj svih samoglasnika: " + ukupanBrojSamoglasnika);
						}
					}
				}
				
				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}
		} catch (IOException | InterruptedException ex) {
			System.out.println("GRESKA tokom WatchService");
			return;
		}
		
	}
	
	private static ArrayList<Integer> obradiTxtFajl(Path putanjaTxtFajla) {
		ArrayList<Integer> samoglasnici = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(putanjaTxtFajla.toString()))) {
			String linija;
			int samoglasnikA = 0;
			int samoglasnikE = 0;
			int samoglasnikI = 0;
			int samoglasnikO = 0;
			int samoglasnikU = 0;
			while ((linija = br.readLine()) != null) {
				for (char c : linija.toCharArray()) {
					if (c == 'a' || c == 'A') {
						samoglasnikA++;
					} else if (c == 'e' || c == 'E') {
						samoglasnikE++;
					} else if (c == 'i' || c == 'I') {
						samoglasnikI++;
					} else if (c == 'o' || c == 'O') {
						samoglasnikO++;
					} else if (c == 'u' || c == 'U') {
						samoglasnikU++;
					}
				}
			}
			samoglasnici.add(samoglasnikA);
			samoglasnici.add(samoglasnikE);
			samoglasnici.add(samoglasnikI);
			samoglasnici.add(samoglasnikO);
			samoglasnici.add(samoglasnikU);
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom citanja txt fajla: " + putanjaTxtFajla);
		}
		
		return samoglasnici;
	}
}