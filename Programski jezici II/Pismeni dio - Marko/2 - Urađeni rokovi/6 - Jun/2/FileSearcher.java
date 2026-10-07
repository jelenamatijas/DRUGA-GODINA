import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class FileSearcher {
	
	public String putanja;
	public int duzinaRijeci;
	// Za dati fajl nam kaze broj rijeci date duzine
	public Map<File, Integer> mapaDuzina = new HashMap<>();
	public int ukupanBrojRijeci = 0;
	
	public FileSearcher(String putanja, int duzinaRijeci) {
		this.putanja = putanja;
		this.duzinaRijeci = duzinaRijeci;
	}
	
	public void pretraziFajlove() {
		try {
			Files.walk(Paths.get(this.putanja)).filter(path -> path.toString().endsWith(".txt")).forEach(path -> {
				this.obradiFajl(path.toFile());
			});
		} catch (IOException ex) {
			System.out.println("Greska prilikom pretrazivanja fajlova!");
			return;
		}
	}
	
	private void obradiFajl(File txtFile) {
		int brojac = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
			String linija;
			while ((linija = br.readLine()) != null) {
				brojac += this.obradiLiniju(linija);
			}
		} catch (IOException ex) {
			System.out.println("Greska prilikom obrade fajla " + txtFile.toString());
			return;
		}
		if (brojac > 0) {
			this.mapaDuzina.put(txtFile, brojac);
			this.ukupanBrojRijeci += brojac;
		}
	}
	
	private int obradiLiniju(String linija) {
		int brojac = 0;
		
		String linijaRijeci = linija.replaceAll("[.,!?]", "");
		String[] rijeci = linijaRijeci.split(" ");
		
		for (String rijec : rijeci) {
			if (rijec.length() == this.duzinaRijeci) {
				brojac++;
			}
		}
		
		return brojac;
	}
	
	public void zapisiRezultateUFajl() {
		try (PrintWriter pw = new PrintWriter("rijeci_duzine_" + this.duzinaRijeci + ".txt")) {
			for (Map.Entry<File, Integer> entry : this.mapaDuzina.entrySet()) {
				BufferedReader br = new BufferedReader(new FileReader(entry.getKey()));
				String linija;
				while ((linija = br.readLine()) != null) {
					ArrayList<String> rijeci = nadjiRijeci(linija);
					for (String rijec : rijeci) {
						pw.println(rijec);
					}
				}
			}
		} catch (IOException ex) {
			System.out.println("Greska prilikom zapisivanja rezultata u fajl!");
			return;
		}
	}
	
	private ArrayList<String> nadjiRijeci(String linija) {
		String linijaRijeci = linija.replaceAll("[.,!?]", "");
		String[] rijeci = linijaRijeci.split(" ");
		
		ArrayList<String> trazeneRijeci = new ArrayList<>();
		
		for (String rijec : rijeci) {
			if (rijec.length() == this.duzinaRijeci) {
				trazeneRijeci.add(rijec);
			}
		}
		
		return trazeneRijeci;
	}
	
	public void ispisiRezultate() {
		this.mapaDuzina.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).forEach(entry -> System.out.println(entry.getKey().getAbsolutePath() + " = " + entry.getValue()));
		
		System.out.println("Ukupan broj rijeci duzine " + this.duzinaRijeci + ": " + this.ukupanBrojRijeci);
	}
}