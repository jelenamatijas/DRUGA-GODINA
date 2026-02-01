import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

class Main{
	static Random rand = new Random();
	static List<Posiljka> posiljke = new ArrayList<>();
	static Object lock = new Object();
	static List<Posiljka> razglednice = new ArrayList<>();
	static List<Posiljka> pisma = new ArrayList<>();
	static List<Posiljka> vrijednosne = new ArrayList<>();
	static volatile boolean razvrstano = false;
	static volatile boolean zapakovano = false;
	static List<Paket> paketi = new ArrayList<>();
	
	static public void main(String args[]){
		
		for(int i=0;i<15;i++){
			posiljke.add(new Razglednica(new File("slika.png")));
			posiljke.add(new Pismo(new File("pismo.txt")));
			posiljke.add(new VrijednosnaPosiljka());
		}
		
		Collections.shuffle(posiljke);
		Razvrstaj razvrstaj = new Razvrstaj();
		Zapakuj zapakuj = new Zapakuj();
		Posalji posalji = new Posalji();
		
		long pocetak = System.currentTimeMillis();
		
		razvrstaj.start();
		zapakuj.start();
		posalji.start();
		
		try{
			razvrstaj.join();
			zapakuj.join();
			posalji.join();
		}catch(InterruptedException e){
			System.out.println("Greska: " +e);
		}
		
		long kraj = System.currentTimeMillis();
		System.out.println("\nVrijeme trajanja simulacije: " + (kraj-pocetak)/60 + "." + (kraj-pocetak)%60 + "s");
		
		File[] files = (new File("folder")).listFiles();
		for(File f : files){
			try{
				long size = Files.size(Paths.get(f.getAbsolutePath()));
				System.out.println(f.getName() + " -> " + size);
			}catch(IOException e){
				System.out.println("Greska: " +e);
			}
		}
	}
}