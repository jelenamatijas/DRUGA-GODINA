import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		// Laptopi
		JSONParser jsonParser = new JSONParser();
		HashMap<String, String> hashMap = jsonParser.load(new File("obj.json"));
		Laptop jsonLaptop = new ObjectConverter<Laptop>().convert(hashMap, new Laptop());
		
		System.out.println("JSON Laptop: " + jsonLaptop);
		
		XMLParser xmlParser = new XMLParser();
		HashMap<String, String> hashMap2 = xmlParser.load(new File("obj.xml"));
		Laptop xmlLaptop = new ObjectConverter<Laptop>().convert(hashMap2, new Laptop());
		
		System.out.println("XML Laptop: " + xmlLaptop);
		
		// Vozila
		JSONParser jsonParser2 = new JSONParser();
		HashMap<String, String> hashMap3 = jsonParser2.load(new File("obj1.json"));
		Vozilo jsonVozilo = new ObjectConverter<Vozilo>().convert(hashMap3, new Vozilo());
		
		System.out.println("\n\nJSON Vozilo: " + jsonVozilo);
		
		XMLParser xmlParser2 = new XMLParser();
		HashMap<String, String> hashMap4 = xmlParser2.load(new File("obj1.xml"));
		Vozilo xmlVozilo = new ObjectConverter<Vozilo>().convert(hashMap4, new Vozilo());
		
		System.out.println("XML Vozilo: " + xmlVozilo);
	}
}