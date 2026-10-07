import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;
import java.io.*;

class Main{
	public static void main(String[] args){
		if(args.length != 1){
			System.out.println("GRESKA: format treba biti: java Main <PUTANJA_DO_FAJLA>");
			return;
		}
		
		String folder = args[0];
		Path root = Paths.get(folder);
		if(!Files.exists(root) || !Files.isDirectory(root)){
			System.out.println("GRESKA: Folder ne postoji ili je putanja pogresna.");
			return;
		}
		
		Map<String, String> fajlovi = new HashMap<>();
		
		try{
			Files.walk(root).forEach(filePath -> {
				try{
					if(Files.isRegularFile(filePath)){
						String sadrzaj = new String(Files.readAllBytes(filePath));
						if(fajlovi.containsValue(sadrzaj)){
							System.out.println("Fajl " + filePath + " je duplikat.");
							
								Files.delete(filePath);
							
						}else{
							fajlovi.put(filePath.toString(), sadrzaj);
						}
					}
				}catch(Exception e){
					System.out.println("GRESKA prilikom brisanja fajla.");
				}
				
			});
		}catch(IOException e){
			System.out.println("GRESKA pri prolasku kroz folder.");
		}
		
		System.out.println("Sadrzaj mape: ");
		for(String key : fajlovi.keySet()){
			System.out.println("<" +key + ">" + "    <" + fajlovi.get(key) + ">");
		}
	}
}