import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;

class Main{
	static List<String> metoda1(Path path){
		List<String> names = new ArrayList<>();
		try{
			Files.walk(path).forEach(p -> {
				if(p.toFile().isDirectory()){
					names.add(p.toString());
				}else{
					names.add(p.getFileName().toString());
				}
			});
		}catch(Exception e){
			System.out.println("Greska u metodi 1.");
		}
		
		return names;
	}
	
	static List<String> metoda2(Path path){
		List<String> names = new ArrayList<>();
		try{
			File[] files = path.toFile().listFiles();
			
			for(int i=0;i<files.length;i++){
				Path p = Paths.get(files[i].getAbsolutePath());
				if(p.toFile().isDirectory()){
					names.add(p.toString());
					names.addAll(metoda2(p));
				}else{
					names.add(p.getFileName().toString());
				}
			}
		}catch(Exception e){
			System.out.println("Greska u metodi 2.");
		}
		
		return names;
	}
	
	static void obradi(List<String> lista){
		try(PrintWriter pw = new PrintWriter(new FileWriter(new File("rezultati.txt")))){
			for(String s: lista){
				pw.println(s);
			}
		}catch(IOException e){
			System.out.println("Greska pri upisu u fajl.");
		}
		
		System.out.println("\n******************** 1 ********************");
		lista.stream().collect(Collectors.groupingBy((String s) -> Paths.get(s).isAbsolute())).forEach((bool, list) -> {
			if(bool){
				System.out.println("Direktorijumi:");
			}else{
				System.out.println("Fajlovi:");
			}
			
			list.stream().sorted().forEach(System.out::println);
			System.out.println();
		});
		
		System.out.println("\n******************** 2 ********************");
		lista.stream().filter((String s) -> {
			if(Paths.get(s).isAbsolute()){
				if(check(s) > 4){
					return true;
				}
				
			}
			return false;
		}).toList().forEach(l -> {
			System.out.println(l);
		});
		
		System.out.println("\n******************** 3 ********************");
		lista.stream().filter((String s) -> {
			if(!Paths.get(s).isAbsolute()){
				return true;
			}
			return false;
		}).collect(Collectors.groupingBy((String s) -> getExtension(s))).forEach((bool, list) -> {
			System.out.println(bool);
			
			list.stream().sorted().forEach(System.out::println);
			System.out.println();
		});
		
		System.out.println("\n******************** 4 ********************");
		long number = lista.stream().filter((String s) -> !Paths.get(s).isAbsolute() && s.toLowerCase().startsWith("a")).count();
		System.out.println(number);
		
		System.out.println("\n******************** 5 ********************");
		Map<String, Long> fajlovi = lista.stream().filter((String s) -> !Paths.get(s).isAbsolute()).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		for(String s : fajlovi.keySet()){
			System.out.println(s + ": " + fajlovi.get(s));
			
		}
	
	}
	
	static String getExtension(String path) {
		Path p = Paths.get(path).getFileName();
		if (p == null) return "";
		String name = p.toString();

		int dot = name.lastIndexOf('.');
		if (dot <= 0) return "";
		return name.substring(dot + 1);
	}

	
	static int check(String s){
		int i = 0;
		while(s.contains(File.separator)){
			i++;
			s = s.substring(s.indexOf(File.separator)+1, s.length());
		}
		return i;
	}
	
	static public void main(String args[]){
		if(args.length!=1){
			System.out.println("GRESKA u formatu.");
			return;
		}
		
		Path path = Paths.get(args[0]);
		long p1 = System.currentTimeMillis();
		List<String> l1 = metoda1(path);
		long k1 = System.currentTimeMillis();
		
		long p2 = System.currentTimeMillis();
		List<String> l2 = metoda2(path);
		long k2 = System.currentTimeMillis();
		
		if(p1 - p2 < 0){
			System.out.println("Brza je metoda 2 (File).");
			obradi(l2);
			
		}else{
			System.out.println("Brza je metoda 1 (Files).");
			obradi(l1);
		}
		
	}
}