import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.util.stream.*;

class Main{
	public static Random rand = new Random();
	static PoolPutnikaThread pool = new PoolPutnikaThread();
	public static void main(String args[]){
		pool.start();
		ArrayList<Vozilo> vozila = new ArrayList<>();
		
		for(int i=0; i< 5; i++){
			Vozilo v = new GradskiAutobus();
			vozila.add(v);
		}
		
		for(int i=0; i< 5; i++){
			Vozilo v = new PrigradskiAutobus();
			vozila.add(v);
		}
		
		for(int i=0; i< 5; i++){
			Vozilo v = new VozBezVagona();
			vozila.add(v);
		}
		
		for(int i=0; i< 5; i++){
			Vozilo v = new VozSaVagonima();
			vozila.add(v);
		}
		
		for(Vozilo v : vozila){
			v.start();
		}
		
		try{
			for(Vozilo v : vozila){
				v.join();
			}
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju vozila.");
		}
		
		pool.radi = false;
		
		PrintWriter pw1 = null;
		PrintWriter pw2 = null;
		
		try{
			pw1 = new PrintWriter(new FileWriter("vozilaSaBaterijama.txt", true));
			pw2 = new PrintWriter(new FileWriter("vozilaBezBaterija.txt", true));
			for(Vozilo v : vozila){
				String putniciStr = v.putnici.stream().map(Object::toString).collect(Collectors.joining(", "));
				if(v instanceof BaterijskoPunjenjeInterface){
					pw1.println(v + " Putnici: " + putniciStr);
				}else{
					pw2.println(v + " Putnici: " + putniciStr);
				}
			}
			
		}catch(IOException e){
			System.out.println("Greska prilikom upisivanja u fajlove.");
		}finally{
			try{
				if(pw1 != null ){
					pw1.close();
				}
			}catch(Exception e){
				System.out.println("Greska prilikom zatvranja fajla vozilaSaBaterijama.txt.");
			}
			
			try{
				if(pw2 != null ){
					pw2.close();
				}
			}catch(Exception e){
				System.out.println("Greska prilikom zatvranja fajla vozilaSaBaterijama.txt.");
			}
		}
	}
}