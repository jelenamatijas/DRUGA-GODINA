package pismeni.R_2020_01_30.Z01;

import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Simulacija{

	static Object lock = new Object();
	static HashMap<String, Long> poredak = new HashMap<>();
	static ArrayList<Vozilo> vozila = new ArrayList<>();
	static ObicnoPolje[] staza;
	static String STAZA_PATH = "./staza.csv";
	static String VOZILA_PATH = "./vozila.csv";

	public static void main(String[] args){
		try{
			// staza
			List<String> polja = Files.readAllLines(Path.of(STAZA_PATH));
			staza = new ObicnoPolje[polja.size()];
			int i = 0;
			for(String polje : polja){
				if(polje.startsWith("OBICNO")){
					staza[i++] = new ObicnoPolje();
				}else if(polje.startsWith("KLIZAVO")){
					String e = polje.split(";")[1];
					String procenat = e.substring(0, e.lastIndexOf('%'));
					staza[i++] = new KlizavoPolje(Integer.parseInt(procenat));
				}else if(polje.startsWith("NERAVNO")){
					staza[i++] = new NeravnoPolje();
				}
			}

			// vozila
			List<String> vozilaStrings = Files.readAllLines(Path.of(VOZILA_PATH));
			vozilaStrings.forEach(System.out::println);
			Map<String, List<String>> mapa = vozilaStrings.stream().collect(Collectors.groupingBy(v -> (v.split(";")[1])));
			for(Map.Entry<String, List<String>> e : mapa.entrySet()){
				List<String> value = e.getValue();
				String s = value.toString();
				if(s.contains("AUTOMOBIL")){
					vozila.add(new AutomobilBuilder().build(value));
				}else if(s.contains("AUTOBUS")){
					vozila.add(new AutobusBuilder().build(value));
				}else if(s.contains("KAMION")){
					vozila.add(new KamionBuilder().build(value));
				}
			}
			vozila.forEach(System.out::println);
			vozila.forEach(v -> v.start());
			vozila.forEach(v -> {
				try{
					v.join();
				}catch(Exception e){
					e.printStackTrace();
				}
			});
			System.out.println("======= POREDAK ==========");
			poredak.entrySet().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}