import java.util.*;
import java.io.*;

public class XMLParser {
	
	public HashMap<String, String> load(File file) {
		HashMap<String, String> hashMap = new HashMap<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			// preskacemo <...>
			br.readLine();
			
			String linija;
			
			while ((linija = br.readLine()).indexOf('/') != 1) {
				this.obradiLiniju(linija, hashMap);
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom citanja XML fajla");
		}
		
		return hashMap;
	}
	
	public void obradiLiniju(String linija, HashMap<String, String> hashMap) {
		String polje = linija.substring(linija.indexOf('<') + 1, linija.indexOf('>')).trim();
		String vrijednost = linija.substring(linija.indexOf('>') + 1, linija.lastIndexOf('<')).trim();
		hashMap.put(polje, vrijednost);
		//System.out.println("POLJE: " + polje + "\nVRIJEDNOST: " + vrijednost);
	}
}