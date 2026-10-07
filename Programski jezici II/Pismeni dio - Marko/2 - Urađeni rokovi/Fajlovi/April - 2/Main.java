import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Main {
	
	public static int ukupanBrojSamoglasnika = 0;
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("GRESKA format: java Main <broj_direktorijuma> <putanja_do_root_direktorijuma>");
			return;
		}
		
		int brojDirektorijuma = Integer.parseInt(args[0]);
		Path rootPutanja = Paths.get(args[1]);
		
		if (Files.notExists(rootPutanja)) {
			System.out.println("GRESKA root direktorijum sa datom putanjom ne postoji!");
			return;
		}
		
		try {
			for (int i = 0; i < brojDirektorijuma; i++) {
				String tempFolderPutanja = rootPutanja.toString() + "/podfolder" + (i + 1);
				Files.createDirectory(Paths.get(tempFolderPutanja));
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom pravljanje podfoldera");
			return;
		}
		
		FileWatcher fileWatcher = new FileWatcher(rootPutanja);
		fileWatcher.start();
		
		Scanner scanner = new Scanner(System.in);
		String unos = "";
		while (!unos.equalsIgnoreCase("kraj")) {
			unos = scanner.nextLine();
		}
		fileWatcher.radi = false;
	}
		
}