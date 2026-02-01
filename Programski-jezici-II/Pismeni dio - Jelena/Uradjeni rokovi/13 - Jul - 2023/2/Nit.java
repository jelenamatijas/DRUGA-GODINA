import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Nit extends Thread{
	static int id = 1;
	int ID;
	
	Nit(){
		ID = id++;
	}
	static volatile boolean pronadjen = false;
	
	public void run(){
		while(!pronadjen){
			synchronized(Main.lock){
				int vrijednost = 0;

				try {
					if (Files.notExists(Main.path)) {
						Files.createFile(Main.path);
					} else {
						String content = Files.readString(Main.path).trim();
						if (!content.isEmpty()) {
							vrijednost = Integer.parseInt(content);
						}
					}
				} catch (IOException | NumberFormatException e) {
					System.err.println("GRESKA pri radu sa fajlom: " + e.getMessage());
				}
				
				if(!pronadjen && vrijednost == Main.broj){
					pronadjen = true;
					Main.lock.notifyAll();
					System.out.println("Broj unutar fajla je isti kao zadani broj. Pobijedila je nit " + ID);
					break;
				}else{
					if(Main.rand.nextInt(0, 101) <=30){
						vrijednost--;
					}else{
						vrijednost++;
					}
					try(PrintWriter pw = new PrintWriter(new FileWriter(Main.path.getFileName().toString(), false))){
						pw.print(vrijednost);
					}catch(IOException e){
						System.out.println("GRESKA pri upisu u fajl :" + e);
					}
				}
			}
			try{
				sleep(100);
			}catch(InterruptedException e){
				System.out.println("GRESKA pri pauziranju niti.");
			}
		}
	}
}