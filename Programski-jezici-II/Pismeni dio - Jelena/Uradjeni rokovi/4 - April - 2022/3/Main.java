import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main{
	static public void main(String args[]){
		List<String> sadrzaj = new ArrayList<>();
		
		try{
			sadrzaj = Files.readAllLines(Paths.get("songs.txt"));
		}catch(SecurityException|IOException e){
			System.out.println("GRESKA pri citanju pjesama.");
		}
		
		List<Song> pjesme = new ArrayList<>();
		for(String s : sadrzaj){
			String []podaci = s.split("###");
			pjesme.add(new Song(podaci[0], podaci[1], Integer.parseInt(podaci[2]), Integer.parseInt(podaci[3])));
		}
		
		pjesme.forEach(l -> System.out.println(l));
		
		System.out.println("\n================= 1 =================");
		pjesme.stream().collect(Collectors.groupingBy((Song s) -> s.godina)).forEach((godina, list) -> {
			System.out.println(godina);
			list.forEach(l -> System.out.println(l));
			System.out.println();
		});
		
		System.out.println("\n================= 2 =================");
		pjesme.stream().filter((Song s) -> s.izvodjac.equals("Queen") || s.izvodjac.equals("Michael Jackson")).forEach(l -> {
			System.out.println(l);
		});
		
		System.out.println("\n================= 3 =================");
		pjesme.stream().filter((Song s) -> s.duzina/60.0 > 5.0).forEach(l -> {
			System.out.println(l);
		});
		
		System.out.println("\n================= 4 =================");
		pjesme.stream().filter((Song s) -> s.godina>=2010).forEach(l -> {
			System.out.println(l);
		});
		
		System.out.println("\n================= 5 =================");
		double p = pjesme.stream().filter((Song s) -> s.godina>=1980 && s.godina <1990).mapToDouble((Song s) -> s.duzina).average().getAsDouble();
		System.out.println("Projesk u sekundama: " + p);
	}
	
}