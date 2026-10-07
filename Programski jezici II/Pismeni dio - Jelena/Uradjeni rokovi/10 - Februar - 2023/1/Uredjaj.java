import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.StandardCopyOption;

class Uredjaj extends Thread{
	
	static Random random = new Random();
	
	static enum EKSTENZIJE{
		PDF, TXT, DOCX, DOC, MP3;
	}
	
	volatile int brojFajlova;
	volatile int brojFoldera;
	volatile long pocetak;
	String naziv, proizvodjac;
	long brojRadnihSekundi;
	static int id = 1;
	
	Uredjaj(String tip){
		naziv = tip + "_uredjaj_" + id;
		proizvodjac = "Proizvodjac_" + ((id%2 == 0) ? "1" : "2");
		brojRadnihSekundi = 0;
		id++;
	}
	
	public void run(){
		Path path  = Paths.get(Main.putanja);
		EKSTENZIJE values[] = EKSTENZIJE.values();
		if(this instanceof SkenerInterface){
			synchronized(Main.lock){
				try{
					brojFoldera = 0;
					brojFajlova = 0;
					pocetak = System.currentTimeMillis();
					String ekstenzija = "." + values[random.nextInt(0,values.length)].name().toLowerCase();
					List<String> fajlovi = new ArrayList<>();
				
					Files.walk(path).forEach(p -> {
						if((brojRadnihSekundi = (System.currentTimeMillis() - pocetak)/1000) <2){
							if(Files.isRegularFile(p)){
								brojFajlova++;
								if(p.getFileName().toString().toLowerCase().endsWith(ekstenzija)){
									fajlovi.add(p.getFileName().toString());
								}
							}else if(Files.isDirectory(p)){
								brojFoldera++;
							}
						}
					});
					System.out.println("Vrijeme rada uredjaja " + this + " je isteklo.\n\tBroj pronadjenih foldera: " + brojFoldera 
						+ ".\n\tBroj pronadjenih fajlova: " + brojFajlova + ". Fajlovi sa pronadjenom ekstenzijom " + ekstenzija + ":\n\t\t" + fajlovi);
					
				}catch(IOException e){
					System.out.println("Greska pri skeniranju uredjajem " + this + ".");
				}
			}
		}
		if(this instanceof StampacInterface){
			synchronized(Main.lock1){
				try{
					pocetak = System.currentTimeMillis();
					Map<String, List<String>> fajlovi = new HashMap<>();
				
					Files.walk(path).forEach(p -> {
						if((brojRadnihSekundi = (System.currentTimeMillis() - pocetak)/1000) <2){
							if(Files.isRegularFile(p)){
								String name = p.getFileName().toString().toLowerCase();
								String ext = "." + values[random.nextInt(values.length)].name().toLowerCase();
								if(name.endsWith(ext)){
									try{
										if(Files.size(p) < 2048){
											List<String> sadrzaj = Files.readAllLines(p);
											fajlovi.put(name, sadrzaj);
										}
									}catch(IOException e){
										System.out.println("Greska pri stampanju s uredjajem " + this + ". Citanje pogresno.");
									}
								}
							}
						}
					});
					fajlovi.forEach((key, val) -> {
						System.out.println(this + " -> " + key + " -> " + val );
						
					});
					
				}catch(IOException e){
					System.out.println("Greska pri stampanju s uredjajem " + this + ".");
				}
			}
		}
		
		if(this instanceof KopirInterface){
			synchronized(Main.lock2){
				try{
					pocetak = System.currentTimeMillis();
								
					Files.walk(path).forEach(p -> {
						if((brojRadnihSekundi = (System.currentTimeMillis() - pocetak)/1000) <2){
							if(Files.isRegularFile(p) && !p.getFileName().toString().startsWith("COPY - ")){	
								try{
									String name = "COPY - " + p.getFileName().toString();
									Files.copy(p, p.resolveSibling(name), StandardCopyOption.REPLACE_EXISTING);
									System.out.println(this + " -> Fajl kopiran na putanji: " + p.resolveSibling(name));
								}catch(IOException e){
									System.out.println("Greska pri kopiranju s uredjajem " + this + ". Kopiranje pogresno.");
								}
								
							}
						}
					});
					
				}catch(IOException e){
					System.out.println("Greska pri kopiranju s uredjajem " + this + ".");
				}
			}
		}
	}
	
	@Override
	public String toString(){
		return naziv.toUpperCase();
	}
	
}