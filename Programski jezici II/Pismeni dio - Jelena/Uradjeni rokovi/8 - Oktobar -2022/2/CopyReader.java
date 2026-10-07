import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CopyReader extends Thread{
	
	public void run(){
		List<String> linije = new ArrayList<>();
		synchronized(Main.lock){
			
			try{
				linije =Files.readAllLines(Main.path);
			}catch(IOException e){
				System.out.println("Greska pri citanju fajla.");
			}
		}
		String sadrzaj = "";
		for(String s: linije){
			sadrzaj+=s;
		}
		
		System.out.println(sadrzaj);
		System.out.println("================================================================================");
		try{
			sleep(1000);
		}catch(InterruptedException e){}
	}

	
}