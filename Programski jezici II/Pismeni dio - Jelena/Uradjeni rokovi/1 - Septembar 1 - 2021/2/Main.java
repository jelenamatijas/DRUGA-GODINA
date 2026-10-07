import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main{
	static String sekvenca;
	static public void main(String args[]){
		if(args.length !=1){
			System.out.println("Pogresan format.");
			return;
		}
		
		Path path = Paths.get(args[0]);
		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Unesi sekvencu: ");
		while(true){
			if(scanner.hasNext()){
				sekvenca = scanner.next().trim();
				scanner.close();
				break;
			}
		}
		
		sekvenca = sekvenca.toLowerCase();
		
		try{
			Files.walk(path).forEach(file -> {
				try{
					if(Files.isRegularFile(file)){
						String fileName = file.getFileName().toString().toLowerCase();
						fileName = fileName.substring(0, fileName.lastIndexOf("."));
						List<String> sadrzaj = new ArrayList<>();
						try{
							sadrzaj = Files.readAllLines(file);
						}catch(IOException | SecurityException e){
							System.out.println("Greska pri citanju sadrzaja fajla." + e);
						}
						
						int i=1;
						for(String s: sadrzaj){
							s =s.toLowerCase();
							if(s.contains(fileName)){
								System.out.println(file + " -> " + fileName);
							}
							if(s.contains(sekvenca)){
								String indeks = "";
								int start = 0;
								while((start = s.indexOf(sekvenca, start))!=-1){
									indeks += start + " ";
									start += sekvenca.length();
								}
								System.out.println(file + " -> " + sekvenca + " red: " + i + ", indeks: " + indeks);
							}
							i++;
							
						}
					}
				}catch(SecurityException e){
					System.out.println("Greska pri provjeri fajla.");
				}
			});
		}catch(IOException e){
			System.out.println("Greska pri prolasku kroz direktorijum.");
		}
		
	}
}