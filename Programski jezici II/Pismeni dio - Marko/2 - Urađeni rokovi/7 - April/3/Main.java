import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.*;


public class Main {
	
	public static int brojSkrivenihFajlova = 0;
	public static int ukupanBrojFajlova = 0;
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("GRESKA: format mora biti: java main <putanja_do_root_direktorijuma>");
			return;
		}
		
		String rootDirektorijum = args[0];
		Path putanjaRootDirektorijuma = Paths.get(rootDirektorijum);
		if (!Files.exists(putanjaRootDirektorijuma) || !Files.isDirectory(putanjaRootDirektorijuma)) {
			System.out.println("GRESKA: Direktorijum ne postoji ili putanja ne vodi do direktorijuma!");
			return;
		}
		
		
		File txtFajl = new File(rootDirektorijum + "/txtFajl.txt");
		try {
			if (!txtFajl.exists()) {
				if (txtFajl.createNewFile()) {
					System.out.println(txtFajl + " uspjesno kreiran");
				}
			} else {
				System.out.println("Fajl vec postoji");
			}
			obradiDirektorijum(putanjaRootDirektorijuma);
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom kreiranja txt fajla: " + txtFajl);
			return;
		}
		
		upisiStatistiku(putanjaRootDirektorijuma.toString());
	}
	
	public static void obradiDirektorijum(Path putanjaRootDirektorijuma) throws IOException {
		Files.walk(putanjaRootDirektorijuma).forEach(path -> {
			try {
				if (Files.isHidden(path)) {
					brojSkrivenihFajlova++;
					upisiUTxtFajl(path, putanjaRootDirektorijuma.toString());
				}
				if (Files.isRegularFile(path)) {
					ukupanBrojFajlova++;
				}
			} catch (IOException ex) {
				System.out.println("GRESKA prilikom obrade direktorijuma");
			}
		});
	}
	
	private static void upisiUTxtFajl(Path path, String rootDirektorijum) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(rootDirektorijum + "/txtFajl.txt", true))) {
			pw.println(path);
		} catch (IOException ex) {
			System.out.println("GRESKA prikikom pisanja fajla: " + path);
		}
	}
	
	private static void upisiStatistiku(String rootDirektorijum) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(rootDirektorijum + "/txtFajl.txt", true))) {
			pw.println("Ukupan broj fajlova: " + ukupanBrojFajlova);
			pw.println("Broj skrivenih fajlova: " + brojSkrivenihFajlova);
			pw.printf("Procenat skrivenih fajlova: %.2f%s", ((double) brojSkrivenihFajlova / (double) ukupanBrojFajlova), "%");
		} catch (IOException ex) {
			System.out.println("GRESKA prikikom pisanja fajla: " + rootDirektorijum);
		}
	}
}
