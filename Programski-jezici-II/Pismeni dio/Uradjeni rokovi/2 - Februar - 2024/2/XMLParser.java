import java.io.*;
import java.util.*;

class XMLParser{
	public static HashMap<String, String> load(File f){
		HashMap<String, String> hashMap = new HashMap<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			br.readLine();
			
			String linija;
			while((linija = br.readLine()).indexOf('/') != 1){
				String key = linija.substring(linija.indexOf('<')+1, linija.indexOf('>')).trim();
				String value = linija.substring(linija.indexOf('>')+1, linija.lastIndexOf('<')).trim();
				hashMap.put(key, value);
			}
		}catch(IOException e){
			System.out.println("Greska pri pretvaranju iz XML formata.");
		}
		
		return hashMap;
	}
}