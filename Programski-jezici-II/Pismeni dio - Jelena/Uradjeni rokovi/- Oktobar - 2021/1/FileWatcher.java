import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileWatcher extends Thread{
	String univerzitetPath;
	boolean radi = true;
	
	FileWatcher(String path){
		univerzitetPath = path;
	}
	
	public void run(){
		while(radi){
			
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				System.out.println("Greska pri pauziranju fileWatcher-a foldera: " + univerzitetPath);
			}
			
			if(!radi){
				return;
			}
			
			Path folderPath = Paths.get("drzava"+File.separator+univerzitetPath);
			try{
				if(!folderPath.toFile().exists()){
					try{
						Files.createDirectory(folderPath);
						synchronized(Main.lock){
							Main.drzava.univerziteti.put(univerzitetPath, new Univerzitet(univerzitetPath));
						}
					}catch(IOException e){
						System.out.println("Greska pri kreiranju foldera: "+univerzitetPath);
					}
				}
			}catch(SecurityException e){
				System.out.println("Greska pri kreiranju foldera univerziteta: " + univerzitetPath);
				return;
			}
			
			try{
				Files.walk(folderPath).forEach(path -> {
					synchronized(Main.lock1){
						if(Main.pauza){
							try{
								Main.lock1.wait();
							}catch(InterruptedException e){
								System.out.println("FileWatcher " + univerzitetPath + " prekinut u cekanju.");
							}
						}
					}
					
					try{
						Thread.sleep(200);
					}catch(InterruptedException e){
						System.out.println("Greska pri pauziranju fileWatcher-a foldera: " + univerzitetPath + " fajl: " + path);
					}
					
					if(!radi){
						return;
					}
					
					if(Files.isRegularFile(path)){
					
						String nazivFakulteta = path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf("."));
						Fakultet fakultet = new Fakultet(nazivFakulteta);
						try(BufferedReader bf = new BufferedReader(new FileReader(path.toString()))){
							String s = null;
							while((s = bf.readLine())  != null){
								String []podaci = s.trim().split(" ");
								if(podaci.length == 2){
									fakultet.studenti.add(new Student(podaci[0], podaci[1], univerzitetPath, nazivFakulteta));
								}else{
									throw new InvalidFileException("Fajl nije korektan: " + path);
								}
							}
						}catch(InvalidFileException e){
							System.out.println(e);
						}catch(IOException e){
							System.out.println("Greska pri citanju fajla: " + path);
						}
						
						synchronized(Main.lock){
							if(Main.drzava.univerziteti.get(univerzitetPath).fakulteti.get(fakultet.naziv) == null){
								Main.drzava.univerziteti.get(univerzitetPath).fakulteti.put(fakultet.naziv, fakultet);
							}else{
								Main.drzava.univerziteti.get(univerzitetPath).fakulteti.replace(fakultet.naziv, fakultet);
							}
						}
					}
				});
			}catch(IOException e){
				System.out.println("Greska pri obilasku foldera: " + univerzitetPath);
			}
		}
	}
}