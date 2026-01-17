import java.io.*;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class Main{
	
	public static void main(String []args){
		if(args.length != 4){
			System.out.println("GRESKA: format mora biti java Main -d <putanja_do_foldera> -l <duzina>");
			return;
		}
		
		String putanjaDoFoldera = "";
		int d = 0;
		Path root = null;
		if("-d".equals(args[0])){
			putanjaDoFoldera = args[1];
			root = Paths.get(putanjaDoFoldera);
		}else{
			System.out.println("GRESKA: format mora biti java Main -d <putanja_do_foldera> -l <duzina>");
			return;
		}
		
		if(!Files.isDirectory(root) || !Files.exists(root)){
			System.out.println("GRESKA: folder ne postoji ili je putanja pogresna.");
			return;
		}
		
		if("-l".equals(args[2])){
			d = Integer.parseInt(args[3]);
		}else{
			System.out.println("GRESKA: format mora biti java Main -d <putanja_do_foldera> -l <duzina>");
			return;
		}
		
		final int duzina = d;
		
		Map<String, Integer> RIJECI = new HashMap<>();
		Map<Path, Integer> FAJLOVI = new HashMap<>();
		
		System.out.println(root);
		
		try{
			Files.walk(root).forEach(file -> {
				if(Files.isRegularFile(file) && file.getFileName().toString().toLowerCase().endsWith(".txt")){
					try(BufferedReader bf = new BufferedReader(new FileReader(file.toString()))){
						String linija = "";
						Integer brRijeciUFajlu=0;
						while((linija = bf.readLine())!=null){
							String[] rijeci = linija.split(" ");
							for(String rijec : rijeci){
								String r = rijec.trim();
								if(r.length() == duzina){
									brRijeciUFajlu++;
									if(RIJECI.containsKey(r)){
										RIJECI.replace(r, RIJECI.get(r)+1);
									}else{
										RIJECI.put(r, 1);
									}
								}
							}
						}
						FAJLOVI.put(file, brRijeciUFajlu);
					}catch(IOException e){
						System.out.println("GRESKA prilikom citanja fajla: " + file);
					}
				}
				
			});
		}catch(IOException e){
			System.out.println("GRESKA prilikom prolaska kroz direktorijum.");
		}
		
		try(PrintWriter pw = new PrintWriter(new FileWriter("rijeci_duzine_" + duzina + ".txt"))){
			for(String key : RIJECI.keySet()){
				pw.println(key + " -> " + RIJECI.get(key));
			}
		}catch(IOException e){
			System.out.println("GRESKA prilikom upisivanja u fajl: " + "rijeci_duzine_" + duzina + ".txt");
		}
		
		for(Path p : FAJLOVI.keySet()){
			System.out.println(p.getFileName() + " -> " + p + " -> " + FAJLOVI.get(p));
		}
	}
}