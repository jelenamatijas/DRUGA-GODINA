import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class Main{
	static public void main(String args[]){
		if(args.length!=4){
			System.out.println("GRESKA pri pokretanju. Format: java Main -d putanja_do_foldera -w rijec");
			return;
		}
		if(!args[0].equals("-d") || !args[2].equals("-w")){
			System.out.println("GRESKA pri pokretanju. Format: java Main -d putanja_do_foldera -w rijec");
			return;
		}
		
		Path path = null;
		try{
			path = Paths.get(args[1]);
		}catch(Exception e){
			System.out.println("Netacna putanja.");
			return;
		}
		String rijec = args[3].toLowerCase();
		
		Map<Integer, Path> podaci = new HashMap<>();
		List<Integer> ponavljanja = new ArrayList<>();
		
		try{
			Files.walk(path).forEach(p -> {
				if(Files.isRegularFile(p)){
					String sadrzaj = "";
					try{
						sadrzaj = Files.readString(p).toLowerCase();
					}catch(IOException e){
						System.out.println("Greska pri citanju iz fajla: " + p);
					}
					if(sadrzaj.contains(rijec)){
						int brojPonavljanja = 0;
						while(sadrzaj.indexOf(rijec)!=-1){
							sadrzaj = sadrzaj.substring(sadrzaj.indexOf(rijec)+1, sadrzaj.length());
							brojPonavljanja++;
						}
						podaci.put(brojPonavljanja, p);
						ponavljanja.add(brojPonavljanja);
					}
				}
			});
		}catch(Exception e){
			System.out.println("Greska pri prolasku kroz direktorijum.");
		}
		
		if(ponavljanja.isEmpty()){
			System.out.println("Nema trazene rijeci u fajlovima unutar navedenog foldera.");
		}else{
			System.out.println("Pronadjeni su iduci fajlovi:");
			Collections.sort(ponavljanja);
			for(Integer i : ponavljanja){
				System.out.println(podaci.get(i) + " = " + i);
			}
		}
		
	}
}