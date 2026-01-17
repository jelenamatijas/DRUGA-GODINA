import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.*;
import java.util.*;

class Main{
	static long najveciFajl = 0;
	static Path najveciFajlPutanja;
	static long ukupno = 0;
	static List<String> naziviFajlova = new ArrayList<>();
	static Map<Path, File> fajlovi = new HashMap<>();
	
	static public void main(String args[]){
		if(args.length!=1){
			System.out.println("Greska pri pokretanju.");
			return;
		}
		
		Path path = Paths.get(args[0]);
		if(Files.isRegularFile(path)){
			List<String> redovi = new ArrayList<>();
			try{
				redovi = Files.readAllLines(path);
			}catch(IOException e){
				System.out.println("Greska pri citanju fajla.");
			}
			Collections.sort(redovi);
			//System.out.println(redovi);
			File folder = new File("folder");
			folder.mkdir();
			redovi.stream().collect(Collectors.groupingBy((String p) -> p.charAt(0))).forEach((slovo, lista) -> {
				try(PrintWriter pw = new PrintWriter(new FileWriter("folder/sortirani" + Character.toUpperCase(slovo) + ".txt"))){
					for(String s : lista){
						pw.println(s);
					}
				}catch(IOException e){
					System.out.println("Greska pri upisu u fajl.");
				}
			});
		}else if(Files.isDirectory(path)){			
			try{
				Files.walk(path).forEach( p -> {
					if(Files.isRegularFile(p)){
						try{
							ukupno += Files.size(p);
							if(najveciFajl<Files.size(p)){
								najveciFajl = Files.size(p);
								najveciFajlPutanja = p;
							}
						}catch(IOException e){
							System.out.println("Greska pri provjeri velicine fajla.");
						}catch(Exception e){
							System.out.println("Greska pri provjeri velicine fajla.");
						}
						naziviFajlova.add(p.getFileName().toString());
						fajlovi.put(p, new File(p.toString()));
					}
				});
			}catch(IOException e){
				System.out.println("Greska pri prolasku kroz folder.");
			}
			
			System.out.println("Ukupna velicina fajlova: " + ukupno);
			System.out.println("Najveci fajl: " + najveciFajl + " -> " + najveciFajlPutanja.toString());
			Collections.sort(naziviFajlova);
			System.out.println("Nazivi fajlova: ");
			naziviFajlova.forEach(s -> System.out.println(s));
		}
	}
	
}