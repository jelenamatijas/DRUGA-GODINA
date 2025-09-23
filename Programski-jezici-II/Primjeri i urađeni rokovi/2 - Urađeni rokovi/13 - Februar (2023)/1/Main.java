import java.util.*;
import java.nio.file.*;

public class Main {
	
	public static Object lock = new Object();
	public static int brojUredjajaKojiSuZavrsili = 0;
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("GRESKA format: java Main <putanja_do_foldera>");
			return;
		}
		
		Path path = Paths.get(args[0]);
		
		ArrayList<Uredjaj> uredjaji = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			uredjaji.add(new Skener(path));
			uredjaji.add(new Stampac(path));
			uredjaji.add(new Kopir(path));
		}
		
		ArrayList<KopiSkener> kopiSkeneri = new ArrayList<>();
		ArrayList<Kombo> komboi = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			kopiSkeneri.add(new KopiSkener(new Skener(path), new Kopir(path)));
			komboi.add(new Kombo(new Skener(path), new Stampac(path), new Kopir(path)));
		}
		
		for (Uredjaj uredjaj : uredjaji) {
			uredjaj.start();
		}
		
		for (KopiSkener kopiSkener : kopiSkeneri) {
			kopiSkener.skener.start();
			kopiSkener.kopir.start();
		}
		
		for (Kombo kombo : komboi) {
			kombo.skener.start();
			kombo.stampac.start();
			kombo.kopir.start();
		}
		
		String unos = "";
		Scanner scanner = new Scanner(System.in);
		while (!"STOP".equalsIgnoreCase(unos)) {
			if (brojUredjajaKojiSuZavrsili == 24) {
				break;
			}
			unos = scanner.nextLine();
		}
	}
}