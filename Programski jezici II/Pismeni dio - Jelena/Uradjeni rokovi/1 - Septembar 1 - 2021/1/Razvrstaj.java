import java.util.*;
import java.io.File;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class Razvrstaj extends Thread{
	Razvrstaj(){}
	
	public void run(){
		System.out.println("Razvrstavanje je zapocelo.");
		synchronized(Main.lock){
			for(Posiljka p: Main.posiljke){
				if(p instanceof PismoInterface){
					Main.pisma.add(p);
				}else if(p instanceof RazglednicaInterface){
					Main.razglednice.add(p);
				}else{
					Main.vrijednosne.add(p);
				}
				
				File folder = new File("folder");
				try{
					if(!folder.exists()){
						Files.createDirectory(Paths.get("folder"));
					}
				}catch(SecurityException | IOException e){
					System.out.println("Greska pri provjeri postojanja foldera.");
				}
				
				try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(folder.getName() + File.separator + p.ID + ".txt"))){
					out.writeObject(p);
				}catch(IOException e){
					System.out.println("Greska pri serjalizaciji objekta : " + p);
				}
				
			}
			
			Main.razvrstano = true;
			Main.lock.notifyAll();
		}
		System.out.println("Razvrstavanje zavrseno.");
	}
}