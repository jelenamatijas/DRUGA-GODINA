import java.io.*;
import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main{
	static String rijec;
	static String ekstenzija;
	static public void main(String args[]){
		if(args.length != 6){
			System.out.println("Pogresan format.");
			return;
		}
		
		Path path = null;
		rijec = null;
		ekstenzija = null;
		
		if(args[0].equals("-s")){
			path = Paths.get(args[1]);
		}else if(args[2].equals("-s")){
			path = Paths.get(args[3]);
		}else{
			path = Paths.get(args[5]);
		}
		
		if(args[0].equals("-w")){
			rijec = args[1].toLowerCase();
		}else if(args[2].equals("-w")){
			rijec = args[3].toLowerCase();
		}else{
			rijec = args[5].toLowerCase();
		}
		
		if(args[0].equals("-e")){
			ekstenzija = args[1].toLowerCase();
		}else if(args[2].equals("-e")){
			ekstenzija = args[3].toLowerCase();
		}else{
			ekstenzija = args[5].toLowerCase();
		}
		
		if(path==null || rijec==null || ekstenzija==null){
			return;
		}
		
		Map<String, Integer> fajlovi = new HashMap<>();
		
		try{
			Files.walk(path).forEach(file -> {
				if(Files.isRegularFile(file) && file.toString().endsWith(ekstenzija)){
					try{
						List<String> linije = Files.readAllLines(file);
						String sadrzaj = "";
						for(String s:linije){
							sadrzaj+=s.toLowerCase();
						}
						
						int x = 0;
						while(sadrzaj.contains(rijec)){
							x++;
							sadrzaj = sadrzaj.substring(sadrzaj.indexOf(rijec)+ rijec.length(), sadrzaj.length());
						}
						
						fajlovi.put(file.toString(), x);
					}catch(IOException e){
						System.out.println("Greska pri citanju fajla " + file);
					}
				}
			});
		}catch(IOException e){
						System.out.println("Greska pri prolasku kroz folder");
					}
		
		for(String s: fajlovi.keySet()){
			System.out.println(s + ": " + fajlovi.get(s));
		}
	}
}