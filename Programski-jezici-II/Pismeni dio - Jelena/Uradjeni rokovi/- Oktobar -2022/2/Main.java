import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class Main{
	static Object lock  = new Object();
	static Path path = null;
	
	static public void main(String []args){
		path = Paths.get("fajl.txt");
		for (int i = 0; i < 10; i++) {
			CopyMaker cm = new CopyMaker();
			CopyReader cr = new CopyReader();

			cm.start();
			try { cm.join(); } catch (InterruptedException e) {}

			cr.start();
			try { cr.join(); } catch (InterruptedException e) {}
		}
		
		List<String> linije = new ArrayList<>();
		try{
			linije =Files.readAllLines(Paths.get("rezultatiKopiranja.txt"));
		}catch(IOException e){
			System.out.println("Greska pri citanju fajla.");
		}
		for(String s: linije){
			System.out.println(s);
		}
		
		
		
	}
}