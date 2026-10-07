import java.io.*;
import java.util.*;

class JSONParser{
	public static HashMap<String, String> load(File f){
		HashMap<String, String> hashMap = new HashMap<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			br.readLine();
			
			String linija;
			while(!(linija = br.readLine()).equals("}")){
				String []par = linija.split(":");
				String key = par[0].replaceAll("\"", "").trim();
				String value = par[1].replaceAll("[\",]", "").trim();
				hashMap.put(key, value);
			}
		}catch(IOException e){
			System.out.println("Greska pri pretvaranju iz JSON formata.");
		}
		
		return hashMap;
	}
}