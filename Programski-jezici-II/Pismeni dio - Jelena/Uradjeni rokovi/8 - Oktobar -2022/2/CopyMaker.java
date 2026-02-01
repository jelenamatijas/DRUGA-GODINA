import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class CopyMaker extends Thread{
	
	public void run(){
		int brojSlovaO = 0;
		int brojBrojeva= 0;
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
			
			String []brojevi = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
			for (int i = 0; i < sadrzaj.length(); i++) {
				char c = sadrzaj.charAt(i);

				if (c == 'O' || c == 'o') {
					brojSlovaO++;
				} else if (c >= '1' && c <= '9') {
					brojBrojeva++;
				}
			}

			
			try{
				sadrzaj = sadrzaj.replaceAll("o", "0");
				sadrzaj = sadrzaj.replaceAll("O","0");
				
				for(int i=0;i<brojevi.length;i++){
					sadrzaj = sadrzaj.replaceAll(brojevi[i], "");
				}
			}catch(Exception e){
				System.out.println("Greska pri prepravljanju sadrzaja.");
			}
		synchronized(Main.lock){
			try(PrintWriter pw = new PrintWriter(new FileWriter(Main.path.toFile(), true))){
				pw.println(sadrzaj);
			}catch(IOException e){
				System.out.println("Greska pri upisu u fajl.");
			}
		}
		try(PrintWriter pw = new PrintWriter(new FileWriter("rezultatiKopiranja.txt", true))){
			pw.println("Broj slova o: " + brojSlovaO + " Broj brojeva: " + brojBrojeva);
		}catch(IOException e){
			System.out.println("Greska pri upisu u fajl rezultatiKopiranja.txt.");
		}
		
		try{
			sleep(1000);
		}catch(InterruptedException e){}
	
	}
	
}