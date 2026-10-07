import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.io.*;

public class Main {
	
	public static int ukupanBrojFajlova = 0;
	public static int ukupanBrojSkrivenihFajlova = 0;
	
	public static File txtFajl;
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("GRESKA format: java Main <putanja_do_root_direktorijuma>");
			return;
		}
		
		Path rootPutanja = Paths.get(args[0]);
		txtFajl = new File(rootPutanja.toString() + "/txtFajl.txt");
		
		try {
			List<Path> paths = Files.walk(rootPutanja).collect(Collectors.toList());
			for (Path path : paths) {
				if (!Files.isDirectory(path)) {
					if (Files.isHidden(path)) {
						ukupanBrojSkrivenihFajlova++;
						// System.out.println(path.toAbsolutePath().toString());
						upisiUTxtFajl(path.toAbsolutePath().toString());
					}
					ukupanBrojFajlova++;
				}
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom rada sa fajlovima");
			return;
		}
		System.out.println("Ukupan broj fajlova u root folderu: " + ukupanBrojFajlova);
		System.out.println("Ukupan broj SKRIVENIH fajlova u root folderu: " + ukupanBrojSkrivenihFajlova);
		System.out.println("Procenat skrivenih fajlova u root folderu: " + ((double) ukupanBrojSkrivenihFajlova / (double)ukupanBrojFajlova) + "%");
	}
	
	private static void upisiUTxtFajl(String apsolutnaPutanja) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(txtFajl, true));
		pw.println(apsolutnaPutanja);
	}
}