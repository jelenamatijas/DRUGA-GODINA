import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class Main{
	
	static int ukupno = 0;
	static int pronadjeno = 0;
	static Object lock  = new Object();
	static String ekstenzija = "";
	
	public static void main(String []args){
		if(args.length != 1){
			System.out.println("GRESKA: format java Main <ekstenzija>");
			return;
		}
		
		ekstenzija = args[0];
		List<String> putanje = null	;
		try{
			putanje = Files.readAllLines(Paths.get("putanje.txt"));
		}catch(IOException e){
			System.out.println("GRESKA pri otvaranju fajla putanje.txt");
		}
		List<Obrada> obrade = new ArrayList<>();
		for(String s: putanje){
			Obrada o = new Obrada(s);
			obrade.add(o);
			o.start();
		}
		
		try{
			for(Obrada o : obrade){
				o.join();
			}
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju.");
		}
		
		System.out.println("Ukupno je pronadjeno: " + ukupno + " fajlova.");
		System.out.println("Sa ekstenzijom " + ekstenzija + " je pronadjeno: " + pronadjeno + " fajlova.");
		System.out.println("Procenat: " + ((double)pronadjeno/ukupno)*100 + "%");
		
	}
}